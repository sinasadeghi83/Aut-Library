package com.sinatech.components;

import com.sinatech.models.*;

import java.util.HashMap;

public class DatabaseManager {
    public static HashMap<String, Library> libraries;
    public static HashMap<String, Category> categories;

    public DatabaseManager() {
        libraries = new HashMap<>();
        categories = new HashMap<>();
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
}
