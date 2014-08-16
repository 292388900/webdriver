package com.zchen.webdriver.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import java.util.Date;

/**
 * @author Zhouce Chen
 * @version Aug 13, 2014
 */
@Entity//Fixme: use hibernate entity
public class WebDoc {

    private int id;

    private String name;

    private String suffix;

    private long size;

    private User user;

    private Date updateTime;

    private Boolean isRemoved = false;

    private String serialNum;

    private Folder folder;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(nullable = false)
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

    @ManyToOne(fetch = FetchType.LAZY)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "update_time")
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Column(name = "is_removed")
    public Boolean getIsRemoved() {
        return isRemoved;
    }

    public void setIsRemoved(Boolean isRemoved) {
        this.isRemoved = isRemoved;
    }

    @Column(name = "serial_num")
    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }
}
