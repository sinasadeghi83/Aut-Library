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
        //Default category
        this.categories.put("null", new Category("null", "null"));
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

    public Response addBook(Book book){
        Library library = libraries.get(book.getLibId());
        if(library == null){
            return new Response(2); //Returns not-found
        }
        Category category = categories.get(book.getCatId());
        if(category == null){
            return new Response(2); //Returns not-found
        }
        if(library.getBook(book.getId()) != null){
            return new Response(1); //Returns duplicate-id
        }
        library.addBook(book);
        return new Response(0); //Returns success
    }

    public Response editBook(Book editBook){
        //Check if library exist in database
        Library library = libraries.get(editBook.getLibId());
        if(library == null){
            return new Response(2); //Returns not-found
        }

        //Check if category exist
        if(editBook.getCatId() != null) {
            Category category = categories.get(editBook.getCatId());
            if (category == null) {
                return new Response(2); //Returns not-found
            }
        }

        Book book = library.getBook(editBook.getId());
        //Check if book exist in the library
        if(book == null){
            return new Response(2); //Returns not-found
        }

        book.update(editBook);
        return new Response(0); //Returns success
    }

    public Response removeBook(String id, String libId) {
        //Check if library exist in database
        Library library = libraries.get(libId);
        if(library == null){
            return new Response(2); //Returns not-found
        }
        //Check if book exist in the library
        if(library.getBook(id) == null){
            return new Response(2); //Returns not-found
        }

        library.removeBook(id);
        return new Response(0); //Returns success
    }


    public Response addThesis(Thesis thesis) {
        Library library = libraries.get(thesis.getLibId());
        if(library == null){
            return new Response(2); //Returns not-found
        }
        Category category = categories.get(thesis.getCatId());
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
