package com.bootcamp.demo.project_stock_data.service;

import java.util.List;
import com.bootcamp.demo.project_stock_data.dto.CompanyData;
import com.bootcamp.demo.project_stock_data.dto.RealTimeData;
import com.bootcamp.demo.project_stock_data.dto.StockForHeatmap;

public interface StockForHeatmapService {
  List<StockForHeatmap> getAllForHeatmap();
}
