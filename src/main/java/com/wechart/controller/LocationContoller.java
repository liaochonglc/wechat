package com.wechart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/location")
public class LocationContoller {
    @RequestMapping("/map")
    public String map(){
        return "map";
    }
}
