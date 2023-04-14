package com.sinatech.models;

import com.sinatech.components.DatabaseManager;

import java.util.Date;

public class Borrow {
    private String userId, paperId, libId;
    private boolean isStaff, isBook;
    private Date date;

    public Borrow(String userId, boolean isStaff, String paperId, boolean isBook, Date date, String libId) {
        this.userId = userId;
        this.isStaff = isStaff;
        this.paperId = paperId;
        this.isBook = isBook;
        this.date = date;
        this.libId = libId;
    }

    public String getLibId() {
        return libId;
    }

    public void setLibId(String libId) {
        this.libId = libId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isStaff() {
        return isStaff;
    }

    public void setIsStaff() {
        this.isStaff = DatabaseManager.getStaff(this.userId) != null;;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public boolean isBook(){
        return this.isBook;
    }

    public void setIsBook() {
        this.isBook = DatabaseManager.getLibrary(this.getLibId()).getBook(this.getPaperId()) != null;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
