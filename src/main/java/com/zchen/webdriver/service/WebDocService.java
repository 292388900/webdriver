package com.zchen.webdriver.service;

import com.zchen.webdriver.bean.WebDoc;
import com.zchen.webdriver.core.Configuration;
import com.zchen.webdriver.dao.WebDocDao;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Zhouce Chen
 * @version Aug 13, 2014
 */
@Service
@Transactional
public class WebDocService {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private WebDocDao webDocDao;
    @Autowired
    private Configuration configuration;


    public List<WebDoc> list() {
        return webDocDao.list();
    }

    public void save(WebDoc doc, MultipartFile uploadFile) throws IOException {
        //persist file to disk
        String rootPath = configuration.getRootPath();
        String path = doc.getPath();
        String fileSuffix = FilenameUtils.getExtension(uploadFile.getOriginalFilename());
        String fileName = FilenameUtils.getBaseName(doc.getName()) + "." + fileSuffix;
        File destFile = FileUtils.getFile(rootPath, path, fileName);
        uploadFile.transferTo(destFile);

        //save to database
        doc.setName(fileName);
        doc.setSuffix(fileSuffix);
        sessionFactory.getCurrentSession().save(doc);
    }


    public void delete(int id) throws IOException {
        Session session = sessionFactory.getCurrentSession();
        WebDoc doc = (WebDoc) session.get(WebDoc.class, id);
        //delete from disk
        File file = FileUtils.getFile(configuration.getRootPath(), doc.getPath(), doc.getName());
        FileUtils.deleteQuietly(file);

        //delete from database
        session.delete(doc);
    }

    public void update(WebDoc doc) {
        sessionFactory.getCurrentSession().update(doc);
    }



}
