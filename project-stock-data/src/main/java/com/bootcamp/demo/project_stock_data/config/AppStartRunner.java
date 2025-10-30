package com.bootcamp.demo.project_stock_data.config;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.bootcamp.demo.project_stock_data.entity.StockProfile;
import com.bootcamp.demo.project_stock_data.repository.StockOhlcDataRepository;
import com.bootcamp.demo.project_stock_data.repository.StockProfilesRepository;
import com.bootcamp.demo.project_stock_data.repository.StockRepository;
import com.bootcamp.demo.project_stock_data.service.StockCompanyDataService;
import com.bootcamp.demo.project_stock_data.service.impl.StockProfileSyncService;
import lombok.RequiredArgsConstructor;

@Component
//@RequiredArgsConstructor
public class AppStartRunner {

    /*@Autowired
    private StockProfileSyncService syncService;

    @Override
    public void run(String... args) {
        syncService.syncAllProfiles();  // fire and forget
    }*/
}
  // app start create entity -> delete all repository -> get companydata from dataprovider & run python script -> save repository

  /*@Override
  public void run(String... args) throws Exception {
    //this.stockRepository.deleteAll();
    this.stockProfilesRepository.deleteAll();
    //this.stockOhlcDataRepository.deleteAll();

    List<StockProfile> stockProfiles = this.stockCompanyDataService.callAnotherService();

    this.stockProfilesRepository.saveAll(stockProfiles);
  }*/


/*@Autowired
    private StockProfileRepository profileRepo;

    @Override
    @Transactional
    public void run(String... args) {
        List<StockProfile> profiles = loadFromApiOrCsv();

        for (StockProfile p : profiles) {
            String symbol = p.getSymbol().getSymbol();

            Optional<StockProfile> existing = profileRepo.findBySymbolSymbol(symbol);
            if (existing.isPresent()) {
                StockProfile entity = existing.get();
                // Update fields
                entity.setCompanyName(p.getCompanyName());
                entity.setSector(p.getSector());
                // ... update other fields
                profileRepo.save(entity); // This is now attached
            } else {
                profileRepo.save(p);
            }
        }
    }*/