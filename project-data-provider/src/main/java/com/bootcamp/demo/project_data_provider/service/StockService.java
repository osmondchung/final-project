package com.bootcamp.demo.project_data_provider.service;

import java.util.List;
import com.bootcamp.demo.project_data_provider.dto.CompanyDataDto;
import com.bootcamp.demo.project_data_provider.dto.StockDto;
import com.bootcamp.demo.project_data_provider.model.dto.CompanyDataDTO;
import com.bootcamp.demo.project_data_provider.model.dto.StockDTO;

public interface StockService {
  StockDto getQuote(String symbol);
  CompanyDataDto getProfile(String symbol); 
  List<StockDto> getAllQuotes();
  List<CompanyDataDto> getAllProfiles();
}
