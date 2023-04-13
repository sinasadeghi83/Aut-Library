package com.sinatech.models;

import java.util.Date;

public class Library {
    private String id;
    private String name;
    private Date foundDate;
    private int tableCount;
    private String address;

    public Library(String id, String name, Date foundDate, int tableCount, String address) {
        this.id = id;
        this.name = name;
        this.foundDate = foundDate;
        this.tableCount = tableCount;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getFoundDate() {
        return foundDate;
    }

    public int getTableCount() {
        return tableCount;
    }

    public String getAddress() {
        return address;
    }
}
