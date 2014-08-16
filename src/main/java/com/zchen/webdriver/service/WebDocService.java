package com.zchen.webdriver.service;

import com.zchen.webdriver.bean.Folder;
import com.zchen.webdriver.bean.WebDoc;
import com.zchen.webdriver.core.Configuration;
import com.zchen.webdriver.dao.WebDocDao;
import org.apache.commons.io.FileExistsException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
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


    public List<WebDoc> list(WebDoc doc, int folderId) {
        Folder folder = (Folder) sessionFactory.getCurrentSession().get(Folder.class, folderId);
        doc.setFolder(folder);
        return webDocDao.query(doc, false);
    }

    public void save(WebDoc doc, int folderId ,MultipartFile uploadFile) throws IOException {
        Session session = sessionFactory.getCurrentSession();

        String rootPath = configuration.getRootPath();
        String fileSuffix = FilenameUtils.getExtension(uploadFile.getOriginalFilename());
        String fileName = FilenameUtils.getBaseName(doc.getName()) + "." + fileSuffix;
        String serialNum = RandomStringUtils.randomAlphanumeric(30);

        if (webDocDao.query(doc, true).size() > 0) {
            throw new FileExistsException("Document ["+ fileName +"] has exists.");
        }

        //persist file to disk
        File destFile = FileUtils.getFile(rootPath, serialNum);
        uploadFile.transferTo(destFile);

        //save to database
        doc.setName(fileName);
        doc.setSuffix(fileSuffix);
        doc.setSize(uploadFile.getSize());
        doc.setUpdateTime(new Date());
        doc.setSerialNum(serialNum);
        doc.setFolder((Folder) session.get(Folder.class, folderId));
        session.save(doc);
    }


    public void delete(int id) throws IOException {
        Session session = sessionFactory.getCurrentSession();
        WebDoc doc = (WebDoc) session.get(WebDoc.class, id);
        //delete from disk
        File file = FileUtils.getFile(configuration.getRootPath(), doc.getFolder().fetchPath(), doc.getName());
        FileUtils.deleteQuietly(file);

        //delete from database
        session.delete(doc);
    }

    public void update(WebDoc doc) {
        sessionFactory.getCurrentSession().update(doc);
    }


    public void trash(int id) {
        Session session = sessionFactory.getCurrentSession();
        WebDoc doc = (WebDoc) session.get(WebDoc.class, id);
        doc.setIsRemoved(true);
    }

}
