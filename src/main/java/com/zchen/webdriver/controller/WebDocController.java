package com.zchen.webdriver.controller;

import com.zchen.webdriver.bean.Folder;
import com.zchen.webdriver.bean.WebDoc;
import com.zchen.webdriver.service.FolderService;
import com.zchen.webdriver.service.UserService;
import com.zchen.webdriver.service.WebDocService;
import com.zchen.webdriver.utils.AjaxResult;
import org.apache.commons.io.FileExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Zhouce Chen
 * @version Aug 13, 2014
 */
@Controller
@RequestMapping("/doc")
public class WebDocController {

    private Logger logger = LoggerFactory.getLogger(WebDocController.class);

    @Autowired
    private WebDocService webDocService;
    @Autowired
    private UserService userService;
    @Autowired
    private FolderService folderService;


    @RequestMapping
    public String index(ModelMap map) {
        return "index";
    }

    @RequestMapping("/list")
    @ResponseBody
    public List list(WebDoc doc, Folder folder, int parentId) {
        try {
            List folders = folderService.list(folder, parentId);
            List docs = webDocService.list(doc, parentId);
            folders.addAll(docs);
            return folders;
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

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult upload(WebDoc doc, Integer folderId ,MultipartFile file) {
        try {
            webDocService.save(doc, folderId ,file);
            return AjaxResult.get().success().setData(doc);
        } catch (FileExistsException e) {
            logger.error(e.getMessage());
            return AjaxResult.get().failure().setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("Upload failed.", e);
            return AjaxResult.get().failure().setMessage(e.getMessage());
        }
    }

    @RequestMapping("/trash/{id}")
    @ResponseBody
    public AjaxResult trash(@PathVariable int id) {

        try {
            webDocService.trash(id);
            return AjaxResult.get().success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
