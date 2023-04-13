package com.sinatech.components;

import com.sinatech.models.*;

import java.util.HashMap;

public class DatabaseManager {
    private static HashMap<String, Library> libraries;
    private static HashMap<String, Category> categories;
    private static HashMap<String, Student> students;

    public DatabaseManager() {
        libraries = new HashMap<>();
        categories = new HashMap<>();
        students = new HashMap<>();
        //Default category
        categories.put("null", new Category("null", "null"));
    }

    public static Library getLibrary(String id) {
        return libraries.get(id);
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
}
