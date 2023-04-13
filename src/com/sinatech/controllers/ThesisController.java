package com.sinatech.controllers;

import com.sinatech.components.DatabaseManager;
import com.sinatech.models.Category;
import com.sinatech.models.Library;
import com.sinatech.models.Response;
import com.sinatech.models.Thesis;

public class ThesisController {
    public static Response addThesis(Thesis thesis) {
        Library library = DatabaseManager.getLibrary(thesis.getLibId());
        if(library == null){
            return new Response(2); //Returns not-found
        }
        Category category = DatabaseManager.getCategory(thesis.getCatId());
        if(category == null){
            return new Response(2); //Returns not-found
        }
        if(library.getThesis(thesis.getId()) != null){
            return new Response(1); //Returns duplicate-id
        }
        library.addThesis(thesis);
        return new Response(0); //Returns success
    }
}
