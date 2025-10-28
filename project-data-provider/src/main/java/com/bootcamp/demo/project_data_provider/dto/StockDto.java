package com.bootcamp.demo.project_data_provider.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StockDto {
    private String symbol;
    private Double price;
    private Double change;
    private Double changesPercentage;
    private Double highPrice;
    private Double lowPrice;
    private Double openPrice;
    private Double previousPrice;
}
