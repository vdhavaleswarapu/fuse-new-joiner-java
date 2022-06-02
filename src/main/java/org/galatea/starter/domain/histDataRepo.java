package org.galatea.starter.domain;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface histDataRepo extends CrudRepository<histData, Long >{

  List<histData> findBySymbol(String symbols);
  List<histData> findByDate(String tp);
//  @Query("SELECT close, high, low, open, symbol, volume, date FROM histData WHERE histData.symbol = ?1 AND histData.date = ?2")
//  List<histData> findByDataExists(String symbol, String date);
//  histData findById(long id);
}
