package com.bootcamp.demo.project_heatmap_ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HeatmapController {
    @GetMapping("/treemap")
    public String treemap() {
        return "treemap";
    }

    @GetMapping("/candlestick")
    public String candlestick(){
        return "candlestick";
    }
}
