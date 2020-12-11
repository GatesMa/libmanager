package com.gatesma.bookmanage.dao;

/**
 * Copyright (C), 2019
 * FileName: BookDAO
 * Author:   Marlon
 * Date:     2019-11-05 13:06
 * Description: BookDAO
 */
import com.gatesma.bookmanage.bean.Book;
import com.gatesma.bookmanage.util.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


@Repository
public class BookDAO {

    public BookDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bookmanagement?characterEncoding=UTF-8", "root",
                "qzone");
    }

    public int getTotal() {
        int total = 0;
        try (Connection c = getConnection(); Statement s = c.createStatement();) {

            String sql = "select count(*) from book";

            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }

//            System.out.println("total:" + total);

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return total;
    }

    public void add(Book book) {

        String sql = "insert into book values(null,?,?,?,?,?,?,?)";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {


            ps.setString(1, book.getBookid());
            ps.setString(2, book.getName());
            ps.setString(3, book.getPress());
            ps.setTimestamp(4, DateUtil.d2t(book.getPublishdate()));
            ps.setString(5, book.getAuthor());
            ps.setString(6, book.getContent());
            ps.setInt(7, book.getStock());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                book.setId(id);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void update(Book book) {

        String sql = "update book set bookid = ? , name= ?, press = ?, publishdate = ? , author = ?, content = ? , stock = ? where id = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {


            ps.setString(1, book.getBookid());
            ps.setString(2, book.getName());
            ps.setString(3, book.getPress());
            ps.setTimestamp(4, DateUtil.d2t(book.getPublishdate()));
            ps.setString(5, book.getAuthor());
            ps.setString(6, book.getContent());
            ps.setInt(7, book.getStock());
            ps.setInt(8, book.getId());

            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public void delete(int id) {

        try (Connection c = getConnection(); Statement s = c.createStatement();) {

            String sql = "delete from book where id = " + id;

            s.execute(sql);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public Book get(int id) {
        Book book = null;

        try (Connection c = getConnection(); Statement s = c.createStatement();) {

            String sql = "select * from book where id = " + id;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                book = new Book();
                book.setBookid(rs.getString("bookid"));
                book.setName(rs.getString("name"));
                book.setPress(rs.getString("press"));
                book.setPublishdate(DateUtil.t2d(rs.getTimestamp("publishdate")));
                book.setAuthor(rs.getString("author"));
                book.setContent(rs.getString("content"));
                book.setStock(rs.getInt("stock"));
                book.setId(id);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return book;
    }

    public List<Book> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<Book> list(int start, int count) {
        List<Book> books = new ArrayList<Book>();

        String sql = "select * from book order by id desc limit ?,? ";

        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, start);
            ps.setInt(2, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book = new Book();
                book.setBookid(rs.getString("bookid"));
                book.setName(rs.getString("name"));
                book.setPress(rs.getString("press"));
                book.setPublishdate(DateUtil.t2d(rs.getTimestamp("publishdate")));
                book.setAuthor(rs.getString("author"));
                book.setContent(rs.getString("content"));
                book.setStock(rs.getInt("stock"));
                book.setId(rs.getInt("id"));

                books.add(book);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return books;
    }

}

