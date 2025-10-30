package com.bootcamp.demo.project_stock_data.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.project_stock_data.controller.StockDataAppOperation;
import com.bootcamp.demo.project_stock_data.dto.CompanyData;
import com.bootcamp.demo.project_stock_data.dto.RealTimeData;
import com.bootcamp.demo.project_stock_data.dto.StockForHeatmap;
import com.bootcamp.demo.project_stock_data.dto.StockOhlcvDto;
import com.bootcamp.demo.project_stock_data.entity.StockProfile;
import com.bootcamp.demo.project_stock_data.entity.StockSymbol;
import com.bootcamp.demo.project_stock_data.service.StockCompanyDataService;
import com.bootcamp.demo.project_stock_data.service.StockForHeatmapService;
import com.bootcamp.demo.project_stock_data.service.StockOhlcDataService;
import com.bootcamp.demo.project_stock_data.service.StockRealTimeDataService;
import com.bootcamp.demo.project_stock_data.service.StockSymbolService;

@RestController
public class StockDataAppController implements StockDataAppOperation{
  @Autowired
  private StockCompanyDataService stockCompanyDataService;
  @Autowired
  private StockOhlcDataService stockOhlcDataService;
  @Autowired
  private StockRealTimeDataService stockRealTimeDataService;
  @Autowired
  private StockSymbolService stockSymbolService;
  @Autowired
  private StockForHeatmapService stockForHeatmapService;

  @Override
  public List<String> getSymbols(){
    return this.stockSymbolService.getStocks();
  }

  /*@Override
  public List<StockOhlcData> getOhlc(String symbol){
    return this.stockOhlcDataService.getOhlc(symbol);
  }*/

  @Override
  public  StockOhlcvDto getOhlcPerStock(String symbol){
    return this.stockOhlcDataService.getOhlcPerStock(symbol);
  }


  @Override
  public List<RealTimeData> getAllQuotes(){
    return this.stockRealTimeDataService.callAnotherService();
  }

  @Override
  public List<StockProfile> getAllProfiles(){
    return this.stockCompanyDataService.callAnotherService();
  }

  @Override
  public List<StockForHeatmap> getForHeatmap(){
    return this.stockForHeatmapService.getAllForHeatmap();
  }
}
