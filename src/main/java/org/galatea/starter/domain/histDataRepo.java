package org.galatea.starter.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface histDataRepo extends CrudRepository<histData, Long >{

  List<histData> findBySymbol(String symbol);
  List<histData> findByDate(String date);

  histData findById(long id);
}
