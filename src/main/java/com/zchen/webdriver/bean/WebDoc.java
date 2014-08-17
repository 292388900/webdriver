package com.zchen.webdriver.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;

/**
 * @author Zhouce Chen
 * @version Aug 13, 2014
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WebDoc {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    private String suffix;

    private long size;

    @Column(name = "update_time")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updateTime;

    @Column(name = "is_removed")
    private Boolean isRemoved = false;

    @Column(name = "serial_num")
    private String serialNum;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Folder folder;


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

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getIsRemoved() {
        return isRemoved;
    }

    public void setIsRemoved(Boolean isRemoved) {
        this.isRemoved = isRemoved;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }
}
