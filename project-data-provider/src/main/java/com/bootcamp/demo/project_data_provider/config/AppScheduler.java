package com.bootcamp.demo.project_data_provider.config;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.bootcamp.demo.project_data_provider.model.dto.StockDTO;
import com.bootcamp.demo.project_data_provider.service.StockService;

@Component
public class AppScheduler {

  private static final Logger logger = LoggerFactory.getLogger(AppScheduler.class);

  @Autowired
  private StockService stockService;

  private List<StockDTO> cachedTopStocks = null;

  /*@Scheduled(fixedRate = 15000)
  public void refreshStockData(){
    try {
        logger.info("Refreshing top 50 stocks every 15 seconds...");
        cachedTopStocks = stockService.getAllQuotes(); // Fetches and sorts top 50
        logger.info("Refreshed {} stocks at {}", cachedTopStocks.size(), new java.util.Date());
    } catch (Exception e) {
        logger.error("Error refreshing stock data: {}", e.getMessage(), e);
    }
  }*/

  public List<StockDTO> getCachedTopStocks(){
    return cachedTopStocks;
  }

}
