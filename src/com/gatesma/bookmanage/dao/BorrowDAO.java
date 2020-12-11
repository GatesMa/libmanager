package com.gatesma.bookmanage.dao;

/**
 * Copyright (C), 2019
 * FileName: BorrowDAO
 * Author:   Marlon
 * Date:     2019-11-05 13:36
 * Description: BorrowDAO
 */
import com.gatesma.bookmanage.bean.Book;
import com.gatesma.bookmanage.bean.Borrow;
import com.gatesma.bookmanage.util.DateUtil;
import org.springframework.stereotype.Repository;
import org.w3c.dom.UserDataHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BorrowDAO {

    public BorrowDAO() {
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

            String sql = "select count(*) from borrow";

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

    public int getTotal(int uid) {
        int total = 0;
        try (Connection c = getConnection(); Statement s = c.createStatement();) {

            String sql = "select count(*) from borrow where uid = " + uid;

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

    public void add(Borrow borrow) {

        String sql = "insert into borrow values(null,?,?,?,?)";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, borrow.getUser().getId());


            ps.setInt(2, borrow.getBook().getId());

            ps.setTimestamp(3, DateUtil.d2t(borrow.getBtime()));
            ps.setInt(4, borrow.getBdays());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                borrow.setId(id);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }


    public void update(Borrow borrow) {

        String sql = "update borrow set uid = ? , bid= ?, btime = ?, bdays = ?  where id = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, borrow.getUser().getId());


            ps.setInt(2, borrow.getBook().getId());

            ps.setTimestamp(3, DateUtil.d2t(borrow.getBtime()));
            ps.setInt(4, borrow.getBdays());

            ps.setInt(5, borrow.getId());

            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public void delete(int id) {

        try (Connection c = getConnection(); Statement s = c.createStatement();) {

            String sql = "delete from borrow where id = " + id;

            s.execute(sql);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public Borrow get(int id) {
        Borrow borrow = null;

        try (Connection c = getConnection(); Statement s = c.createStatement();) {

            String sql = "select * from borrow where id = " + id;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {

                borrow = new Borrow();
                borrow.setUser(new UserDAO().get(rs.getInt("uid")));
                borrow.setBook(new BookDAO().get(rs.getInt("bid")));
                borrow.setBtime(DateUtil.t2d(rs.getTimestamp("btime")));

                borrow.setId(id);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return borrow;
    }

    public List<Borrow> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<Borrow> list(int start, int count) {
        List<Borrow> borrows = new ArrayList<Borrow>();

        String sql = "select * from borrow order by id desc limit ?,? ";

        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, start);
            ps.setInt(2, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Borrow borrow = new Borrow();
                borrow = new Borrow();
                borrow.setUser(new UserDAO().get(rs.getInt("uid")));
                borrow.setBook(new BookDAO().get(rs.getInt("bid")));
                borrow.setBtime(DateUtil.t2d(rs.getTimestamp("btime")));

                borrow.setId(rs.getInt("id"));

                borrows.add(borrow);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return borrows;
    }


    public List<Borrow> getByUid(int uid) {
        return getByUid(uid, 0, Short.MAX_VALUE);
    }


    public List<Borrow> getByUid(int uid, int start, int count) {
        List<Borrow> borrows = new ArrayList<Borrow>();

        String sql = "select * from borrow  where uid = ? order by id desc limit ?,?";

        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, uid);
            ps.setInt(2, start);
            ps.setInt(3, count);



            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Borrow borrow = new Borrow();
                borrow = new Borrow();
                borrow.setUser(new UserDAO().get(rs.getInt("uid")));
                borrow.setBook(new BookDAO().get(rs.getInt("bid")));
                borrow.setBtime(DateUtil.t2d(rs.getTimestamp("btime")));
                borrow.setBdays(rs.getInt("bdays"));

                borrow.setId(rs.getInt("id"));

                borrows.add(borrow);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return borrows;
    }
}

