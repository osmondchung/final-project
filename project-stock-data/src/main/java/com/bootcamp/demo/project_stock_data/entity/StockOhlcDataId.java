package com.bootcamp.demo.project_stock_data.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class StockOhlcDataId implements Serializable {
    private String symbol;
    private LocalDateTime date;
}