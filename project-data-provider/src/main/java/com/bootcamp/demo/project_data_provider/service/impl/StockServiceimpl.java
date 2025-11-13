package com.bootcamp.demo.project_data_provider.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.demo.project_data_provider.dto.CompanyDataDto;
import com.bootcamp.demo.project_data_provider.dto.StockDto;
import com.bootcamp.demo.project_data_provider.model.dto.CompanyDataDTO;
import com.bootcamp.demo.project_data_provider.model.dto.StockDTO;
import com.bootcamp.demo.project_data_provider.service.StockService;

@Service
public class StockServiceimpl implements StockService{

  @Autowired
  private RestTemplate restTemplate;

  @Value("${finnhub.token}")
  private String token;

  @Value("${StockQuote.url}")
  private String stockQuoteUrl;

  @Value("${ComProfile.url}")
  private String comProfileUrl;

  @Value("${sp500.symbols.url}")
  private String sp500SymbolsUrl;

  public static final String t = "&token=";

  @Override
  public StockDto getQuote(String symbol){
    String StockQuoteURL = stockQuoteUrl + symbol + t + token;
    StockDTO stockDTO = this.restTemplate.getForObject(StockQuoteURL, StockDTO.class);

    StockDto stockDto = StockDto.builder()
      .symbol(stockDTO.getSymbol())
      .price(stockDTO.getPrice())
      .change(stockDTO.getChange())
      .changesPercentage(stockDTO.getChangesPercentage())
      .highPrice(stockDTO.getHighPrice())
      .lowPrice(stockDTO.getLowPrice())
      .openPrice(stockDTO.getOpenPrice())
      .previousPrice(stockDTO.getPreviousPrice())
      .build();
    if (stockDto.getSymbol() == null){
      stockDto.setSymbol(symbol);
    }
    return stockDto;
  }

  @Override
  public CompanyDataDto getProfile(String symbol){
    String ComProfileURL = comProfileUrl + symbol + t + token;
    CompanyDataDTO companyDataDTO = this.restTemplate.getForObject(ComProfileURL, CompanyDataDTO.class);

    CompanyDataDto companyDataDto = CompanyDataDto.builder()
      .symbol(companyDataDTO.getSymbol())
      .industry(companyDataDTO.getIndustry())
      .marketCap(companyDataDTO.getMarketCap())
      .logo(companyDataDTO.getLogo())
      .companyName(companyDataDTO.getCompanyName())
      .shares(companyDataDTO.getShares())
      .ipo(companyDataDTO.getIpo())
      .weburl(companyDataDTO.getWeburl())
      .build();
    if (companyDataDto.getSymbol() == null){
      companyDataDto.setSymbol(symbol);
    }
    return companyDataDto;
  }
  
}
