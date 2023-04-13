package com.sinatech.controllers;

import com.sinatech.components.DatabaseManager;
import com.sinatech.models.*;

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

    public static Response editThesis(Thesis editThesis) {
        //Check if library exist in database
        Library library = DatabaseManager.getLibrary(editThesis.getLibId());
        if(library == null){
            return new Response(2); //Returns not-found
        }

        //Check if category exist
        if(editThesis.getCatId() != null) {
            Category category = DatabaseManager.getCategory(editThesis.getCatId());
            if (category == null) {
                return new Response(2); //Returns not-found
            }
        }

        Thesis thesis = library.getThesis(editThesis.getId());
        //Check if thesis exist in the library
        if(thesis == null){
            return new Response(2); //Returns not-found
        }

        thesis.update(editThesis);
        return new Response(0); //Returns success
    }

    //TODO: Checking if thesis is borrowed by someone
    public static Response removeThesis(String id, String libId) {
        //Check if library exist in database
        Library library = DatabaseManager.getLibrary(libId);
        if(library == null){
            return new Response(2); //Returns not-found
        }
        //Check if thesis exist in the library
        if(library.getThesis(id) == null){
            return new Response(2); //Returns not-found
        }

        library.removeThesis(id);
        return new Response(0); //Returns success
    }
}
