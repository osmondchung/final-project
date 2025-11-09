const API = '/api';
let chart = null;

async function apiCall(endpoint, params = '') {
    const url = `${API}${endpoint}${params}`;
    console.log('Fetching:', url);
    try {
        const res = await fetch(url);
        if (!res.ok) throw new Error(`HTTP ${res.status}`);
        const data = await res.json();
        console.log('Response:', data);  
        return data;
    } catch (error) {
        console.error('API Error:', error);
        alert(`Error loading data: ${error.message}. Check if stock-data-app is running on localhost:8080.`);
        return [];
    }
}

document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('searchBtn').addEventListener('click', async () => {
        const symbol = document.getElementById('symbol').value.trim().toUpperCase();
        if (!symbol) {
            alert('Enter a symbol (e.g., AAPL)');
            return;
        }

        const data = await apiCall(`/ohlc?symbol=${symbol}`);
        if (!data || !data.ohlcs || data.ohlcs.length === 0) {
            alert(`No OHLC data for ${symbol}. Try AAPL or GOOGL.`);
            return;
        }

        document.getElementById('chart-container').style.display = 'block';

        setTimeout(() => {
            drawCandlestick(data);
        }, 0);  
    });
});

function drawCandlestick(dto) {
    const ctx = document.getElementById('candlestick').getContext('2d');
    if (chart) chart.destroy();

    const ohlc = dto.ohlcs
        .map(d => ({
            x: new Date(d.date),
            o: d.openPrice,
            h: d.highPrice,
            l: d.lowPrice,
            c: d.closePrice
        }))
        .sort((a, b) => a.x - b.x)  
        .reverse();  

    chart = new Chart(ctx, {
        type: 'candlestick',
        data: {
            datasets: [
                {
                    label: `${dto.symbol} OHLC`,
                    data: ohlc,  
                    barPercentage: 0.01,  
                    categoryPercentage: 0.8,  
                    borderColor: {
                        up: 'green', 
                        down: 'red', 
                        unchanged: 'gray'
                    },
                    color: {
                        up: 'rgba(0, 255, 0, 0.3)', 
                        down: 'rgba(255, 0, 0, 0.3)',
                        unchanged: 'rgba(128, 128, 128, 0.3)'
                    }
                },
                {
                    type: 'line', 
                    label: 'Close Price Trend',
                    data: ohlc.map(d => ({ x: d.x, y: d.c })),
                    borderColor: 'blue',
                    borderWidth: 2,
                    pointRadius: 0,
                    tension: 0.1 
                }
            ]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            scales: {
                x: {
                    type: 'time',
                    time: { unit: 'day' },
                    title: { display: true, text: 'Date' }
                },
                y: {
                    beginAtZero: false,
                    title: { display: true, text: 'Price ($)' }
                }
            },
            plugins: {
                title: { 
                    display: true, 
                    text: `${dto.companyName} - Market Cap: $${(dto.marketCap / 1000).toFixed(2)}B` 
                },
                zoom: {
                    zoom: {
                        wheel: { enabled: true }, 
                        pinch: { enabled: true }, 
                        mode: 'x'
                    },
                    pan: {
                        enabled: true,
                        mode: 'x'
                    }
                }
            }
        }
    });

    chart.update();
    chart.resize();
}