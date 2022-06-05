package org.galatea.starter.domain;

import io.swagger.models.auth.In;
import java.math.BigDecimal;
import java.util.List;
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

  public histData(String symbol, String date, BigDecimal close, BigDecimal high, BigDecimal low, BigDecimal open, Integer volume){
    this.symbol = symbol;
    this.date = date;
    this.close = close;
    this.high = high;
    this.low = low;
    this.open = open;
    this.volume = volume;
  }


  public Long getId(){ return id;}
  public String getSymbol(){ return symbol;}
  public String getDate(){ return date;}
  public BigDecimal getClose(){ return close;}
  public BigDecimal getHigh(){ return high;}
  public BigDecimal getLow(){ return low;}
  public BigDecimal getOpen(){ return open;}
  public Integer getVolume(){ return volume;}

  @Override
  public String toString(){
    return "histData[id="+id+", symbol = "+symbol+", date = "+date+", close = "+close+", high = "+high+", low = "+low+", open = "+open+", volume = "+volume+"]";
  }
}