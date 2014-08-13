package com.zchen.webdriver.controller;

import com.zchen.webdriver.bean.User;
import com.zchen.webdriver.bean.WebDoc;
import com.zchen.webdriver.service.UserService;
import com.zchen.webdriver.service.WebDocService;
import com.zchen.webdriver.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Zhouce Chen
 * @version Aug 13, 2014
 */
@Controller
@RequestMapping("/doc")
public class WebDocController {

    @Autowired
    private WebDocService webDocService;
    @Autowired
    private UserService userService;


    @RequestMapping
    public String index(ModelMap map) {
        return "index";
    }

    @RequestMapping("/list")
    @ResponseBody
    public List list() {
        try {
            return webDocService.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable int id) {

        try {
            webDocService.delete(id);
            return AjaxResult.get().success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/upload")
    @ResponseBody
    public AjaxResult upload(@RequestBody WebDoc doc) {
        try {
            webDocService.save(doc);
            return AjaxResult.get().success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
