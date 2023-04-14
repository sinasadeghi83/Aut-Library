package com.sinatech.models;

import java.util.Date;

public class Student {
    private String id, password, firstName, lastName, nationalCode;
    private Date birthdate;
    private String address;
    private int borrowedCount, debt;

    public Student(String id, String password, String firstName, String lastName, String nationalCode, Date birthdate, String address) {
        this.id = id;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.birthdate = birthdate;
        this.address = address;
        this.borrowedCount = this.debt = 0;
    }

    public int getDebt() {
        return debt;
    }

    public void setDebt(int debt) {
        this.debt = debt;
    }

    public int getBorrowedCount() {
        return borrowedCount;
    }

    public void setBorrowedCount(int borrowedCount) {
        this.borrowedCount = borrowedCount;
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

    public void update(Student editStudent) {
        if(editStudent.getBirthdate() != null){
            this.setBirthdate(editStudent.getBirthdate());
        }

        if(editStudent.getAddress() != null){
            this.setAddress(editStudent.getAddress());
        }

        if(editStudent.getFirstName() != null){
            this.setFirstName(editStudent.getFirstName());
        }

        if(editStudent.getLastName() != null){
            this.setLastName(editStudent.getLastName());
        }

        if(editStudent.getNationalCode() != null){
            this.setNationalCode(editStudent.getNationalCode());
        }

        if(editStudent.getPassword() != null){
            this.setPassword(editStudent.getPassword());
        }
    }
}
