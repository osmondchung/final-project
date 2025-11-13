package com.bootcamp.demo.project_data_provider.service;

import com.bootcamp.demo.project_data_provider.dto.CompanyDataDto;
import com.bootcamp.demo.project_data_provider.dto.StockDto;

public interface StockService {
  StockDto getQuote(String symbol);
  CompanyDataDto getProfile(String symbol); 
}
