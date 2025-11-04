package com.bootcamp.demo.project_stock_data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "stock_symbol_list")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class StockSymbol {

  @Id
  @Column(name = "symbol")
  private String symbol;
}

