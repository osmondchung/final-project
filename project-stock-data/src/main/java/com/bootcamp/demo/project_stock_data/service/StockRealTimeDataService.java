package com.bootcamp.demo.project_stock_data.service;

import java.util.List;
import com.bootcamp.demo.project_stock_data.dto.RealTimeData;

public interface StockRealTimeDataService {
  List<RealTimeData> callAnotherService();
  RealTimeData callAnotherService2(String symbol);
}
