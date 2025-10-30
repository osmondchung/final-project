package com.bootcamp.demo.project_stock_data.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.demo.project_stock_data.dto.CompanyData;
import com.bootcamp.demo.project_stock_data.dto.RealTimeData;
import com.bootcamp.demo.project_stock_data.dto.StockForHeatmap;
import com.bootcamp.demo.project_stock_data.entity.StockProfile;
import com.bootcamp.demo.project_stock_data.entity.StockSymbol;
import com.bootcamp.demo.project_stock_data.repository.StockRepository;
import com.bootcamp.demo.project_stock_data.service.StockCompanyDataService;
import com.bootcamp.demo.project_stock_data.service.StockForHeatmapService;
import com.bootcamp.demo.project_stock_data.service.StockRealTimeDataService;

@Service
public class StockForHeatmapServiceImpl implements StockForHeatmapService{
  @Autowired
  private RestTemplate restTemplate;
  @Autowired
  private StockRepository stockRepository;
  @Autowired
  private StockRealTimeDataService stockRealTimeDataService;
  @Autowired
  private StockCompanyDataService stockCompanyDataService;

  @Override
  public List<StockForHeatmap> getAllForHeatmap(){
    List<RealTimeData> realTimeDatas = this.stockRealTimeDataService.callAnotherService();
    List<StockProfile> companyDatas = this.stockCompanyDataService.callAnotherService();
    List<StockForHeatmap> output = new ArrayList<>();

    for (int i = 0; i < realTimeDatas.size(); i++){
      StockForHeatmap stockForHeatmap = StockForHeatmap.builder()
        .symbol(realTimeDatas.get(i).getSymbol())
        .price(realTimeDatas.get(i).getPrice())
        .marketPriceChg(realTimeDatas.get(i).getChange())
        .marketPriceChgPct(realTimeDatas.get(i).getChangesPercentage())
        .name(companyDatas.get(i).getCompanyName())
        .marketCap(companyDatas.get(i).getMarketCap())
        .industry(companyDatas.get(i).getIndustry())
        .ipoDate(companyDatas.get(i).getIpo())
        .webUrl(companyDatas.get(i).getWeburl())
        .shareOutstanding(companyDatas.get(i).getShares())
        .logo(companyDatas.get(i).getLogo())
        .build();
      output.add(stockForHeatmap);
    }
    return output;
  }
}
