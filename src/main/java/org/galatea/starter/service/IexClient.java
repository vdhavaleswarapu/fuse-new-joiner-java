package org.galatea.starter.service;

import java.util.List;
import org.galatea.starter.domain.IexHistoricalPrice;
import org.galatea.starter.domain.IexLastTradedPrice;
import org.galatea.starter.domain.IexSymbol;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * A Feign Declarative REST Client to access endpoints from the Free and Open IEX API to get market
 * data. See https://iextrading.com/developer/docs/
 */
@FeignClient(name = "IEX", url = "${spring.rest.iexBasePath}")
public interface IexClient {

  /**
   * Get a list of all stocks supported by IEX. See https://iextrading.com/developer/docs/#symbols.
   * As of July 2019 this returns almost 9,000 symbols, so maybe don't call it in a loop.
   *
   * @return a list of all of the stock symbols supported by IEX.
   */
  @GetMapping("/ref-data/symbols?token={token}")
  //hardcoded private token to the mappings
  List<IexSymbol> getAllSymbols(@PathVariable("token") String token);

  /**
   * Get the last traded price for each stock symbol passed in. See
   * https://iextrading.com/developer/docs/#last.
   *
   * @param symbols stock symbols to get last traded price for.
   * @return a list of the last traded price for each of the symbols passed in.
   */
  @GetMapping("/tops/last?token={token}")
  List<IexLastTradedPrice> getLastTradedPriceForSymbols(@RequestParam("symbols") String symbols,
      @PathVariable("token") String token);

  /**
   * Get the historical price of a stock on a selected date.
   *
   * @param symbols Stock symbol to get historical price data for.
   * @param timePeriod Date on which the historical price for a stock is to be pulled.
   * @return The high, low, open, close, volume of the selected stock on the selected date.
   */
  @GetMapping(
      "/stock/{symbols}/chart/date/{timePeriod}?token={token}&chartByDay=true")
  List<IexHistoricalPrice> getHistoricalPrice(@PathVariable("symbols") String symbols,
      @PathVariable("timePeriod") String timePeriod, @PathVariable("token") String token);

}
