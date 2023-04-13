package com.sinatech.controllers;

import com.sinatech.components.DatabaseManager;
import com.sinatech.models.Book;
import com.sinatech.models.Category;
import com.sinatech.models.Library;
import com.sinatech.models.Response;

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
}
