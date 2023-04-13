package com.sinatech.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Database {
    HashMap<String, Library> libraries;

    public Database() {
        this.libraries = new HashMap<>();
    }

    public Response addLibrary(Library library){
        if(libraries.get(library.getId()) != null){
            return new Response(1); //Return duplicate-id
        }
        this.libraries.put(library.getId(), library);
        return new Response(0); //Returns success
    }
}
