package org.galatea.starter.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface histDataRepo extends CrudRepository<histData, Long> {

  /**
   * Define a method to Query the H2 Database on Symbol And Date.
   */
  List<histData> findBySymbolAndDate(String symbols, String tp);

}
