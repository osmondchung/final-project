package com.bootcamp.demo.project_stock_data.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.bootcamp.demo.project_stock_data.entity.StockOhlcData;
import com.bootcamp.demo.project_stock_data.entity.StockSymbol;

@Repository
public interface StockOhlcDataRepository extends JpaRepository<StockOhlcData, String>{
  @Query(value = "select * from stock_ohlc_data s where s.symbol = :symbol", nativeQuery = true)
  List<StockOhlcData> findBySymbol(@Param(value = "symbol") String symbol);
}