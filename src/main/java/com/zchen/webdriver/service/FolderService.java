package com.zchen.webdriver.service;

import com.zchen.webdriver.bean.Folder;
import com.zchen.webdriver.dao.FolderDao;
import org.apache.commons.io.FileExistsException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * @author Zhouce Chen
 * @version Aug 15, 2014
 */
@Service
@Transactional
public class FolderService {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private FolderDao folderDao;

    public List list(Folder folder, int parentId){
        Folder parent = (Folder) sessionFactory.getCurrentSession().load(Folder.class, parentId);
        folder.setParent(parent);
        return folderDao.query(folder, true);
    }

    public void create(Folder folder, int parentId) throws IOException {
        Session session = sessionFactory.getCurrentSession();
        Folder parent = (Folder) session.load(Folder.class, parentId);
        folder.setParent(parent);
        if (folderDao.query(folder, false).size() > 0) {
            throw new FileExistsException("Folder ["+ folder.getName() +"] has exists.");
        }
        session.save(folder);
    }

    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Folder folder = (Folder) session.get(Folder.class, id);
        session.delete(folder);
    }



    public void trash(int id) {
        Session session = sessionFactory.getCurrentSession();
        Folder folder = (Folder) session.get(Folder.class, id);
        folder.setIsRemoved(true);
    }


}
