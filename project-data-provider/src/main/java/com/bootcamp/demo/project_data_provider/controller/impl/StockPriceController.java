package com.bootcamp.demo.project_data_provider.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.project_data_provider.config.AppScheduler;
import com.bootcamp.demo.project_data_provider.controller.StockPriceOperation;
import com.bootcamp.demo.project_data_provider.dto.CompanyDataDto;
import com.bootcamp.demo.project_data_provider.dto.StockDto;
import com.bootcamp.demo.project_data_provider.model.dto.CompanyDataDTO;
import com.bootcamp.demo.project_data_provider.model.dto.StockDTO;
import com.bootcamp.demo.project_data_provider.service.StockService;

@RestController
public class StockPriceController implements StockPriceOperation{
  @Autowired
  private StockService stockService;

  @Autowired
  private AppScheduler scheduler;

  @Override
  public StockDto getQuote(String symbol){
    return this.stockService.getQuote(symbol);
  }

  @Override
  public CompanyDataDto getProfile(String symbol){
    return this.stockService.getProfile(symbol);
  }

  @Override
  public List<StockDto> getAllQuotes() {
    return this.stockService.getAllQuotes();
  }

  @Override
  public List<CompanyDataDto> getAllProfiles(){
    return this.stockService.getAllProfiles();
  }

}
