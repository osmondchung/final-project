package com.bootcamp.demo.project_data_provider.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.demo.project_data_provider.dto.CompanyDataDto;
import com.bootcamp.demo.project_data_provider.dto.StockDto;

public interface StockPriceOperation {
  @GetMapping(value = "/quote")
  StockDto getQuote(@RequestParam String symbol);

  @GetMapping(value = "/profile")
  CompanyDataDto getProfile(@RequestParam String symbol);

  @GetMapping(value = "/allquotes")
  List<StockDto> getAllQuotes();

  @GetMapping(value = "/allprofiles")
  List<CompanyDataDto> getAllProfiles();
}
