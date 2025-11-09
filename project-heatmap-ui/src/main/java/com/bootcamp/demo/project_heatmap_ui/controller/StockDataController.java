package com.bootcamp.demo.project_heatmap_ui.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.project_heatmap_ui.DTO.StockForHeatmap;
import com.bootcamp.demo.project_heatmap_ui.DTO.StockOhlcvDto;
import com.bootcamp.demo.project_heatmap_ui.service.StockDataService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StockDataController {

    @Autowired
    private StockDataService stockDataService;

    @GetMapping("/heatmap")
    public List<StockForHeatmap> heatmap() {
        return this.stockDataService.getHeatmapData();
    }

    @GetMapping("/ohlc")
    public StockOhlcvDto ohlc(@RequestParam String symbol) {
        return this.stockDataService.getOhlc(symbol);
    }


}