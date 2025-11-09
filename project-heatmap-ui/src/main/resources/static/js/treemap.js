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

// 1. Load Heatmap (Fixed: Use built-in d3.treemap)
async function loadTreemap() {
    const data = await apiCall('/heatmap');
    if (!data || data.length === 0) {
        document.getElementById('heatmap').innerHTML = '<p style="text-align:center; color:red;">No data available. Check backend.</p>';
        return;
    }

    const root = {
        name: "stocks",
        children: data.map(d => ({
            name: `${d.symbol}<br>$${d.price?.toFixed(2)} (Δ${d.marketPriceChgPct?.toFixed(2)}%)`,
            value: (d.marketCap || 1e9) / 1e9
        }))
    };

    drawTreemap(root, data);  // Pass data here
}

// 2. Draw Treemap (Fixed: Use d3.treemap() from D3 v7)
function drawTreemap(root, data) {  // Receive data
    const width = 960, height = 600;
    d3.select("#heatmap").html("");

    const svg = d3.select("#heatmap")
        .append("svg")
        .attr("viewBox", `0 0 ${width} ${height}`)
        .style("width", "100%")
        .style("height", "900px");

    const treemap = d3.treemap()
        .size([width, height])
        .padding(2)
        .round(true);

    const hierarchy = d3.hierarchy(root)
        .sum(d => d.value)
        .sort((a, b) => b.value - a.value);

    treemap(hierarchy);

    // Bind original stock data to each node (fixes scoping)
    hierarchy.each(d => {
        if (d.data.name) {
            const symbol = d.data.name.split('<br>')[0];
            d.stock = data.find(s => s.symbol === symbol);
        }
    });

    // Dynamic color scale based on actual data (avoids black)
    const pctValues = data.map(d => d.marketPriceChgPct).filter(v => v != null);
    const minPct = d3.min(pctValues) || -10;
    const maxPct = d3.max(pctValues) || 10;
    const colorScale = d3.scaleSequential(d3.interpolateRdYlGn)
        .domain([minPct * 1.2, maxPct * 1.2]);

    const nodes = svg.selectAll("g")
        .data(hierarchy.leaves())
        .enter()
        .append("g")
        .attr("transform", d => `translate(${d.x0},${d.y0})`);

    nodes.append("rect")
        .attr("width", d => d.x1 - d.x0)
        .attr("height", d => d.y1 - d.y0)
        .attr("fill", d => colorScale(d.stock?.marketPriceChgPct || 0))  // Use d.stock
        .attr("stroke", "#fff")
        .attr("stroke-width", 1)
        .classed("node", true);

    nodes.append("foreignObject")
        .attr("width", d => d.x1 - d.x0)
        .attr("height", d => d.y1 - d.y0)
        .append("xhtml:div")
        .style("width", "100%")
        .style("height", "100%")
        .style("display", "flex")
        .style("flex-direction", "column")
        .style("align-items", "center")
        .style("justify-content", "center")
        .style("font-size", d => Math.min(d.x1 - d.x0, d.y1 - d.y0) / 15 + "px")
        .style("color", d => Math.abs(d.stock?.marketPriceChgPct || 0) > 3 ? "white" : "black")  // Contrast
        .style("text-align", "center")
        .style("padding", "2px")
        .style("overflow", "hidden")
        .style("word-break", "break-all")
        .html(d => d.data.name);  // Supports <br> breaks

    // Hover effect (unchanged)
    nodes.on("mouseover", function(event, d) {
        d3.select(this).select("rect").style("stroke-width", 3);
    }).on("mouseout", function() {
        d3.select(this).select("rect").style("stroke-width", 1);
    });
}
loadTreemap();
setInterval(loadTreemap, 120000);