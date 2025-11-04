package com.bootcamp.demo.project_stock_data.entity;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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

@Entity
@Table(name = "stock_ohlc_data")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties({"change", "changesPercentage", "price", "stock", "symbol"})
public class StockOhlcData {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String symbol;
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDateTime date;

  private Double price;
  private Double change;
  private Double changesPercentage;
  private Double highPrice;
  private Double lowPrice;
  private Double openPrice;
  private Double closePrice;
  private Long volume;

  @ManyToOne
  @JoinColumn(name = "symbol", insertable = false, updatable = false)
  private StockSymbol stock;

}
