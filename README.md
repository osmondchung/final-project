# Real-time stock data visualization
Applied Spring Boot framework to create APIs that retrieves the historical stock data and the real-time S&P 500 stock price.

## data-provider-app
- Get real-time data: stock prices & OHLC from external APIs

## python scripts
- Insert stocks' OHLC data into database
- Extract the stock symbols from CSV file

## stock-data-app
- Prepare data for treemap and candlestick chart
- Call data-provider-app service to get the real-time stock prices for every 2 minutes

## heatmap-ui-app
- Stock price Treemap and OHLC candlestick chart visualisation

## Data source
- Finnhub API
- Yahoo finance API

## Skills
- Backend and Frontend
- Java, Spring Boot, Spring Web MVC, Spring Data JPA, Hibernate
- Python
- SQL
- Docker

## Project Limitation
- Limited requestes for free API (30 stocks data per minute)
### Possible solution
- Pay for the premium API to unlock the full data access

## To do
- Redis for storing company profile data


<img width="1710" height="1107" alt="螢幕截圖 2025-11-09 下午6 35 44" src="https://github.com/user-attachments/assets/69517259-0e0f-44a3-8bb7-543636d19263" />
<img width="1710" height="1107" alt="螢幕截圖 2025-11-09 下午6 36 01" src="https://github.com/user-attachments/assets/66781f15-580c-46f2-9f3c-554a4ff41348" />
<img width="1651" height="1057" alt="螢幕截圖 2025-11-13 下午4 39 18" src="https://github.com/user-attachments/assets/7b76e8d3-cf87-4c0d-8b2e-a17707d4345e" />
<img width="1005" height="499" alt="螢幕截圖 2025-11-09 下午8 49 54" src="https://github.com/user-attachments/assets/e92978bd-b24d-4bf6-a78d-0956209e2a0d" />
