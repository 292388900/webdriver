package com.zchen.webdriver.dao;

import com.zchen.webdriver.bean.WebDoc;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
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

    public List<WebDoc> query(WebDoc doc) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(WebDoc.class);
        if (StringUtils.isNotEmpty(doc.getName())) {
            criteria.add(Restrictions.like("name", doc.getName(), MatchMode.ANYWHERE));
        }
        if (doc.getFolder() != null) {
            criteria.add(Restrictions.eq("folder", doc.getFolder()));
        } else {
            criteria.add(Restrictions.isNull("folder"));
        }
        if (StringUtils.isNotEmpty(doc.getSuffix())) {
            criteria.add(Restrictions.eq("suffix", doc.getSuffix()));
        }
        if (doc.getUser() != null) {
            criteria.add(Restrictions.eq("user",  doc.getUser()));
        }
        if (doc.getIsRemoved() != null) {
            criteria.add(Restrictions.eq("isRemoved", doc.getIsRemoved()));
        }
        return criteria.list();
    }

}
