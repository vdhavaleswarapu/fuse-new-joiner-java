package org.galatea.starter.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.galatea.starter.domain.IexHistoricalPrice;
import org.galatea.starter.domain.IexLastTradedPrice;
import org.galatea.starter.domain.IexSymbol;
import org.galatea.starter.domain.histData;
import org.galatea.starter.domain.histDataRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.util.Optionals;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * A layer for transformation, aggregation, and business required when retrieving data from IEX.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class IexService {

  @NonNull
  private IexClient iexClient;


  /**
   * Get all stock symbols from IEX.
   *
   * @return a list of all Stock Symbols from IEX.
   */
  public List<IexSymbol> getAllSymbols() {
    return iexClient.getAllSymbols();
  }

  /**
   * Get the last traded price for each Symbol that is passed in.
   *
   * @param symbols the list of symbols to get a last traded price for.
   * @return a list of last traded price objects for each Symbol that is passed in.
   */
  public List<IexLastTradedPrice> getLastTradedPriceForSymbols(final List<String> symbols) {
    if (CollectionUtils.isEmpty(symbols)) {
      return Collections.emptyList();
    } else {
      return iexClient.getLastTradedPriceForSymbols(symbols.toArray(new String[0]));
    }
  }
  public List<IexHistoricalPrice> getHistoricalPrice(final String symbols, final String tp, histDataRepo repo){
    if(symbols.isEmpty() || tp.isEmpty()) {
      return Collections.emptyList();
    }
    List<IexHistoricalPrice> dummy = new ArrayList<>(); //returning dummy because of incosistent return type. Needs fixing.
    List<histData> result = new ArrayList<>();

    String convDate = tp.substring(0,4)+"-"+tp.substring(4,6)+"-"+tp.substring(6);

    repo.findBySymbolAndDate(symbols.toUpperCase(), convDate).forEach(i->  result.add(i));

    if(!result.isEmpty()){
      System.out.println("Data available locally ... ");
      System.out.println(result); //Currently only printing data to the console and not actually returning it. Needs fixing.
      return dummy;
    }
    else {
      System.out.println("Data not available locally. Querying the IEX API for data ...");
      var data= iexClient.getHistoricalPrice(symbols, tp);
      String row = data.toString();

      double close = Double.parseDouble(helperFunctionIEXData(row, "close="));
      double high = Double.parseDouble(helperFunctionIEXData(row, "high="));
      double open = Double.parseDouble(helperFunctionIEXData(row, "open="));
      double low = Double.parseDouble(helperFunctionIEXData(row, "low="));
      long volume = Long.parseLong(helperFunctionIEXData(row, "volume="));
      String symbol = helperFunctionIEXData(row, "symbol=");
      String date = helperFunctionIEXDate(row, "date=");

      repo.save(new histData(symbol, date, close, high, low, open, volume));
      System.out.println("Data saved to in-memory DB");
      return data;
    }
  }

  public String helperFunctionIEXData(String data, String fieldName){
    String fieldVal = data.substring(data.indexOf(fieldName)+1);
    fieldVal = fieldVal.substring(0, fieldVal.indexOf(","));
    fieldVal = fieldVal.substring(fieldVal.indexOf("=")+1);

    return fieldVal;
  }

  public String helperFunctionIEXDate(String data, String fieldName){
    String fieldVal = data.substring(data.indexOf(fieldName)+1);
    fieldVal = fieldVal.substring(0, fieldVal.indexOf(")"));
    fieldVal = fieldVal.substring(fieldVal.indexOf("=")+1);

    return fieldVal;
  }

}
