package org.galatea.starter.domain;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface histDataRepo extends CrudRepository<histData, Long >{

  List<histData> findBySymbolAndDate(String symbols, String tp);

}
