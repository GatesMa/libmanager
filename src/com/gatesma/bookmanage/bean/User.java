package com.gatesma.bookmanage.bean;

/**
 * Copyright (C), 2019
 * FileName: user
 * Author:   Marlon
 * Date:     2019-11-05 11:15
 * Description: user
 */
public class User {

    private Integer id;
    private String name;
    private String password;
    private String stud;
    private String dept;
    private String grade;
    private String gender;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStud() {
        return stud;
    }

    public void setStud(String stud) {
        this.stud = stud;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public User() {
    }

    public User(String name, String password, String stud, String dept, String grade, String gender) {
        this.name = name;
        this.password = password;
        this.stud = stud;
        this.dept = dept;
        this.grade = grade;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", stud='" + stud + '\'' +
                ", dept='" + dept + '\'' +
                ", grade='" + grade + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
