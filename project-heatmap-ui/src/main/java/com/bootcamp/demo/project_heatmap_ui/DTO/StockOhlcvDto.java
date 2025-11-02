package com.bootcamp.demo.project_heatmap_ui.DTO;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class StockOhlcvDto {
    private String symbol;
    private String companyName;
    private Double marketCap;
    private String industry;
    private Double shareOutstanding;
    private String logo;

    @Getter
    @JsonProperty("ohlcs")
    private List<StockOhlcData> ohlcs;
}