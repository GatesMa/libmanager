package com.gatesma.bookmanage.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Copyright (C), 2019
 * FileName: book
 * Author:   Marlon
 * Date:     2019-11-05 11:15
 * Description: book
 */
public class Book {

    private Integer id;
    private String bookid;
    private String name;
    private String press;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publishdate;
    private String author;
    private String content;
    private int stock;

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public Date getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Book() {
    }

    public Book(String bookid, String name, String press, Date publishdate, String author, String content, int stock) {
        this.bookid = bookid;
        this.name = name;
        this.press = press;
        this.publishdate = publishdate;
        this.author = author;
        this.content = content;
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookid='" + bookid + '\'' +
                ", name='" + name + '\'' +
                ", press='" + press + '\'' +
                ", publishdate=" + publishdate +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", stock='" + stock + '\'' +
                '}';
    }
}
