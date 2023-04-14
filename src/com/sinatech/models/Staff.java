package com.sinatech.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Staff {
    private String id, password, firstName, lastName, nationalCode;
    private Date birthdate;
    private String address;
    private int debt;
    private HashMap<String, Integer> borrowCount;

    public Staff(String id, String password, String firstName, String lastName, String nationalCode, Date birthdate, String address) {
        this.id = id;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.birthdate = birthdate;
        this.address = address;
        this.borrowCount = new HashMap<>();
        this.debt = 0;
    }

    public int getDebt() {
        return debt;
    }

    public void setDebt(int debt) {
        this.debt = debt;
    }

    public int getBorrowCount(String libId) {
        Integer count = borrowCount.get(libId);
        if(count == null){
            return 0;
        }
        return count.intValue();
    }

    public void setBorrowCount(String libId, int borrowCount) {
        this.borrowCount.put(libId, borrowCount);
    }

    public boolean isBorrowed(){
        for (Integer bCount :
                new ArrayList<>(borrowCount.values())) {
            if(bCount > 0){
                return true;
            }
        }
        return false;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void update(Staff editStaff) {
        if(editStaff.getBirthdate() != null){
            this.setBirthdate(editStaff.getBirthdate());
        }

        if(editStaff.getAddress() != null){
            this.setAddress(editStaff.getAddress());
        }

        if(editStaff.getFirstName() != null){
            this.setFirstName(editStaff.getFirstName());
        }

        if(editStaff.getLastName() != null){
            this.setLastName(editStaff.getLastName());
        }

        if(editStaff.getNationalCode() != null){
            this.setNationalCode(editStaff.getNationalCode());
        }

        if(editStaff.getPassword() != null){
            this.setPassword(editStaff.getPassword());
        }
    }

    @Override
    public String toString() {
        return firstName + "|" + lastName;
    }
}
