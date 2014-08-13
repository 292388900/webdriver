package com.zchen.webdriver.service;

import com.zchen.webdriver.bean.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Zhouce Chen on 2014/8/13.
 */
@Service
@Transactional
public class UserService {
    @Autowired
    private SessionFactory sessionFactory;

    public User getUserByName(String name) {
        return (User) sessionFactory.getCurrentSession()
                 .createQuery("from User u where u.username = ?")
                 .setString(0, name).uniqueResult();
    }

    public void save(User user) {
        sessionFactory.getCurrentSession().save(user);
    }


}
