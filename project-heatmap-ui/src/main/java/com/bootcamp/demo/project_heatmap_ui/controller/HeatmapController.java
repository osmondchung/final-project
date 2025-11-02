package com.bootcamp.demo.project_heatmap_ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HeatmapController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
