package com.bootcamp.demo.project_stock_data.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.demo.project_stock_data.entity.StockSymbol;
import com.bootcamp.demo.project_stock_data.dto.CompanyData;
import com.bootcamp.demo.project_stock_data.dto.RealTimeData;
import com.bootcamp.demo.project_stock_data.dto.StockForHeatmap;
import com.bootcamp.demo.project_stock_data.dto.StockOhlcvDto;
import com.bootcamp.demo.project_stock_data.entity.StockOhlcData;

public interface StockDataAppOperation {
  @GetMapping(value = "/symbols")
  List<StockSymbol> getSymbols();

  @GetMapping(value = "/ohlc")
  List<StockOhlcData> getOhlc(@RequestParam String symbol);

  @GetMapping(value = "/ohlc2")
  StockOhlcvDto getOhlcPerStock(@RequestParam String symbol);


  @GetMapping(value = "/allquotes")
  List<RealTimeData> getAllQuotes();

  @GetMapping(value = "/allprofiles")
  List<CompanyData> getAllProfiles();

  @GetMapping(value = "/data/heatmap")
  List<StockForHeatmap> getForHeatmap();
}
