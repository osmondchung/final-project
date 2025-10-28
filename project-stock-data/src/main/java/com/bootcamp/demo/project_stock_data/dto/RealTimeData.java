package com.bootcamp.demo.project_stock_data.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class RealTimeData {
  private String symbol;
  private Double price;
  private Double change;
  private Double changesPercentage;
  private Double highPrice;
  private Double lowPrice;
  private Double openPrice;
  private Double previousPrice;
}
