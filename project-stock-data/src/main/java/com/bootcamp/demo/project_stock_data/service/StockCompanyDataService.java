package com.bootcamp.demo.project_stock_data.service;

import java.util.List;
import com.bootcamp.demo.project_stock_data.dto.CompanyData;

public interface StockCompanyDataService {
  List<CompanyData> callAnotherService();
  CompanyData callAnotherService2(String symbol);
}
