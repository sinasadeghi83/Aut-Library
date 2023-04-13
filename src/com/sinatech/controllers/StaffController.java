package com.sinatech.controllers;

import com.sinatech.components.DatabaseManager;
import com.sinatech.models.Response;
import com.sinatech.models.Staff;

public class StaffController {
    public static Response addStaff(Staff staff) {
        if(DatabaseManager.getStaff(staff.getId()) != null){
            return new Response(1); //returns duplicate-id
        }
        DatabaseManager.insertStaff(staff);
        return new Response(0); //returns success
    }

    public static Response editStaff(Staff editStaff) {
        Staff staff = DatabaseManager.getStaff(editStaff.getId());
        if(staff == null){
            return new Response(2); //returns not-found
        }
        staff.update(editStaff);
        return new Response(0); //returns success
    }

    //TODO: Check if the staff has debt or has borrowed a book
    public static Response removeStaff(String id) {
        if(DatabaseManager.getStaff(id) == null){
            return new Response(2); //returns not-found
        }
        DatabaseManager.deleteStaff(id);
        return new Response(0); //returns success
    }
}
