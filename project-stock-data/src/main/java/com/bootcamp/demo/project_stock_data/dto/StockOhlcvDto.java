package com.bootcamp.demo.project_stock_data.dto;

import java.util.List;
import com.bootcamp.demo.project_stock_data.entity.StockOhlcData;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
public class StockOhlcvDto {
  //private String stockId;
  private String symbol;
  private String companyName;
  private Double marketCap;
  private String industry;
  private Double shareOutstanding;
  private String logo;
  @Setter
  @Getter
  @JsonProperty("ohlcs")
  private List<StockOhlcData> Ohlcs;

  
}
