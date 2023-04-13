package com.sinatech.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Database {
    HashMap<String, Library> libraries;
    HashMap<String, Category> categories;

    public Database() {
        this.libraries = new HashMap<>();
        this.categories = new HashMap<>();
    }

    public Response addLibrary(Library library){
        if(libraries.get(library.getId()) != null){
            return new Response(1); //Return duplicate-id
        }
        this.libraries.put(library.getId(), library);
        return new Response(0); //Returns success
    }

    public Response addCategory(Category category){
        if(categories.get(category.getId()) != null){
            return new Response(1); //Return duplicate-id
        }
        this.categories.put(category.getId(), category);
        return new Response(0); //Returns success
    }
}
