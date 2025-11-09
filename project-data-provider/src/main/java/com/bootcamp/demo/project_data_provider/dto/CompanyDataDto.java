package com.bootcamp.demo.project_data_provider.dto;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class CompanyDataDto {
  private String symbol;
  private String industry;
  private Double marketCap;
  private String logo;
  private String companyName;
  private Double shares;
  private String ipo;
  private String weburl;
}
