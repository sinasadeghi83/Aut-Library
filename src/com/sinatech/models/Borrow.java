package com.sinatech.models;

import java.util.Date;

public class Borrow {
    private String userId, userType, paperId, paperType;
    private Date date;

    public Borrow(String userId, String userType, String paperId, String paperType, Date date) {
        this.userId = userId;
        this.userType = userType;
        this.paperId = paperId;
        this.paperType = paperType;
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
