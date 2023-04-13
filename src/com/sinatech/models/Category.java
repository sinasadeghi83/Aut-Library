package com.sinatech.models;

import java.util.HashSet;

public class Category {
    private String id;
    private String name;

    //A collection of Book and thesis ids which belong to this category
    private HashSet<String> bookIds;

    public Category(String id, String name) {
        this.id = id;
        this.name = name;
        this.bookIds = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addPaperId(String id){
        bookIds.add(id);
    }
}
