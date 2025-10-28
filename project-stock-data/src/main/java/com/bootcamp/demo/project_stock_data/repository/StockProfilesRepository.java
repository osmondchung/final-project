package com.bootcamp.demo.project_stock_data.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.bootcamp.demo.project_stock_data.entity.StockSymbol;
import com.bootcamp.demo.project_stock_data.entity.StockProfile;

@Repository
public interface StockProfilesRepository extends JpaRepository<StockProfile, String>{
  @Query(value = "select * from stock_profile", nativeQuery = true)
  List<StockProfile> getStockProfile();
}
