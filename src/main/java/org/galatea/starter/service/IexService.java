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
    List<histData> result = new ArrayList<>();

    String convDate = tp.substring(0,4)+"-"+tp.substring(4,6)+"-"+tp.substring(6);

    repo.findBySymbolAndDate(symbols.toUpperCase(), convDate).forEach(i->  result.add(i));
    List <IexHistoricalPrice> localData = (List<IexHistoricalPrice>)(Object)result;

    if(!result.isEmpty()){
      System.out.println("Data available locally ... ");
      System.out.println(localData); 
      return localData;
    }
    else {
      System.out.println("Data not available locally. Querying the IEX API for data ...");
      List<IexHistoricalPrice> iexData = iexClient.getHistoricalPrice(symbols, tp);
      var data= iexData.get(0);

      repo.save(new histData(data.getSymbol(), data.getDate(), data.getClose(), data.getHigh(), data.getLow(), data.getOpen(), data.getVolume()));
      System.out.println("Data saved to in-memory DB");
      return iexData;
    }
  }
  }
