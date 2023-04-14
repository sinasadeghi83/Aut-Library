package com.sinatech.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class Library {

    public static final int RETURN_FALSE = -1;
    private String id;
    private String name;
    private Date foundDate;
    private int tableCount;
    private String address;
    // Book's id -> Book's object
    private HashMap<String, Book> books;
    //Thesis' id -> Thesis' object
    private HashMap<String, Thesis> theses;
    //Paper id -> Borrow objects
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

    public boolean removeBook(String id) {
        ArrayList<Borrow> borrowList = borrows.get(id);
        if(borrowList != null && borrowList.size() > 0) {
            return false;
        }
        this.books.remove(id);
        return true;
    }

    public void addThesis(Thesis thesis){
        this.theses.put(thesis.getId(), thesis);
    }

    public Thesis getThesis(String id){
        return this.theses.get(id);
    }

    public boolean removeThesis(String id) {
        ArrayList<Borrow> borrowList = borrows.get(id);
        if(borrowList != null && borrowList.size() > 0) {
            return false;
        }
        this.theses.remove(id);
        return true;
    }

    public boolean borrowBook(Borrow borrow, Object userObj){
        Book book = this.getBook(borrow.getPaperId());
        ArrayList<Borrow> borrowList = borrows.get(borrow.getPaperId());
        if(borrowList == null){
            borrowList = new ArrayList<>();
        }
        if(!checkUserAbleToBorrow(userObj, borrow.getPaperId()) || borrowList.size() == book.getCopyCount()){
            return false;
        }
        increaseUserBorrow(userObj);
        borrowList.add(borrow);
        borrows.put(borrow.getPaperId(), borrowList);
        return true;
    }

    public boolean borrowThesis(Borrow borrow, Object userObj) {
        ArrayList<Borrow> borrowList = borrows.get(borrow.getPaperId());
        if(borrowList == null){
            borrowList = new ArrayList<>();
        }
        if(!checkUserAbleToBorrow(userObj, borrow.getPaperId()) || borrowList.size() == 1){
            return false;
        }
        increaseUserBorrow(userObj);
        borrowList.add(borrow);
        borrows.put(borrow.getPaperId(), borrowList);
        return true;
    }

    private boolean checkUserAbleToBorrow(Object userObj, String paperId){
        if(userObj instanceof Staff staff){
            return staff.getBorrowCount(this.id) < 5;
        }else if(userObj instanceof Student student){
            return student.getBorrowCount(this.id) < 3;
        }
        return false;
    }

    public int returnPaper(Borrow returnBor, Object user) {
        ArrayList<Borrow> borrowList = borrows.get(returnBor.getPaperId());
        if(borrowList == null){
            return RETURN_FALSE;
        }
        Borrow maxBorrow = getMaxBorrow(borrowList, returnBor.getUserId());
        if(maxBorrow == null){
            return RETURN_FALSE;
        }
        int debt = calcDebt(maxBorrow, returnBor.getDate());
        setDebt(user, debt);
        decreaseUserBorrow(user);
        borrowList.remove(maxBorrow);
        return debt;
    }

    private void decreaseUserBorrow(Object userObj) {
        if(userObj instanceof Staff staff){
            staff.setBorrowCount(this.id, staff.getBorrowCount(this.id)-1);
        } else if (userObj instanceof Student student) {
            student.setBorrowCount(this.id, student.getBorrowCount(this.id)-1);
        }
    }

    public ArrayList<String> search(String query){
        ArrayList<String> result = searchBook(query);
        result.addAll(searchThesis(query));
        return result;
    }

    public ArrayList<String> searchBook(String query){
        ArrayList<String> result = new ArrayList<>();
        for (Book book :
                getBooks()) {
            if(book.toString().toLowerCase().contains(query)){
               result.add(book.getId());
            }
        }
        return result;
    }

    public ArrayList<String> searchThesis(String query){
        ArrayList<String> result = new ArrayList<>();
        for (Thesis thesis :
                getTheses()) {
            if(thesis.toString().toLowerCase().contains(query)){
                result.add(thesis.getId());
            }
        }
        return result;
    }

    private void increaseUserBorrow(Object userObj) {
        if(userObj instanceof Staff staff){
            staff.setBorrowCount(this.id, staff.getBorrowCount(this.id)+1);
        } else if (userObj instanceof Student student) {
            student.setBorrowCount(this.id, student.getBorrowCount(this.id)+1);
        }
    }

    private Borrow getMaxBorrow(ArrayList<Borrow> borrowList, String userId){
        Borrow maxBorrow = null;
        if(borrowList == null){
            return null;
        }

        for(int i = 0; i < borrowList.size(); i++){
            Borrow temp = borrowList.get(i);
            if(!temp.getUserId().equals(userId)){
                continue;
            }
            if(maxBorrow == null){
                maxBorrow = temp;
                continue;
            }
            if(temp.getDate().getTime() > maxBorrow.getDate().getTime()){
                maxBorrow = temp;
            }
        }
        return maxBorrow;
    }

    private int calcDebt(Borrow maxBorrow, Date returnDate){
        int debt = 0;

        if(maxBorrow == null){
            return 0;
        }

        if(maxBorrow.isStaff()){
            if(maxBorrow.isBook()) {
                debt = (int) (((returnDate.getTime() - maxBorrow.getDate().getTime()) / 3600000) - 14 * 24)*100;
            }else {
                debt = (int) (((returnDate.getTime() - maxBorrow.getDate().getTime()) / 3600000) - 10 * 24)*100;
            }
        } else {
            if(maxBorrow.isBook()) {
                debt = (int) (((returnDate.getTime() - maxBorrow.getDate().getTime()) / 3600000) - 10 * 24)*50;
            }else {
                debt = (int) (((returnDate.getTime() - maxBorrow.getDate().getTime()) / 3600000) - 7 * 24)*50;
            }
        }
        if(debt < 0){
            debt = 0;
        }
        return debt;
    }

    private void setDebt(Object userObj, int debt){
        if(userObj instanceof Staff user){
            user.setDebt(user.getDebt() + debt);
        }else if(userObj instanceof Student user){
            user.setDebt(user.getDebt() + debt);
        }
    }

    public ArrayList<Book> getBooks(){
        return new ArrayList<>(this.books.values());
    }

    public ArrayList<Thesis> getTheses(){
        return new ArrayList<>(this.theses.values());
    }

    public ArrayList<Book> getBorrowedBooks(){
        ArrayList<Book> borBooks = new ArrayList<>();
        for (Book book :
                getBooks()) {
            if(borrows.containsKey(book.getId())){
                borBooks.add(book);
            }
        }
        return borBooks;
    }

    public int countBorrowedBooks(){
        ArrayList<Book> borBooks = getBorrowedBooks();
        int count = 0;
        for (Book book:
             borBooks) {
            count += borrows.get(book.getId()).size();
        }
        return count;
    }

    public ArrayList<Thesis> getBorrowedTheses(){
        ArrayList<Thesis> borTheses = new ArrayList<>();
        for (Thesis thesis :
                getTheses()) {
            if(borrows.containsKey(thesis.getId())){
                borTheses.add(thesis);
            }
        }
        return borTheses;
    }

    public int countBorrowedTheses(){
        return  getBorrowedTheses().size();
    }

    public int[] catReport(String id) {
        int[] result = new int[]{0, 0};
        for (Book book :
                new ArrayList<>(books.values())) {
            if (book.getCatId().equals(id)) {
                result[0]+= book.getCopyCount();
            }
        }

        for (Thesis thesis :
                new ArrayList<>(theses.values())) {
            if(thesis.getCatId().equals(id)){
                result[1]++;
            }
        }

        return result;
    }

    public int booksCount(){
        ArrayList<Book> books1 = getBooks();
        int count = 0;
        for (Book book:
             books1) {
            count += book.getCopyCount();
        }
        return count;
    }

    public int thesesCount(){
        return getTheses().size();
    }

    private Borrow getMinBorrow(ArrayList<Borrow> borrowList){
        Borrow minBorrow = null;
        for(int i = 0; i < borrowList.size(); i++){
            Borrow temp = borrowList.get(i);
            if(minBorrow == null){
                minBorrow = temp;
                continue;
            }
            if(temp.getDate().getTime() < minBorrow.getDate().getTime()){
                minBorrow = temp;
            }
        }
        return minBorrow;
    }

    public HashSet<String> getPassedDeadlineIds(Date today) {
        HashSet<String> ids = new HashSet<>();
        for (ArrayList<Borrow> borrowList : new ArrayList<>(borrows.values())){
            Borrow minBorrow = getMinBorrow(borrowList);
            int debt = calcDebt(minBorrow, today);
            if(debt > 0){
                ids.add(minBorrow.getPaperId());
            }
        }
        return ids;
    }
}
