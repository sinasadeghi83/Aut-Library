package com.sinatech.components;

import com.sinatech.models.*;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseManager {
    private static HashMap<String, Library> libraries;
    private static HashMap<String, Category> categories;
    private static HashMap<String, Student> students;
    private static HashMap<String, Staff> staffs;

    public DatabaseManager() {
        libraries = new HashMap<>();
        categories = new HashMap<>();
        students = new HashMap<>();
        staffs = new HashMap<>();
        //Default category
        categories.put("null", new Category("null", "null"));
    }

    public static Library getLibrary(String id) {
        return libraries.get(id);
    }

    public static ArrayList<Library> getLibraries(){
        return new ArrayList<>(libraries.values());
    }
    public static void insertLibrary(Library library){
        libraries.put(library.getId(), library);
    }

    public static Category getCategory(String id) {
        return categories.get(id);
    }

    public static void insertCategory(Category category) {
        categories.put(category.getId(), category);
    }

    public static Student getStudent(String id) {
        return students.get(id);
    }

    public static void insertStudent(Student student) {
        students.put(student.getId(), student);
    }

    public static boolean deleteStudent(String id) {
        Student student = students.get(id);
        if(student.getBorrowedCount() > 0 || student.getDebt() > 0){
            return false;
        }
        students.remove(id);
        return true;
    }

    public static Staff getStaff(String id) {
        return staffs.get(id);
    }

    public static void insertStaff(Staff staff) {
        staffs.put(staff.getId(), staff);
    }

    public static boolean deleteStaff(String id) {
        Staff staff = staffs.get(id);
        if(staff.getBorrowCount() > 0 || staff.getDebt() > 0){
            return false;
        }
        staffs.remove(id);
        return true;
    }

    public static ArrayList<Staff> getStaffs(){
        return new ArrayList<>(staffs.values());
    }

    public static ArrayList<Student> getStudents(){
        return new ArrayList<>(students.values());
    }

    public static String searchUser(String query) {
        String result = "";
        for (Staff staff :
                getStaffs()) {
            if(staff.toString().toLowerCase().contains(query)){
                result += "|" + staff.getId();
            }
        }
        for (Student student :
                getStudents()) {
            if(student.toString().toLowerCase().contains(query)){
                result += "|" + student.getId();
            }
        }

        if(result.length() > 0){
            result = result.substring(1);
        }

        return result;
    }
}
