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

    public static Response editStudent(Student editStudent) {
        Student student = DatabaseManager.getStudent(editStudent.getId());
        if(student == null){
            return new Response(2); //returns not-found
        }
        student.update(editStudent);
        return new Response(0); //returns success
    }

    public static Response removeStudent(String id) {
        if(DatabaseManager.getStudent(id) == null){
            return new Response(2); //returns not-found
        }
        if(!DatabaseManager.deleteStudent(id)){
            return new Response(3); //not-allowed
        }
        return new Response(0); //returns success
    }
}
