package com.sinatech.controllers;

import com.sinatech.components.DatabaseManager;
import com.sinatech.models.Response;
import com.sinatech.models.Staff;
import com.sinatech.models.Student;

public class UserController {

    public static Response search(String id, String password, String query) {
        query = query.toLowerCase();
        Staff staff = DatabaseManager.getStaff(id);
        Student student = DatabaseManager.getStudent(id);
        if(staff == null && student == null){
            return new Response(2); //not-found
        }

        String supposedPass;
        if(staff != null){
            supposedPass = staff.getPassword();
        }else{
            supposedPass = student.getPassword();
        }
        if(!supposedPass.equals(password)){
            return new Response(3); //not-allowed
        }

        String result = DatabaseManager.searchUser(query);
        return new Response(0, result);
    }
}
