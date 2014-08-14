package com.zchen.webdriver.dao;

import com.zchen.webdriver.bean.WebDoc;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Zhouce Chen
 * @version Aug 13, 2014
 */
@Repository
public class WebDocDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<WebDoc> list() {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(WebDoc.class);
        criteria.addOrder(Order.desc("updateTime"));
        return criteria.list();
    }

}
