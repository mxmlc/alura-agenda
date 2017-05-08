package com.github.mxmlc.alura.agenda.model;

import java.io.Serializable;

public class Student implements Serializable {

    public static final String TABLE_NAME = "student";
    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";
    public static final String ADDRESS_COLUMN = "address";
    public static final String TELEPHONE_COLUMN = "telephone";
    public static final String SITE_COLUMN = "site";
    public static final String EMAIL_COLUMN = "email";
    public static final String GRADE_COLUMN = "grade";

    private Long id;
    private String name;
    private String address;
    private String telephone;
    private String site;
    private String email;
    private Double grade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }

}
