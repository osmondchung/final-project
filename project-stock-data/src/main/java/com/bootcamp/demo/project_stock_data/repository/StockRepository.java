package com.bootcamp.demo.project_stock_data.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.bootcamp.demo.project_stock_data.entity.StockSymbol;

@Repository
public interface StockRepository extends JpaRepository<StockSymbol, String>{
  @Query(value = "select * from stock_symbol_list", nativeQuery = true)
  List<StockSymbol> getStockSymbol();
  
}