package com.gatesma.bookmanage.servlet;

import com.gatesma.bookmanage.bean.Book;
import com.gatesma.bookmanage.bean.Borrow;
import com.gatesma.bookmanage.bean.User;
import com.gatesma.bookmanage.dao.BookDAO;
import com.gatesma.bookmanage.dao.BorrowDAO;
import com.gatesma.bookmanage.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2019
 * FileName: BookHandler
 * Author:   Marlon
 * Date:     2019-11-05 18:14
 * Description:
 */
@Controller
@RequestMapping("book")
public class BookHandler {

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private BorrowDAO borrowDAO;


    /**
     * 列出所有书籍
     * @param map
     * @return
     */
    @RequestMapping(value = "/list")
    private String list(Map<String, Object> map, @RequestParam(value = "start", required = false) Integer start) {

//        System.out.println("start :" + start);
        if(null == start) {
            start = 0;
        }
//        int start = 0;
        int count = 10;

        int next = start + count;
        int pre = start - count;

        int total = bookDAO.getTotal();
        int last;
        if(0 == total % count) {
            last = total - count;
        } else {
            last = total - total % count;
        }

        pre = pre < 0 ? 0 : pre;
        next = next > last ? last : next;

        List<Book> bookList = bookDAO.list(start, count);
//        request.setAttribute("next", next);
//        request.setAttribute("pre", pre);
//        request.setAttribute("last", last);
//        request.setAttribute("heros", heros);

        map.put("next", next);
        map.put("pre", pre);
        map.put("last", last);
        map.put("books", bookList);

        return "listBook";
    }


    @RequestMapping(value = "/borrow", method = RequestMethod.GET)
    private String borrow(@RequestParam(value = "id")Integer id, Map<String, Object>map,
                          HttpServletRequest httpServletRequest) throws IOException {

        httpServletRequest.setCharacterEncoding("UTF-8");
        User user = (User)httpServletRequest.getSession().getAttribute("user");

        if(null == user) {
            map.put("msg", "请先登录");
            return "redirect:/user/login";
        }
        map.put("book", bookDAO.get(id));


        return "borrow";
    }

    @RequestMapping(value = "/borrow", method = RequestMethod.POST)
    private String borrow(@RequestParam(value = "bid")Integer bid, @RequestParam(value = "bdays")Integer bdays,
                          Map<String, Object>map, HttpServletRequest httpServletRequest) {

        Book book = bookDAO.get(bid);
        User user = (User)httpServletRequest.getSession().getAttribute("user");

//        System.out.println("---------------");
//        System.out.println("bid:" + bid);
//        System.out.println("user:" + user);
//        System.out.println("bdays:" + bdays);
//        System.out.println("---------------");

        if(book.getStock() <= 0) {
            return "list";
        }

        book.setStock(book.getStock() - 1);
        bookDAO.update(book);

        Borrow borrow = new Borrow(user, book, new Date(), bdays);
        borrowDAO.add(borrow);

        return "redirect:/user/userCenter";
    }

    @RequestMapping("returnBook")
    private String returnBook(@RequestParam(value = "id", required = false) Integer id, Map<String, Object>map,
                              HttpServletRequest httpServletRequest) throws IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        Borrow borrow = borrowDAO.get(id);
        Book book =  borrow.getBook();
        book.setStock(book.getStock() + 1);
        bookDAO.update(book);
        borrowDAO.delete(id);
        map.put("msg", "还书成功");
        map.put("type", "success");
        return "redirect:/user/userCenter";
    }


}
