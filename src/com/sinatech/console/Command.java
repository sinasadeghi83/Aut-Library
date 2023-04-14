package com.sinatech.console;

import com.sinatech.components.DatabaseManager;
import com.sinatech.controllers.*;
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
            case "edit-thesis":
                this.editThesis();
                break;
            case "remove-thesis":
                this.removeThesis();
                break;
            case "add-student":
                this.addStudent();
                break;
            case "edit-student":
                this.editStudent();
                break;
            case "remove-student":
                this.removeStudent();
                break;
            case "add-staff":
                this.addStaff();
                break;
            case "edit-staff":
                this.editStaff();
                break;
            case "remove-staff":
                this.removeStaff();
                break;
            case "borrow":
                this.borrow();
                break;
            case "return":
                this.returnPaper();
                break;
            case "search":
                this.search();
                break;
            case "search-user":
                this.searchUser();
                break;
            case "category-report":
                this.catReport();
                break;
            case "library-report":
                this.libReport();
                break;
            case "report-passed-deadline":
                this.reportDeadline();
                break;
            case "report-penalties-sum":
                this.reportPenalties();
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

    private Thesis readThesisData(boolean editMode) throws Exception{
        String id = args.get(0);
        String libId;
        if(editMode){
            libId = args.get(1);
            args.remove(1);
        }else{
            libId = args.get(6);
        }
        String title = args.get(1);
        if(title.equals("-")){
            title = null;
        }
        String studName = args.get(2);
        if(studName.equals("-")){
            studName = null;
        }
        String profName = args.get(3);
        if(profName.equals("-")){
            profName = null;
        }
        Date defenseDate = null;
        if(!args.get(4).equals("-")) {
            defenseDate = new SimpleDateFormat("YYYY").parse(args.get(4));
        }
        String catId = args.get(5);
        if(catId.equals("-")){
            catId = null;
        }
        return new Thesis(id, title, studName, profName, defenseDate, catId, libId);
    }

    public void addThesis() throws Exception{
        Thesis thesis = readThesisData(false);
        Response response = ThesisController.addThesis(thesis);
        System.out.println(response);
    }

    public void editThesis() throws Exception{
        Thesis thesis = readThesisData(true);
        Response response = ThesisController.editThesis(thesis);
        System.out.println(response);
    }

    public void removeThesis() throws Exception{
        String id = args.get(0);
        String libId = args.get(1);
        Response response = ThesisController.removeThesis(id, libId);
        System.out.println(response);
    }

    private Student readStudentData() throws Exception{
        String id = args.get(0);
        String password = args.get(1);
        if(password.equals("-")){
            password = null;
        }
        String firstName = args.get(2);
        if(firstName.equals("-")){
            firstName = null;
        }
        String lastName = args.get(3);
        if(lastName.equals("-")){
            lastName = null;
        }
        String nationalCode = args.get(4);
        if(nationalCode.equals("-")){
            nationalCode = null;
        }
        Date birthdate = null;
        if(!args.get(5).equals("-")){
            birthdate = new SimpleDateFormat("YYYY").parse(args.get(5));
        }
        String address = args.get(6);
        if(address.equals("-")){
            address = null;
        }
        return new Student(id, password, firstName, lastName, nationalCode, birthdate, address);
    }

    public void addStudent() throws Exception{
        Student student = readStudentData();
        Response response = StudentController.addStudent(student);
        System.out.println(response);
    }

    public void editStudent() throws Exception{
        Student student = readStudentData();
        Response response = StudentController.editStudent(student);
        System.out.println(response);
    }

    public void removeStudent() throws Exception{
        String id = args.get(0);
        Response response = StudentController.removeStudent(id);
        System.out.println(response);
    }

    private Staff readStaffData() throws Exception{
        String id = args.get(0);
        String password = args.get(1);
        if(password.equals("-")){
            password = null;
        }
        String firstName = args.get(2);
        if(firstName.equals("-")){
            firstName = null;
        }
        String lastName = args.get(3);
        if(lastName.equals("-")){
            lastName = null;
        }
        String nationalCode = args.get(4);
        if(nationalCode.equals("-")){
            nationalCode = null;
        }
        Date birthdate = null;
        if(!args.get(5).equals("-")){
            birthdate = new SimpleDateFormat("YYYY").parse(args.get(5));
        }
        String address = args.get(6);
        if(address.equals("-")){
            address = null;
        }
        return new Staff(id, password, firstName, lastName, nationalCode, birthdate, address);
    }

    public void addStaff() throws Exception{
        Staff staff = readStaffData();
        Response response = StaffController.addStaff(staff);
        System.out.println(response);
    }

    public void editStaff() throws Exception{
        Staff staff = readStaffData();
        Response response = StaffController.editStaff(staff);
        System.out.println(response);
    }

    public void removeStaff() throws Exception{
        String id = args.get(0);
        Response response = StaffController.removeStaff(id);
        System.out.println(response);
    }

    private Borrow readBorrowData() throws Exception{
        String id = args.get(0);
        String libId = args.get(2);
        String paperId = args.get(3);
        String dateStr = args.get(4) + "|" + args.get(5);
        Date date = new SimpleDateFormat("yyyy-MM-dd|hh:mm").parse(dateStr);
        Borrow borrow = new Borrow(id, false, paperId, false, date, libId);
        return borrow;
    }

    public void borrow() throws Exception{
        String password = args.get(1);
        Borrow borrow = readBorrowData();
        Response response = LibraryController.borrow(borrow, password);
        System.out.println(response);
    }

    public void returnPaper() throws Exception{
        String password = args.get(1);
        Borrow borrow = readBorrowData();
        Response response = LibraryController.returnPaper(borrow, password);
        System.out.println(response);
    }

    public void search() throws Exception{
        String query = args.get(0);
        Response response = LibraryController.search(query);
        System.out.println(response);
    }

    public void searchUser(){
        String id = args.get(0);
        String password = args.get(1);
        String query = args.get(2);
        Response response = UserController.search(id, password, query);
        System.out.println(response);
    }

    public void catReport(){
        String id = args.get(0);
        Response response = LibraryController.catReport(id);
        System.out.println(response);
    }

    public void libReport(){
        String id = args.get(0);
        Response response = LibraryController.libReport(id);
        System.out.println(response);
    }

    public void reportDeadline() throws Exception{
        String id = args.get(0);
        String strDate = args.get(1) + "|" + args.get(2);
        Date date = new SimpleDateFormat("yyyy-MM-dd|hh:mm").parse(strDate);
        Response response = LibraryController.reportDeadline(id, date);
        System.out.println(response);
    }

    public void reportPenalties() throws Exception{
        Response response = LibraryController.reportPenalties();
        System.out.println(response);
    }
}
