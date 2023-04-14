package com.sinatech.controllers;

import com.sinatech.components.DatabaseManager;
import com.sinatech.models.*;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class LibraryController {

    public static Response addLibrary(Library library){
        if(DatabaseManager.getLibrary(library.getId()) != null){
            return new Response(1); //Return duplicate-id
        }
        DatabaseManager.insertLibrary(library);
        return new Response(0); //Returns success
    }

    public static Response addCategory(Category category){
        if(DatabaseManager.getCategory(category.getId()) != null){
            return new Response(1); //Return duplicate-id
        }
        DatabaseManager.insertCategory(category);
        return new Response(0); //Returns success
    }

    public static Response borrow(Borrow borrow, String userPass) {
        borrow.setIsStaff();
        Object user;
        if (borrow.isStaff()) {
            user = DatabaseManager.getStaff(borrow.getUserId());
        } else {
            user = DatabaseManager.getStudent(borrow.getUserId());
        }
        if (user == null) {
            return new Response(2); // not-found
        }

        if (!checkUserPass(user, userPass, borrow.isStaff())) {
            return new Response(4); //invalid-pass
        }

        Library library = DatabaseManager.getLibrary(borrow.getLibId());
        if (library == null) {
            return new Response(2); //not-found
        }

        borrow.setIsBook();
        if (borrow.isBook() && library.getBook(borrow.getPaperId()) == null) {
            return new Response(2); //not-found
        }

        if (!borrow.isBook() && library.getThesis(borrow.getPaperId()) == null) {
            return new Response(2); //not-found
        }

        if (borrow.isBook() && !library.borrowBook(borrow, user)) {
            return new Response(3); //not-allowed
        }
        if (!borrow.isBook() && !library.borrowThesis(borrow, user)) {
            return new Response(3); //not-allowed
        }
        return new Response(0); //success
    }

    public static Response returnPaper(Borrow borrow, String userPass) {
        borrow.setIsStaff();
        Object user;
        if(borrow.isStaff()){
            user = DatabaseManager.getStaff(borrow.getUserId());
        }else{
            user = DatabaseManager.getStudent(borrow.getUserId());
        }
        if(user == null){
            return new Response(2); // not-found
        }

        if(!checkUserPass(user, userPass, borrow.isStaff())){
            return new Response(4); //invalid-pass
        }

        Library library = DatabaseManager.getLibrary(borrow.getLibId());
        if(library == null){
            return new Response(2); //not-found
        }

        borrow.setIsBook();

        int result = library.returnPaper(borrow, user);
        if(result == Library.RETURN_FALSE){
            return new Response(2); //not-found
        } else if (result > 0) {
            return new Response(0, Integer.toString(result));
        }
        return new Response(0); //success
    }

    public static Response search(String query) {
        query = query.toLowerCase();
        String result = "";
        for (Library lib:
                DatabaseManager.getLibraries()) {
            result += "|" + lib.search(query);
        }
        if(result.length() > 0){
            result = result.substring(1);
        }
        return new Response(0, result);
    }

    private static boolean checkUserPass(Object userObj, String userPass, boolean isStaff){
        if(isStaff){
            Staff user = (Staff) userObj;
            return userPass.equals(user.getPassword());
        }else{
            Student user = (Student) userObj;
            return userPass.equals(user.getPassword());
        }
    }

    public static Response catReport(String id) {
        int bookCount = 0;
        int thesisCount = 0;

        if(DatabaseManager.getCategory(id) == null){
            return new Response(2); //not-found
        }

        for (Library lib :
                DatabaseManager.getLibraries()) {
            int[] report = lib.catReport(id);
            bookCount += report[0];
            thesisCount += report[1];
        }
        return new Response(0, bookCount + " " + thesisCount);
    }

    public static Response libReport(String id) {
        Library library = DatabaseManager.getLibrary(id);
        if(library == null){
            return new Response(2); //not-found
        }
        String result = library.booksCount() + " " + library.thesesCount() + " " + library.countBorrowedBooks() + " " + library.countBorrowedTheses();
        return new Response(0, result);
    }

    public static Response reportDeadline(String id, Date date) {
        Library library = DatabaseManager.getLibrary(id);
        if(library == null){
            return new Response(2); //not-found
        }

        HashSet<String> ids = library.getPassedDeadlineIds(date);
        String result = "";
        for (String mid:
             ids) {
            result += "|" + mid;
        }
        if(result.length() > 0){
            result = result.substring(1);
        }else {
            result = "none";
        }

        return new Response(0, result);
    }

    public static Response reportPenalties() {
        int penalties = 0;
        for (Student student :
                DatabaseManager.getStudents()) {
            penalties += student.getDebt();
        }

        for (Staff staff :
                DatabaseManager.getStaffs()) {
            penalties += staff.getDebt();
        }

        return new Response(0, Integer.toString(penalties));
    }
}
