package com.gatesma.bookmanage.bean;

import java.util.Date;

/**
 * Copyright (C), 2019
 * FileName: borrow
 * Author:   Marlon
 * Date:     2019-11-05 11:22
 * Description: borrow
 */
public class Borrow {

    private Integer id;
    private User user;
    private Book book;
    private Date btime;
    private Integer bdays;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getBtime() {
        return btime;
    }

    public void setBtime(Date btime) {
        this.btime = btime;
    }

    public Integer getBdays() {
        return bdays;
    }

    public void setBdays(Integer bdays) {
        this.bdays = bdays;
    }

    public Borrow() {
    }

    public Borrow(User user, Book book, Date btime, Integer bdays) {
        this.user = user;
        this.book = book;
        this.btime = btime;
        this.bdays = bdays;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "id=" + id +
                ", user=" + user +
                ", book=" + book +
                ", btime=" + btime +
                ", bdays=" + bdays +
                '}';
    }
}

