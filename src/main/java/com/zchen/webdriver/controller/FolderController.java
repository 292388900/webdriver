package com.zchen.webdriver.controller;

import com.zchen.webdriver.bean.Folder;
import com.zchen.webdriver.service.FolderService;
import com.zchen.webdriver.utils.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zhouce Chen
 * @version Aug 15, 2014
 */
@Controller
@RequestMapping("/folder")
public class FolderController {

    private Logger logger = LoggerFactory.getLogger(FolderController.class);

    @Autowired
    private FolderService folderService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult createFolder(@RequestBody Integer parentId) {
        try {
//            folderService.createFolder(folder, parentId);
            return AjaxResult.get().success();
        } catch (Exception e) {
            logger.error("create folder failed.", e);
            return AjaxResult.get().failure();
        }
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public AjaxResult deleteFolder(@PathVariable int id) {
        try {
            folderService.deleteFolder(id);
            return AjaxResult.get().success();
        } catch (Exception e) {
            logger.error("delete folder failed.", e);
            return AjaxResult.get().failure();
        }
    }


}
