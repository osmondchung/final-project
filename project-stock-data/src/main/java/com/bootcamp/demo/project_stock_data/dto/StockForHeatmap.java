package com.bootcamp.demo.project_stock_data.dto;

import java.time.LocalDateTime;
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
public class StockForHeatmap {
  private Long stockId;
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
