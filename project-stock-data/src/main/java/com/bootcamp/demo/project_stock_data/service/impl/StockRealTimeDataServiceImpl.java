package com.bootcamp.demo.project_stock_data.service.impl;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.demo.project_stock_data.dto.RealTimeData;
import com.bootcamp.demo.project_stock_data.service.StockRealTimeDataService;

@Service
public class StockRealTimeDataServiceImpl implements StockRealTimeDataService{
  @Autowired
  private RestTemplate restTemplate;

  /*@Override
  public RealTimeData callAnotherService(){
    String url = "http://localhost:8081/allquotes";
    ResponseEntity<RealTimeData> response = this.restTemplate.getForEntity(url, RealTimeData.class);
    return response.getBody();
  }*/
  @Override
  public List<RealTimeData> callAnotherService() {
    String url = "http://localhost:8081/allquotes";
    try {
        ResponseEntity<List<RealTimeData>> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<RealTimeData>>() {}
        );

        List<RealTimeData> body = response.getBody();
        return body != null ? body : Collections.emptyList();

    } catch (HttpClientErrorException | HttpServerErrorException e) {
        throw new RuntimeException("Failed to fetch quotes: " + e.getMessage(), e);
    }
  }

  @Override
  public RealTimeData callAnotherService2(String symbol){
    String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/allquote")
      .queryParam("symbol", symbol)
      .encode()
      .toUriString();
    try {
        ResponseEntity<RealTimeData> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<RealTimeData>() {}
        );

        RealTimeData body = response.getBody();
        return body;

    } catch (HttpClientErrorException | HttpServerErrorException e) {
        throw new RuntimeException("Failed to fetch quotes: " + e.getMessage(), e);
    }
  }


}