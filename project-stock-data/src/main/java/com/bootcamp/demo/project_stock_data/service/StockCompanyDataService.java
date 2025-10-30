package com.bootcamp.demo.project_stock_data.service;

import java.util.List;
import com.bootcamp.demo.project_stock_data.dto.CompanyData;
import com.bootcamp.demo.project_stock_data.entity.StockProfile;

public interface StockCompanyDataService {
  List<StockProfile> callAnotherService();
  CompanyData callAnotherService2(String symbol);
}
