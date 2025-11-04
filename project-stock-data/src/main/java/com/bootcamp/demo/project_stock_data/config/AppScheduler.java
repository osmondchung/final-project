package com.bootcamp.demo.project_stock_data.config;

import java.time.Duration;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.bootcamp.demo.project_stock_data.dto.StockForHeatmap;
import com.bootcamp.demo.project_stock_data.service.StockForHeatmapService;
import com.bootcamp.demo.project_stock_data.service.StockOhlcDataService;

@Component
@EnableScheduling
public class AppScheduler {
  @Autowired
  private StockForHeatmapService stockForHeatmapService;
  @Autowired
  private StockOhlcDataService stockOhlcDataService;

  @EventListener(ApplicationReadyEvent.class)
  public void runOnStartup(){
    executeProfileFetch();
  }

  @Scheduled(fixedDelay = 600000)
  public void scheduleProfileFetch(){
    executeProfileFetch();
  }

  private void executeProfileFetch(){
    try {
      List<StockForHeatmap> stockForHeatmap = this.stockForHeatmapService.getAllForHeatmap();
    } catch (Exception e) {
    }
  }


}