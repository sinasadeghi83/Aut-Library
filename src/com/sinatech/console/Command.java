package com.sinatech.console;

import com.sinatech.components.DatabaseManager;
import com.sinatech.controllers.BookController;
import com.sinatech.controllers.LibraryController;
import com.sinatech.controllers.ThesisController;
import com.sinatech.models.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Command {
    private DatabaseManager db;
    private String cmdStr;
    private String method;
    private ArrayList<String> args;

    public Command(String cmdStr, DatabaseManager db) {
        this.cmdStr = cmdStr;
        this.db = db;
        args = new ArrayList<>();
    }

    public void runCommand() throws Exception{
        String[] sepMethodArgs = this.cmdStr.split("#");
        this.method = sepMethodArgs[0];
        if(sepMethodArgs.length > 1) {
            Collections.addAll(this.args, sepMethodArgs[1].split("\\|"));
        }
        switch (method){
            case "add-library":
                this.addLibrary();
                break;
            case "add-category":
                this.addCategory();
                break;
            case "add-book":
                this.addBook();
                break;
            case "edit-book":
                this.editBook();
                break;
            case "remove-book":
                this.removeBook();
                break;
            case "add-thesis":
                this.addThesis();
                break;
        }
    }

    public void addLibrary() throws Exception{
        String id = args.get(0);
        String name = args.get(1);
        Date foundDate = new SimpleDateFormat("YYYY").parse(args.get(2));
        int tableCount = Integer.parseInt(args.get(3));
        String address = args.get(4);
        Library lib = new Library(id, name, foundDate, tableCount, address);
        Response response = LibraryController.addLibrary(lib);
        System.out.println(response.getMessage());
    }

    public void addCategory(){
        String id = args.get(0);
        String name = args.get(1);
        Category category = new Category(id, name);
        Response response = LibraryController.addCategory(category);
        System.out.println(response.getMessage());
    }

    private Book readBookData(boolean editMode) throws Exception{
        String id = args.get(0);
        String libId;
        if(editMode){
            libId = args.get(1);
            args.remove(1);
        }else{
            libId = args.get(7);
        }
        String title = args.get(1);
        if(title.equals("-")){
            title = null;
        }
        String author = args.get(2);
        if(author.equals("-")){
            author = null;
        }
        String pub = args.get(3);
        if(pub.equals("-")){
            pub = null;
        }
        Date printYear = null;
        if(!args.get(4).equals("-")) {
            printYear = new SimpleDateFormat("YYYY").parse(args.get(4));
        }
        int copyCount = args.get(5).equals("-") ? Book.COPY_COUNT_NULL : Integer.parseInt(args.get(5));
        String catId = args.get(6);
        if(catId.equals("-")){
            catId = null;
        }
        return new Book(id, title, author, pub, printYear, copyCount, catId, libId);
    }

    public void addBook() throws Exception{
        Book book = readBookData(false);
        Response response = BookController.addBook(book);
        System.out.println(response.getMessage());
    }

    public void editBook() throws Exception{
        Book book = readBookData(true);
        Response response = BookController.editBook(book);
        System.out.println(response.getMessage());
    }

    public void removeBook() throws Exception{
        String id = args.get(0);
        String libId = args.get(1);
        Response response = BookController.removeBook(id, libId);
        System.out.println(response);
    }

    private Thesis readThesisData() throws Exception{
        String id = args.get(0);
        String title = args.get(1);
        String studName = args.get(2);
        String profName = args.get(3);
        Date defenseDate = new SimpleDateFormat("YYYY").parse(args.get(4));
        String catId = args.get(5);
        String libId = args.get(6);
        return new Thesis(id, title, studName, profName, defenseDate, catId, libId);
    }

    public void addThesis() throws Exception{
        Thesis thesis = readThesisData();
        Response response = ThesisController.addThesis(thesis);
        System.out.println(response);
    }
}
