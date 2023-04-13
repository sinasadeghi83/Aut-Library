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

    public void addBook() throws Exception{
        String id = args.get(0);
        String title = args.get(1);
        String author = args.get(2);
        String pub = args.get(3);
        Date printYear = new SimpleDateFormat("YYYY").parse(args.get(4));
        int copyCount = Integer.parseInt(args.get(5));
        String catId = args.get(6);
        String libId = args.get(7);
        Book book = new Book(id, title, author, pub, printYear, copyCount, catId, libId);
        Response response = db.addBook(book);
        System.out.println(response.getMessage());
    }
}
