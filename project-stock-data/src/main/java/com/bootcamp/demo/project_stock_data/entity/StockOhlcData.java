package com.bootcamp.demo.project_stock_data.entity;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@IdClass(StockOhlcDataId.class)
@Entity
@Table(name = "stock_ohlc_data")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @EqualsAndHashCode
@JsonIgnoreProperties({"change", "changesPercentage", "price", "stock", "symbol"})
public class StockOhlcData {
  @Id
  private String symbol;
  private Double price;
  private Double change;
  private Double changesPercentage;
  private Double highPrice;
  private Double lowPrice;
  private Double openPrice;
  private Double closePrice;
  private Long volume;
  @Id
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDateTime date;

  @ManyToOne
  @MapsId
  @JoinColumn(name = "symbol", referencedColumnName = "symbol")
  private StockSymbol stock;

}
