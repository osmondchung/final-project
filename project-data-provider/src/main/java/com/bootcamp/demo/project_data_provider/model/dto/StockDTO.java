package com.bootcamp.demo.project_data_provider.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockDTO {
    private String symbol;
    @JsonProperty("c")
    private Double price;
    @JsonProperty("d")
    private Double change;
    @JsonProperty("dp")
    private Double changesPercentage;
    @JsonProperty("h")
    private Double highPrice;
    @JsonProperty("l")
    private Double lowPrice;
    @JsonProperty("o")
    private Double openPrice;
    @JsonProperty("pc")
    private Double previousPrice;

}


