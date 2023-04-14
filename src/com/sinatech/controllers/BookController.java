package com.sinatech.controllers;

import com.sinatech.components.DatabaseManager;
import com.sinatech.models.Book;
import com.sinatech.models.Category;
import com.sinatech.models.Library;
import com.sinatech.models.Response;

public class BookController {
    public static Response addBook(Book book){
        Library library = DatabaseManager.getLibrary(book.getLibId());
        if(library == null){
            return new Response(2); //Returns not-found
        }
        Category category = DatabaseManager.getCategory(book.getCatId());
        if(category == null){
            return new Response(2); //Returns not-found
        }
        if(library.getBook(book.getId()) != null){
            return new Response(1); //Returns duplicate-id
        }
        library.addBook(book);
        return new Response(0); //Returns success
    }

    public static Response editBook(Book editBook){
        //Check if library exist in database
        Library library = DatabaseManager.getLibrary(editBook.getLibId());
        if(library == null){
            return new Response(2); //Returns not-found
        }

        //Check if category exist
        if(editBook.getCatId() != null) {
            Category category = DatabaseManager.getCategory(editBook.getCatId());
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
    
    public static Response removeBook(String id, String libId) {
        //Check if library exist in database
        Library library = DatabaseManager.getLibrary(libId);
        if(library == null){
            return new Response(2); //Returns not-found
        }
        //Check if book exist in the library
        if(library.getBook(id) == null){
            return new Response(2); //Returns not-found
        }

        if(!library.removeBook(id)){
            return new Response(3); //not-allowed
        }
        return new Response(0); //Returns success
    }
}
