package com.gatesma.bookmanage.dao;

/**
 * Copyright (C), 2019
 * FileName: userDAO
 * Author:   Marlon
 * Date:     2019-11-05 11:25
 * Description: userDAO
 */

import com.gatesma.bookmanage.bean.User;
import com.gatesma.bookmanage.util.DBUtil;
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
public class UserDAO {

    public UserDAO() {
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

            System.out.println("total:" + total);

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return total;
    }

    public void add(User user) {

        String sql = "insert into user values(null,?,?,?,?,?,?)";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getStud());
            ps.setString(4, user.getDept());
            ps.setString(5, user.getGrade());
            ps.setString(6, user.getGender());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                user.setId(id);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void update(User user) {

        String sql = "update user set name= ?, password = ?, stud = ? , dept = ?, grade = ? , gender = ? where id = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getStud());
            ps.setString(4, user.getDept());
            ps.setString(5, user.getGrade());
            ps.setString(6, user.getGender());
            ps.setInt(7, user.getId());

            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public void delete(int id) {

        try (Connection c = getConnection(); Statement s = c.createStatement();) {

            String sql = "delete from user where id = " + id;

            s.execute(sql);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public User get(int id) {
        User user = null;

        try (Connection c = getConnection(); Statement s = c.createStatement();) {

            String sql = "select * from user where id = " + id;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                user = new User();
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setStud(rs.getString("stud"));
                user.setDept(rs.getString("dept"));
                user.setGrade(rs.getString("grade"));
                user.setGender(rs.getString("gender"));
                user.setId(id);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return user;
    }

    public List<User> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<User> list(int start, int count) {
        List<User> users = new ArrayList<User>();

        String sql = "select * from user order by id desc limit ?,? ";

        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, start);
            ps.setInt(2, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setStud(rs.getString("stud"));
                user.setDept(rs.getString("dept"));
                user.setGrade(rs.getString("grade"));
                user.setGender(rs.getString("gender"));
                users.add(user);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return users;
    }

    public boolean isExist(String name) {
        User user = get(name);
        return user!=null;

    }

    public User get(String name) {
        User bean = null;

        String sql = "select * from user where name = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs =ps.executeQuery();

            if (rs.next()) {
                bean = new User();
                int id = rs.getInt("id");
                bean.setName(name);
                String password = rs.getString("password");
                bean.setPassword(password);
                bean.setId(id);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return bean;
    }

    public User get(String name, String password) {

        User user = null;
        String sql = "select * from user where name = ? and password = ? ";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs =ps.executeQuery();
//            System.out.println(ps);
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setStud(rs.getString("stud"));
                user.setDept(rs.getString("dept"));
                user.setGrade(rs.getString("grade"));
                user.setGender(rs.getString("gender"));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return user;
    }

}
