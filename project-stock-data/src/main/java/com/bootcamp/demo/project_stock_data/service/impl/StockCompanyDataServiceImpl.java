package com.bootcamp.demo.project_stock_data.service.impl;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.demo.project_stock_data.dto.CompanyData;
import com.bootcamp.demo.project_stock_data.service.StockCompanyDataService;

@Service
public class StockCompanyDataServiceImpl implements StockCompanyDataService{
  @Autowired
  private RestTemplate restTemplate;


  /*@Override
  public List<StockOhlcvDto> callAnotherService(){
    String url = "http://localhost:8081/profile";
    ResponseEntity<StockOhlcvDto> response = this.restTemplate.getForEntity(url, StockOhlcvDto.class);
    return response.getBody();
  }*/

  @Override
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
