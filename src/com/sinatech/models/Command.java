package com.sinatech.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Command {
    private Database db;
    private String cmdStr;
    private String method;
    private ArrayList<String> args;

    public Command(String cmdStr, Database db) {
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
        }
    }

    public void addLibrary() throws Exception{
        String id = args.get(0);
        String name = args.get(1);
        Date foundDate = new SimpleDateFormat("YYYY").parse(args.get(2));
        int tableCount = Integer.parseInt(args.get(3));
        String address = args.get(4);
        Library lib = new Library(id, name, foundDate, tableCount, address);
        Response response = db.addLibrary(lib);
        System.out.println(response.getMessage());
    }

    public void addCategory(){
        String id = args.get(0);
        String name = args.get(1);
        Category category = new Category(id, name);
        Response response = db.addCategory(category);
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
        Response response = db.addBook(book);
        System.out.println(response.getMessage());
    }

    public void editBook() throws Exception{
        Book book = readBookData(true);
        Response response = db.editBook(book);
        System.out.println(response.getMessage());
    }
}
