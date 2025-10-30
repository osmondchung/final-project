package com.bootcamp.demo.project_stock_data.service.impl;

import com.bootcamp.demo.project_stock_data.dto.CompanyData;
import com.bootcamp.demo.project_stock_data.entity.StockProfile;
import com.bootcamp.demo.project_stock_data.entity.StockSymbol;
import com.bootcamp.demo.project_stock_data.repository.StockProfilesRepository;
import com.bootcamp.demo.project_stock_data.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockProfileSyncService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StockProfilesRepository stockProfilesRepository;
    @Autowired
    private StockRepository stockRepository;

    private final String baseUrl = "http://localhost:8081";

    @Scheduled(cron = "${stock.collector.cron:0 */5 * * * *}")
    public void syncAllProfiles() {  // ← NO @Transactional
        stockProfilesRepository.deleteAllInBatch();
        List<String> stockSymbols = this.stockRepository.getTop30MarketcapStockSymbol();

        List<CompanyData> companyDatas = new ArrayList<>();
        for (String stockSymbol : stockSymbols){
          //String symbol = stockSymbol.getSymbol();
            String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/profile")
                    .queryParam("symbol", stockSymbol)
                    .encode()
                    .toUriString();

            try {
                ResponseEntity<CompanyData> response = this.restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<CompanyData>() {}
                );

                CompanyData body = response.getBody();
                companyDatas.add(body);

                /*CompanyData dtos = response.getBody();
                if (dtos == null || dtos.isEmpty()) {
                    log.info("No profile data from provider");
                    return;
                }*/

                //log.info("Received {} profiles", dtos.size());
                
                /*List<StockProfile> entities = dtos.stream()
                        .map(this::toEntity)
                        .toList();

                // Use batch operations
                //stockProfilesRepository.deleteAllInBatch();
                stockProfilesRepository.saveAllAndFlush(entities);

                log.info("Synced {} profiles", entities.size());*/

            } catch (Exception e) {
                log.error("Profile sync failed – will retry later", e);
                // No rethrow, no transaction → safe
            }
          }
        if (!companyDatas.isEmpty()) {
            List<StockProfile> entities = companyDatas.stream()
                    .map(this::toEntity)
                    .toList();

            stockProfilesRepository.saveAllAndFlush(entities);
            log.info("Synced {} stock profiles", entities.size());
          } else {
              log.warn("No valid company data received");
          }
    }

    private StockProfile toEntity(CompanyData dto) {
        return StockProfile.builder()
                          .symbol(dto.getSymbol())
                          .industry(dto.getIndustry())
                          .marketCap(dto.getMarketCap())
                          .logo(dto.getLogo())
                          .companyName(dto.getCompanyName())
                          .shares(dto.getShares())
                          .ipo(dto.getIpo())
                          .weburl(dto.getWeburl())
                          .build();
    }

}