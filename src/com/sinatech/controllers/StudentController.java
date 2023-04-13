package com.sinatech.controllers;

import com.sinatech.components.DatabaseManager;
import com.sinatech.models.Response;
import com.sinatech.models.Student;

public class StudentController {

    public static Response addStudent(Student student) {
        if(DatabaseManager.getStudent(student.getId()) != null){
            return new Response(1); //returns duplicate-id
        }
        DatabaseManager.insertStudent(student);
        return new Response(0); //returns success
    }
}
