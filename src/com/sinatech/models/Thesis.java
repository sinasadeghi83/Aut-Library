package com.sinatech.models;

import java.util.Date;

public class Thesis {
    private String id, title, studName, profName;
    private Date defenseDate;
    private String catId, libId;

    public Thesis(String id, String title, String studName, String profName, Date defenseDate, String catId, String libId) {
        this.id = id;
        this.title = title;
        this.studName = studName;
        this.profName = profName;
        this.defenseDate = defenseDate;
        this.catId = catId;
        this.libId = libId;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudName() {
        return studName;
    }

    public void setStudName(String studName) {
        this.studName = studName;
    }

    public String getProfName() {
        return profName;
    }

    public void setProfName(String profName) {
        this.profName = profName;
    }

    public Date getDefenseDate() {
        return defenseDate;
    }

    public void setDefenseDate(Date defenseDate) {
        this.defenseDate = defenseDate;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getLibId() {
        return libId;
    }
}
