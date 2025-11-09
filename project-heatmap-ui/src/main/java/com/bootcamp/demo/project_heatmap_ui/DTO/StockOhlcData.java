package com.bootcamp.demo.project_heatmap_ui.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties(value = {"symbol"})
public class StockOhlcData {
    private String symbol;
    private LocalDate date;
    private Double openPrice;
    private Double highPrice;
    private Double lowPrice;
    private Double closePrice;
    private Long volume;

}