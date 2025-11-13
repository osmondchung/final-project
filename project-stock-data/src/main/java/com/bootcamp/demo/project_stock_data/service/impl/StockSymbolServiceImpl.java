package com.bootcamp.demo.project_stock_data.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.demo.project_stock_data.entity.StockSymbol;
import com.bootcamp.demo.project_stock_data.repository.StockRepository;
import com.bootcamp.demo.project_stock_data.service.StockSymbolService;

@Service
public class StockSymbolServiceImpl implements StockSymbolService{
  @Autowired
  private StockRepository stockRepository;

  @Override
  public List<String> getStocks(){
    return this.stockRepository.getStockSymbol();
  }
}
