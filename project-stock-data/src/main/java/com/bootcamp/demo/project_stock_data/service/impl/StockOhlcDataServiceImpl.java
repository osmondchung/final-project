package com.bootcamp.demo.project_stock_data.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.demo.project_stock_data.dto.CompanyData;
import com.bootcamp.demo.project_stock_data.dto.RealTimeData;
import com.bootcamp.demo.project_stock_data.dto.StockForHeatmap;
import com.bootcamp.demo.project_stock_data.dto.StockOhlcvDto;
import com.bootcamp.demo.project_stock_data.entity.StockOhlcData;
import com.bootcamp.demo.project_stock_data.repository.StockOhlcDataRepository;
import com.bootcamp.demo.project_stock_data.service.StockCompanyDataService;
import com.bootcamp.demo.project_stock_data.service.StockOhlcDataService;

@Service
public class StockOhlcDataServiceImpl implements StockOhlcDataService{
  @Autowired
  private StockOhlcDataRepository stockOhlcDataRepository;
  @Autowired
  private StockCompanyDataService stockCompanyDataService;
  @Autowired
  private RestTemplate restTemplate;
  
  /*@Override
  public List<StockOhlcData> getOhlc(String symbol){
    return this.stockOhlcDataRepository.findBySymbol(symbol);
  }*/

  @Override
  public StockOhlcvDto getOhlcPerStock(String symbol){
    List<StockOhlcData> stockOhlcDatas = this.stockOhlcDataRepository.findBySymbol(symbol);
    CompanyData companyData = this.stockCompanyDataService.callAnotherService2(symbol);
    StockOhlcvDto stockOhlcvDto = StockOhlcvDto.builder()
      .symbol(companyData.getSymbol())
      .companyName(companyData.getCompanyName())
      .marketCap(companyData.getMarketCap())
      .industry(companyData.getIndustry())
      .shareOutstanding(companyData.getShares())
      .logo(companyData.getLogo())
      .Ohlcs(stockOhlcDatas)
      .build();
    return stockOhlcvDto;
  }

}