package com.bootcamp.demo.project_stock_data.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.demo.project_stock_data.dto.CompanyData;
import com.bootcamp.demo.project_stock_data.entity.StockProfile;
import com.bootcamp.demo.project_stock_data.entity.StockSymbol;
import com.bootcamp.demo.project_stock_data.repository.StockProfilesRepository;
import com.bootcamp.demo.project_stock_data.repository.StockRepository;
import com.bootcamp.demo.project_stock_data.service.StockCompanyDataService;

@Service
public class StockCompanyDataServiceImpl implements StockCompanyDataService{
  @Autowired
  private RestTemplate restTemplate;
  @Autowired
  private StockRepository stockRepository;
  @Autowired
  private StockProfilesRepository stockProfilesRepository;

  /*@Override
  public List<StockOhlcvDto> callAnotherService(){
    String url = "http://localhost:8081/profile";
    ResponseEntity<StockOhlcvDto> response = this.restTemplate.getForEntity(url, StockOhlcvDto.class);
    return response.getBody();
  }*/

  /*@Override
  public List<CompanyData> callAnotherService() {
    String url = "http://localhost:8081/allprofiles";
    try {
        ResponseEntity<List<CompanyData>> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<CompanyData>>() {}
        );

        List<CompanyData> body = response.getBody();
        return body != null ? body : Collections.emptyList();

    } catch (HttpClientErrorException | HttpServerErrorException e) {
        throw new RuntimeException("Failed to fetch profiles: " + e.getMessage(), e);
    }
  }*/

    // get all real time quotes
  @Override
  public List<StockProfile> callAnotherService() {
    //this.stockProfilesRepository.deleteAll();
    List<String> stockSymbols = this.stockRepository.getTop30MarketcapStockSymbol();
    List<StockProfile> stockProfiles = new ArrayList<>();
    for (String stockSymbol : stockSymbols){
        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/profile")
          .queryParam("symbol", stockSymbol)
          .encode()
          .toUriString();
        try {
            ResponseEntity<StockProfile> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<StockProfile>() {}
            );

            StockProfile body = response.getBody();
            stockProfiles.add(body);
            //return body;

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new RuntimeException("Failed to fetch quotes: " + e.getMessage(), e);
        }
    }
    //this.stockProfilesRepository.saveAll(stockProfiles);
    return stockProfiles;
  }

  @Override
  public CompanyData callAnotherService2(String symbol) {
    String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/profile")
      .queryParam("symbol", symbol)
      .encode()
      .toUriString();
    try {
        ResponseEntity<CompanyData> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<CompanyData>() {}
        );

        CompanyData body = response.getBody();
        return body;

    } catch (HttpClientErrorException | HttpServerErrorException e) {
        throw new RuntimeException("Failed to fetch profiles: " + e.getMessage(), e);
    }
  }

}
