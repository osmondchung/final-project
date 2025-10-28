package com.bootcamp.demo.project_stock_data.config;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bootcamp.demo.project_stock_data.repository.StockOhlcDataRepository;
import com.bootcamp.demo.project_stock_data.repository.StockProfilesRepository;
import com.bootcamp.demo.project_stock_data.repository.StockRepository;

@Component
public class AppStartRunner {
  @Autowired
  public StockRepository stockRepository;
  @Autowired
  public StockProfilesRepository stockProfilesRepository;
  @Autowired
  public StockOhlcDataRepository stockOhlcDataRepository;

}

