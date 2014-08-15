package com.zchen.webdriver.bean;

import javax.persistence.*;

/**
 * @author Zhouce Chen
 * @version Aug 15, 2014
 */
@Entity
public class Folder {

    private int id;

    private String name;

    private Folder parent;

    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    public Folder getParent() {
        return parent;
    }

    public void setParent(Folder parent) {
        this.parent = parent;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}