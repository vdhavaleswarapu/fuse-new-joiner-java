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
  private Double close;
  private Double high;
  private Double low;
  private Double open;
  private String symbol;
  private Long volume;
  private String date;

  protected histData() {}

  public histData(String symbol, String date, Double close, Double high, Double low, Double open, Long volume){
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
  public Double getClose(){ return close;}
  public Double getHigh(){ return high;}
  public Double getLow(){ return low;}
  public Double getOpen(){ return open;}
  public Long getVolume(){ return volume;}

  @Override
  public String toString(){
    return "histData[id="+id+", symbol = "+symbol+", date = "+date+", close = "+close+", high = "+high+", low = "+low+", open = "+open+", volume = "+volume+"]";
  }
}