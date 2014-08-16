package com.zchen.webdriver.core;

import com.zchen.webdriver.bean.Folder;
import com.zchen.webdriver.service.FolderService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author Zhouce Chen
 * @version Aug 16, 2014
 */
public class Bootstrap {


    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;

    public void init(){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Folder folder = (Folder) session.get(Folder.class, 0);
        if (folder == null) {
            Folder rootFolder = new Folder();
            rootFolder.setId(0);
            rootFolder.setName("/");
            session.save(rootFolder);
        }
        tx.commit();
        session.close();



    }

}
