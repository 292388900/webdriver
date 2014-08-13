package com.zchen.webdriver.bean;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Set;

/**
 * @author Zhouce Chen
 * @version Aug 13, 2014
 */
@Entity
public class User {

    private int id;

    private String username;

    private String password;

    private Set<WebDoc> docs;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(mappedBy = "user")
    public Set<WebDoc> getDocs() {
        return docs;
    }

    public void setDocs(Set<WebDoc> docs) {
        this.docs = docs;
    }
}
