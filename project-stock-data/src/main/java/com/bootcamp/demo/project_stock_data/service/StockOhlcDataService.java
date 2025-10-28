package com.bootcamp.demo.project_stock_data.service;

import java.util.List;
import com.bootcamp.demo.project_stock_data.dto.StockOhlcvDto;
import com.bootcamp.demo.project_stock_data.entity.StockOhlcData;

public interface StockOhlcDataService {
  List<StockOhlcData> getOhlc(String symbol);
  StockOhlcvDto getOhlcPerStock(String symbol);
}
