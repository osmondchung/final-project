package com.bootcamp.demo.project_stock_data.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class CompanyData {
  private String symbol;
  private String industry;
  private Double marketCap;
  private String logo;
  private String companyName;
  private Double shares;
  private String ipo;
  private String weburl;
}

// stockId
/*             {
        "symbol": "StockSymbol(symbol%3DMMM)",
        "industry": null,
        "marketCap": null,
        "logo": null,
        "companyName": null,
        "shares": null,
        "ipo": null,
        "weburl": null,
        "stock": null
    },*/