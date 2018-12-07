package com.wechart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//定位
//加一个
//12
@Controller
@RequestMapping("/location")
public class LocationContoller {
    @RequestMapping("/map")
    public String map(){
        return "map";
    }
}
