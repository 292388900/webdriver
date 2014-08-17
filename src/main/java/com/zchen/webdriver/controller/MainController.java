package com.zchen.webdriver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Zhouce Chen
 * @version Aug 17, 2014
 */
@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping
    public String index(){
        return "index";
    }

}
