package com.bootcamp.demo.project_stock_data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "stock_profile")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @EqualsAndHashCode
@Builder
public class StockProfile {
  @Id
  private String symbol;
  private String industry;
  private Double marketCap;
  @Column(length = 1000)
  private String logo;
  @Column(length = 1000)
  private String companyName;
  private Double shares;
  private String ipo;
  private String weburl;

  @OneToOne
  @MapsId
  @JoinColumn(name = "symbol", referencedColumnName = "symbol")
  private StockSymbol stock;
}
