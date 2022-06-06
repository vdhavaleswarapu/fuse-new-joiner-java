package org.galatea.starter.domain;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class histData {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private BigDecimal close;
  private BigDecimal high;
  private BigDecimal low;
  private BigDecimal open;
  private String symbol;
  private Integer volume;
  private String date;

  protected histData() {}

  /**
   * @param symbol symbol of the stock.
   * @param date date for which the data is requested.
   * @param close closing price of the stock on selected date.
   * @param high highest price of the stock on selected date.
   * @param low lowest price of the stock on selected date.
   * @param open opening price of the stock on selected date.
   * @param volume volume of the stock exchanged on selected date.
   */
  public histData(final String symbol, final String date, final BigDecimal close,
      final BigDecimal high, final BigDecimal low, final BigDecimal open, final Integer volume) {
    this.symbol = symbol;
    this.date = date;
    this.close = close;
    this.high = high;
    this.low = low;
    this.open = open;
    this.volume = volume;
  }


  public Long getId() {return id;}

  public String getSymbol() {return symbol;}

  public String getDate() {return date;}

  public BigDecimal getClose() {return close;}

  public BigDecimal getHigh() {return high;}

  public BigDecimal getLow() {return low;}

  public BigDecimal getOpen() {return open;}

  public Integer getVolume() {return volume;}

  @Override
  public String toString() {
    return "histData[id=" + id + ", symbol = " + symbol + ", date = " + date + ", close = " + close
        + ", high = " + high + ", low = " + low + ", open = " + open + ", volume = " + volume + "]";
  }
}