package com.sinatech.models;

import java.util.Date;

public class Book {

    public static final int COPY_COUNT_NULL = -1;
    private String id;
    private String libId;
    private String title;
    private String author;
    //Publication
    private String pub;
    private Date printDate;
    private int copyCount;
    //Category's id
    private String catId;

    public String getId() {
        return id;
    }

    public String getLibId() {
        return libId;
    }

    public Book(String id, String title, String author, String pub, Date printDate, int copyCount, String catId, String libId) {
        this.id = id;
        this.libId = libId;
        this.title = title;
        this.author = author;
        this.pub = pub;
        this.printDate = printDate;
        this.copyCount = copyCount;
        this.catId = catId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPub() {
        return pub;
    }

    public void setPub(String pub) {
        this.pub = pub;
    }

    public Date getPrintDate() {
        return printDate;
    }

    public void setPrintDate(Date printDate) {
        this.printDate = printDate;
    }

    public int getCopyCount() {
        return copyCount;
    }

    public void setCopyCount(int copyCount) {
        this.copyCount = copyCount;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public void update(Book editBook) {
        if(editBook.getTitle() != null){
            this.setTitle(editBook.getTitle());
        }

        if(editBook.getAuthor() != null){
            this.setAuthor(editBook.getAuthor());
        }

        if(editBook.getPub() != null){
            this.setPub(editBook.getPub());
        }

        if(editBook.getPrintDate() != null){
            this.setPrintDate(editBook.getPrintDate());
        }

        if(editBook.getCopyCount() != COPY_COUNT_NULL){
            this.setCopyCount(editBook.getCopyCount());
        }

        if(editBook.getCatId() != null){
            this.setCatId(editBook.getCatId());
        }
    }

    @Override
    public String toString() {
        return title + "|" + author + "|" + pub;
    }
}
