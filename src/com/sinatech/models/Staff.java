package com.sinatech.models;

import java.util.Date;

public class Staff {
    private String id, password, firstName, lastName, nationalCode;
    private Date birthdate;
    private String address;

    public Staff(String id, String password, String firstName, String lastName, String nationalCode, Date birthdate, String address) {
        this.id = id;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.birthdate = birthdate;
        this.address = address;
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
}
