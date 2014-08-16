package com.zchen.webdriver.dao;

import com.zchen.webdriver.bean.Folder;
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
 * @version Aug 16, 2014
 */
@Repository
public class FolderDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List query(Folder folder, boolean isLike) {
        Session session = sessionFactory.getCurrentSession();


        Criteria criteria = session.createCriteria(Folder.class);
        if (StringUtils.isNotEmpty(folder.getName())) {
            if (isLike) {
                criteria.add(Restrictions.like("name", folder.getName(), MatchMode.ANYWHERE));
            } else {
                criteria.add(Restrictions.eq("name", folder.getName()));
            }
        }
        if (folder.getParent() == null) {
            criteria.add(Restrictions.isNull("parent"));
        } else {
            criteria.add(Restrictions.eq("parent", folder.getParent()));
        }
        if (folder.getIsRemoved()) {
            criteria.add(Restrictions.eq("isRemoved", folder.getIsRemoved()));
        }
        criteria.addOrder(Order.asc("name"));
        return criteria.list();
    }
}
