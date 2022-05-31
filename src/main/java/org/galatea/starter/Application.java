package org.galatea.starter;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.galatea.starter.domain.IexHistoricalPrice;
import org.galatea.starter.domain.histData;
import org.galatea.starter.domain.histDataRepo;
import org.galatea.starter.entrypoint.IexRestController;
import org.galatea.starter.service.IexClient;
import org.galatea.starter.service.IexService;
import org.galatea.starter.utils.exception.MissingOptionException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Query;


/**
 * This is the entry point for the application.
 */
@RequiredArgsConstructor
@Slf4j
@SpringBootApplication
public class Application implements ApplicationRunner {

  /**
   * Start up the spring context.
   *
   * @param args command line args
   */

  private static final Logger log = LoggerFactory.getLogger(Application.class);
  public static void main(final String[] args) {

    log.info("Starting spring application {}", System.getProperty("application.name"));
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public CommandLineRunner push(histDataRepo repository){


    return (args) -> {
      repository.save(new histData("FB","2022-05-02" , 211.13, 211.18, 200.99, 201.165, 49915289));
      System.out.println("written to DB");
//      repository.findBySymbol("FB").forEach(i-> repository.findByDate("2022-05-02").forEach(j-> log.info(j.toString())));
//      };
    };
  };

  /**
   * Ensure that server port is passed in as a command line argument.
   *
   * @param args command line arguments
   * @throws MissingOptionException if server port not provided as argument
   */
  @Override
  public void run(final ApplicationArguments args) {
    if (!args.containsOption("server.port") && System.getProperty("server.port") == null) {
      throw new MissingOptionException("Server port must be set via command line parameter");
    }
  }
}
