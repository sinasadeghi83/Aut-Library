package com.sinatech.models;

import com.sinatech.components.DatabaseManager;

import java.util.ArrayList;
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
    //Thesis' id -> Thesis' object
    private HashMap<String, Thesis> theses;
    //Book id -> Borrow object
    private HashMap<String, ArrayList<Borrow>> borrows;

    public Library(String id, String name, Date foundDate, int tableCount, String address) {
        this.id = id;
        this.name = name;
        this.foundDate = foundDate;
        this.tableCount = tableCount;
        this.address = address;
        this.books = new HashMap<>();
        this.theses = new HashMap<>();
        this.borrows = new HashMap<>();
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

    public void removeBook(String id) {
        this.books.remove(id);
    }

    public void addThesis(Thesis thesis){
        this.theses.put(thesis.getId(), thesis);
    }

    public Thesis getThesis(String id){
        return this.theses.get(id);
    }

    public void removeThesis(String id) {
        this.theses.remove(id);
    }

    public boolean borrowBook(Borrow borrow, Object userObj){
        Book book = this.getBook(borrow.getPaperId());
        ArrayList<Borrow> borrowList = borrows.computeIfAbsent(borrow.getPaperId(), k -> new ArrayList<>());
        if(!checkUserAbleToBorrow(userObj) || borrowList.size() == book.getCopyCount()){
            return false;
        }
        increaseUserBorrow(userObj);
        borrowList.add(borrow);
        borrows.put(borrow.getPaperId(), borrowList);
        return true;
    }

    public boolean borrowThesis(Borrow borrow, Object userObj) {
        ArrayList<Borrow> borrowList = borrows.computeIfAbsent(borrow.getPaperId(), k -> new ArrayList<>());
        if(!checkUserAbleToBorrow(userObj) || borrowList.size() == 1){
            return false;
        }
        increaseUserBorrow(userObj);
        borrowList.add(borrow);
        borrows.put(borrow.getPaperId(), borrowList);
        return true;
    }

    private boolean checkUserAbleToBorrow(Object userObj){
        if(userObj instanceof Staff staff){
            return staff.getBorrowCount() <= 5;
        }else if(userObj instanceof Student student){
            return student.getBorrowedCount() <= 3;
        }
        return false;
    }

    private void increaseUserBorrow(Object userObj) {
        if(userObj instanceof Staff staff){
            staff.setBorrowCount(staff.getBorrowCount()+1);
        } else if (userObj instanceof Student student) {
            student.setBorrowedCount(student.getBorrowedCount()+1);
        }
    }
}
