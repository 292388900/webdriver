package com.zchen.webdriver.controller;

import com.zchen.webdriver.bean.Folder;
import com.zchen.webdriver.service.FolderService;
import com.zchen.webdriver.utils.AjaxResult;
import org.apache.commons.io.FileExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

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
    public AjaxResult create(Folder folder, Integer parentId) {
        try {
            folderService.create(folder, parentId);
            return AjaxResult.get().success().setData(folder);
        } catch (FileExistsException e) {
            logger.error(e.getMessage());
            return AjaxResult.get().failure().setMessage(e.getMessage());
        } catch (IOException e) {
            logger.error("create folder failed.", e);
            return AjaxResult.get().failure().setMessage(e.getMessage());
        }
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable int id) {
        try {
            folderService.delete(id);
            return AjaxResult.get().success();
        } catch (Exception e) {
            logger.error("delete folder failed.", e);
            return AjaxResult.get().failure();
        }
    }

    @RequestMapping("/trash/{id}")
    @ResponseBody
    public AjaxResult trash(@PathVariable int id) {
        try {
            folderService.trash(id);
            return AjaxResult.get().success();
        } catch (Exception e) {
            logger.error("delete folder failed.", e);
            return AjaxResult.get().failure();
        }
    }
}
