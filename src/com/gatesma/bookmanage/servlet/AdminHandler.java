package com.gatesma.bookmanage.servlet;

import com.gatesma.bookmanage.bean.Book;
import com.gatesma.bookmanage.bean.User;
import com.gatesma.bookmanage.dao.BookDAO;
import com.gatesma.bookmanage.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2019
 * FileName: AdminHandler
 * Author:   Marlon
 * Date:     2019-11-06 13:43
 * Description: AmdinHandler
 */


@Controller
@RequestMapping("admin")
public class AdminHandler {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private BookDAO bookDAO;

    @ModelAttribute("book")
    public void getBook(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {

        if(id != null) {

            map.put("book", bookDAO.get(id));
        }
    }


    @RequestMapping("listUser")
    public String listUser(HttpServletRequest httpServletRequest, Map<String, Object>map, @RequestParam(value = "start", required = false) Integer start) {

        User user = (User)httpServletRequest.getSession().getAttribute("user");
        if(!"admin".equals(user.getName())) {
            map.put("msg", "非管理员");
            return "redirect:/user/userCenter";
        }


        if(null == start) {
            start = 0;
        }
        int count = 10;

        int next = start + count;
        int pre = start - count;

        int total = userDAO.getTotal();
        int last;
        if(0 == total % count) {
            last = total - count;
        } else {
            last = total - total % count;
        }

        pre = pre < 0 ? 0 : pre;
        next = next > last ? last : next;

        List<User> userList = userDAO.list(start, count);

        map.put("next", next);
        map.put("pre", pre);
        map.put("last", last);
        map.put("books", userList);


        return "listUser";
    }


    @RequestMapping(value = "addBook", method = RequestMethod.GET)
    public String addBook(HttpServletRequest httpServletRequest, Map<String, Object>map)throws IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        User user = (User)httpServletRequest.getSession().getAttribute("user");


        System.out.println("addBook user:" + user);


        if(null == user) {
            map.put("msg", "请先登录");
            return "redirect:/user/login";
        }
        
        System.out.println("addBook name: " + user.getName() + " " + user.getName().length() + " " + user.getName().getClass());
        
        if("".equals(user.getName()) || "".equals(user.getPassword())
                || "null".equals(user.getName()) || "null".equals(user.getPassword())) {
            map.put("msg", "请先登录");
            return "redirect:/user/login";
        }

        if(!"admin".equals(user.getName())) {
            map.put("msg", "非管理员无法添加书籍");
            return "redirect:/user/userCenter";
        }
        map.put("book", new Book());
        return "addBook";
    }

    @RequestMapping(value = "addBook", method = RequestMethod.PUT)
    public String addBook(Book book, HttpServletRequest httpServletRequest, Map<String, Object>map) throws IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        User user = (User)httpServletRequest.getSession().getAttribute("user");


        if(null == user) {
            map.put("msg", "请先登录");
            return "redirect:/user/login";
        }

        if("".equals(user.getName()) || "".equals(user.getPassword())
                || "null".equals(user.getName()) || "null".equals(user.getPassword())) {
            map.put("msg", "请先登录");
            return "redirect:/user/login";
        }

        System.out.println("addBook user:" + user);


        System.out.println("addBook name: " + user.getName() + " " + user.getName().length() + " " + user.getName().getClass());

        if(!"admin".equals(user.getName())) {
            map.put("msg", "非管理员无法添加书籍");
            return "redirect:/user/userCenter";
        }
        httpServletRequest.setCharacterEncoding("UTF-8");
        byte[] bytes = book.getName().getBytes("ISO-8859-1");
        book.setName(new String(bytes, "UTF-8"));

        bytes = book.getPress().getBytes("ISO-8859-1");
        book.setPress(new String(bytes, "UTF-8"));

        bytes = book.getAuthor().getBytes("ISO-8859-1");
        book.setAuthor(new String(bytes, "UTF-8"));

        bytes = book.getContent().getBytes("ISO-8859-1");
        book.setContent(new String(bytes, "UTF-8"));
        
        System.out.println(book);
        bookDAO.add(book);

        map.put("msg", "添加成功");
        map.put("type", "success");

        return "redirect:/book/list";
    }

}
