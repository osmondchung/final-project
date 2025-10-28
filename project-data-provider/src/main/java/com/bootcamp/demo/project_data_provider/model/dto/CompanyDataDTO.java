package com.bootcamp.demo.project_data_provider.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDataDTO {
  @JsonProperty("ticker")
  private String symbol;
  @JsonProperty("finnhubIndustry")
  private String industry;
  @JsonProperty("marketCapitalization")
  private Double marketCap;
  private String logo;
  @JsonProperty("name")
  private String companyName;
  @JsonProperty("shareOutstanding")
  private Double shares;
  @JsonProperty("ipo")
  private String ipo;
  @JsonProperty("weburl")
  private String weburl;
}
