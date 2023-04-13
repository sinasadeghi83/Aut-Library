package com.sinatech.models;

public class Response {

    public static final String[] ID_MSG = new String[]{
            "success", //id: 0
            "duplicate-id" //id: 1
    };
    private int id;
    private String message;

    public Response(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
