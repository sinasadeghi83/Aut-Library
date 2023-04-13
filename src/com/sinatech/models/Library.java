package com.sinatech.models;

import java.util.Date;
import java.util.HashMap;

public class Library {
    private String id;
    private String name;
    private Date foundDate;
    private int tableCount;
    private String address;
    // Book's id -> Book's object
    private HashMap<String, Book> books;

    public Library(String id, String name, Date foundDate, int tableCount, String address) {
        this.id = id;
        this.name = name;
        this.foundDate = foundDate;
        this.tableCount = tableCount;
        this.address = address;
        this.books = new HashMap<>();
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

    //Overwrites if there is already a book with this book's id
    public void addBook(Book book){
        this.books.put(book.getId(), book);
    }

    public Book getBook(String bookId){
        return this.books.get(bookId);
    }
}
