const API = '/api';
let chart = null;

// Debug: Log API responses
async function apiCall(endpoint, params = '') {
    const url = `${API}${endpoint}${params}`;
    console.log('Fetching:', url);  // DEBUG
    try {
        const res = await fetch(url);
        if (!res.ok) throw new Error(`HTTP ${res.status}`);
        const data = await res.json();
        console.log('Response:', data);  // DEBUG
        return data;
    } catch (error) {
        console.error('API Error:', error);
        alert(`Error loading data: ${error.message}. Check if stock-data-app is running on localhost:8080.`);
        return [];
    }
}

// 3. Search & Candlestick (Fixed: Parse dates, error handling)
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

        // Delay draw to ensure container is visible and sized
        setTimeout(() => {
            drawCandlestick(data);
        }, 0);  // 0ms pushes to next event loop tick
    });
});

// Manually register (fixes "not registered" error)
//Chart.register(CandlestickController, CandlestickElement, OhlcController, OhlcElement);

function drawCandlestick(dto) {
    const ctx = document.getElementById('candlestick').getContext('2d');
    if (chart) chart.destroy();

    // Sort OHLC old to new, then reverse for newest first (recent trends on right)
    const ohlc = dto.ohlcs
        .map(d => ({
            x: new Date(d.date),
            o: d.openPrice,
            h: d.highPrice,
            l: d.lowPrice,
            c: d.closePrice
        }))
        .sort((a, b) => a.x - b.x)  // Old to new
        .reverse();  // Newest first for better trend view

    // Optional: Limit to last 365 days for initial overview (uncomment if needed)
    // const lastYear = new Date(Math.max(...ohlc.map(d => d.x.getTime())) - 365 * 24 * 60 * 60 * 1000);
    // const recentOhlc = ohlc.filter(d => d.x >= lastYear);

    chart = new Chart(ctx, {
        type: 'candlestick',
        data: {
            datasets: [
                {
                    label: `${dto.symbol} OHLC`,
                    data: ohlc,  // or recentOhlc if limiting
                    barPercentage: 0.01,  // Thin bars (50% of category space)
                    categoryPercentage: 0.8,  // Group width (adjust gaps)
                    borderColor: {
                        up: 'green',  // Bullish border
                        down: 'red',  // Bearish border
                        unchanged: 'gray'
                    },
                    color: {
                        up: 'rgba(0, 255, 0, 0.3)',  // Semi-transparent fill
                        down: 'rgba(255, 0, 0, 0.3)',
                        unchanged: 'rgba(128, 128, 128, 0.3)'
                    }
                },
                {
                    type: 'line',  // Trend line for close prices
                    label: 'Close Price Trend',
                    data: ohlc.map(d => ({ x: d.x, y: d.c })),
                    borderColor: 'blue',
                    borderWidth: 2,
                    pointRadius: 0,  // No points, just line
                    tension: 0.1  // Slight curve for smooth trend
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
                    text: `${dto.companyName} - Market Cap: $${(dto.marketCap / 1000).toFixed(2)}B`  // Fix: /1000 for billions
                },
                zoom: {
                    zoom: {
                        wheel: { enabled: true },  // Mouse wheel zoom
                        pinch: { enabled: true },  // Touch pinch
                        mode: 'x'  // Zoom on x-axis only
                    },
                    pan: {
                        enabled: true,
                        mode: 'x'  // Pan on x-axis only
                    }
                }
            }
        }
    });

    chart.update();
    chart.resize();
}