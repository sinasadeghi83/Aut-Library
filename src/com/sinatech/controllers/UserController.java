package com.sinatech.controllers;

import com.sinatech.components.DatabaseManager;
import com.sinatech.models.Response;
import com.sinatech.models.Staff;
import com.sinatech.models.Student;

import java.util.ArrayList;
import java.util.Collections;

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
            return new Response(4); //invalid-pass
        }

        ArrayList<String> result = DatabaseManager.searchUser(query);
        if(result.size() == 0){
            return new Response(2); //not-found
        }

        Collections.sort(result);
        String strResult = "";
        for (int i = 0; i < result.size(); i++) {
            if(i > 0){
                strResult += "|";
            }
            strResult += result.get(i);
        }
        return new Response(0, strResult);
    }
}
