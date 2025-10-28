package com.bootcamp.demo.project_stock_data.service;

import java.util.List;
import com.bootcamp.demo.project_stock_data.entity.StockSymbol;

public interface StockSymbolService {
  List<StockSymbol> getStocks();
}
