package com.bootcamp.demo.project_heatmap_ui.DTO;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class StockForHeatmap {
    private String symbol;
    private String name;
    private Double price;
    private Double marketPriceChg;
    private Double marketPriceChgPct;
    private Double marketCap;
    private String industry;
    private String ipoDate;
    private String webUrl;
    private Double shareOutstanding;
    private String logo;
}
