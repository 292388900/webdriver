package com.zchen.webdriver.service;

import com.zchen.webdriver.bean.Folder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Zhouce Chen
 * @version Aug 15, 2014
 */
@Service
@Transactional
public class FolderService {


    @Autowired
    private SessionFactory sessionFactory;

    public void createFolder(Folder folder, int parentId) {
        Session session = sessionFactory.getCurrentSession();

        Folder parent = (Folder) session.get(Folder.class, parentId);
        folder.setParent(parent);
        session.save(folder);
    }

    public void deleteFolder(int id) {
        Session session = sessionFactory.getCurrentSession();
        Folder folder = (Folder) session.get(Folder.class, id);
        session.delete(folder);
    }





}
