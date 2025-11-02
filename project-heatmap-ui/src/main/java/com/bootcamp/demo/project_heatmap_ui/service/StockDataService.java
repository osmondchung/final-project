package com.bootcamp.demo.project_heatmap_ui.service;

import com.bootcamp.demo.project_heatmap_ui.DTO.StockForHeatmap;
import com.bootcamp.demo.project_heatmap_ui.DTO.StockOhlcvDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockDataService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${stock.data.base-url}")
    private String baseUrl;

    public List<StockForHeatmap> getHeatmapData() {
        String url = baseUrl + "/data/heatmap";
        return this.restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<StockForHeatmap>>() {}
        ).getBody();
    }

    public StockOhlcvDto getOhlc(String symbol) {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/ohlc")
                .queryParam("symbol", symbol)
                .encode()
                .toUriString();

        return this.restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                StockOhlcvDto.class
        ).getBody();
    }

    
}