package com.gatesma.bookmanage.test;

import com.gatesma.bookmanage.bean.Book;
import com.gatesma.bookmanage.bean.User;
import com.gatesma.bookmanage.dao.BookDAO;
import com.gatesma.bookmanage.dao.UserDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Copyright (C), 2019
 * FileName: Test
 * Author:   Marlon
 * Date:     2019-11-05 11:44
 * Description: Test
 */
public class Test {

    public static void main(String[] args) {
//        UserDAO userDAO = new UserDAO();
//        BookDAO bookDAO = new BookDAO();
//
//        User addUser1 = new User("Marlon", "","2017141461189", "计算机学院", "2017", "男");
//        User addUser2 = new User("WuZhao", "", "2017141461147", "计算机学院", "2017", "男");
//
//        userDAO.add(addUser1);
//        userDAO.add(addUser2);
//
//
//        List<User> users = new ArrayList<>();
//        users = userDAO.list();
//        for(User user : users) {
//            System.out.println(user);
//        }

//        Book book1 = new Book("17304", "计算机网络","机械工业出版社", new Date(), "詹姆斯", "自顶向下");
//
//        bookDAO.add(book1);
//
//
//        List<Book> books = new ArrayList<>();
//        books = bookDAO.list();
//        for(Book book : books) {
//            System.out.println(book);
//        }

        String name = "Marlon";
        String password = "123456";
        User user = new UserDAO().get(name, password);
        System.out.println(user);
        System.out.println("UserName ：" + name);
        System.out.println("Password ：" + password );
    }
}
