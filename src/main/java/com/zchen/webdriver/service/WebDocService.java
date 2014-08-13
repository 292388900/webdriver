package com.zchen.webdriver.service;

import com.zchen.webdriver.bean.WebDoc;
import com.zchen.webdriver.dao.WebDocDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    public List<WebDoc> list() {
        return webDocDao.list();
    }

    public void save(WebDoc doc){
        sessionFactory.getCurrentSession().save(doc);
    }


    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        WebDoc doc = (WebDoc) session.get(WebDoc.class, id);
        session.delete(doc);
    }

    public void update(WebDoc doc) {
        sessionFactory.getCurrentSession().update(doc);
    }


}
