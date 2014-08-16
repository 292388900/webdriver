package com.zchen.webdriver.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;
import java.util.Set;

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

    private Boolean isRemoved = false;

    private List<Folder> folders;

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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    public Folder getParent() {
        return parent;
    }

    public void setParent(Folder parent) {
        this.parent = parent;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String fetchPath() {
        return this.parent.fetchPath() + "/" + this.name;
    }

    @Column(name = "is_removed")
    public Boolean getIsRemoved() {
        return isRemoved;
    }

    public void setIsRemoved(Boolean isRemoved) {
        this.isRemoved = isRemoved;
    }

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "parent_id")
    @Cascade(value = CascadeType.DELETE)
    public List<Folder> getFolders() {
        return folders;
    }

    public void setFolders(List<Folder> folders) {
        this.folders = folders;
    }
}
