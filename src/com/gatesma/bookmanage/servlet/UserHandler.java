package com.gatesma.bookmanage.servlet;

import com.gatesma.bookmanage.bean.Book;
import com.gatesma.bookmanage.bean.Borrow;
import com.gatesma.bookmanage.bean.User;
import com.gatesma.bookmanage.dao.BookDAO;
import com.gatesma.bookmanage.dao.BorrowDAO;
import com.gatesma.bookmanage.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2019
 * FileName: UserHandler
 * Author:   Marlon
 * Date:     2019-11-05 20:07
 * Description: UserHandler
 */
@Controller
@RequestMapping("user")
@SessionAttributes(value = {"user"})
public class UserHandler {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private BorrowDAO borrowDAO;

    @ModelAttribute
    public void getUser(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {

        if(id != null) {

            map.put("user", userDAO.get(id));
        }
    }

    /**
     * 获取登陆页
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    /**
     * 登陆逻辑,登陆成功去个人中心
     * @param name
     * @param password
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "username")String name, @RequestParam(value = "password")String password,
                        HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse
                        , Map<String, Object> map) throws IOException{
        httpServletRequest.setCharacterEncoding("UTF-8");
        byte[] bytes = name.getBytes("ISO-8859-1");
        name = new String(bytes, "UTF-8");

//        name = HtmlUtils.htmlEscape(name);
//        System.out.println("name:" + name);
//        System.out.println("password:" + password);
        User user = userDAO.get(name, password);
//        System.out.println(user);
        System.out.println(user);
        if(null == user){
            map.put("msg", "账号或密码错误");
            System.out.println("账号或密码错误");
            return "redirect:login";
        }

        if("".equals(user.getName()) || "".equals(user.getPassword())
                || "null".equals(user.getName()) || "null".equals(user.getPassword())) {
            map.put("msg", "账号或密码错误");
            return "login";
        }
        
        httpServletRequest.getSession().setAttribute("user", user);
        return "redirect:userCenter";
    }


    /**
     * 获取登陆页
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register(Map<String, Object> map) {
        map.put("user", new User());
        return "register";
    }

    /**
     * 注册成功去个人中心
     * @param user
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.PUT)
    public String register(User user, HttpServletRequest httpServletRequest, Map<String, Object>map) throws IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        System.out.println("user:" + user);
        if("".equals(user.getName()) || "".equals(user.getPassword())
                || "null".equals(user.getName()) || "null".equals(user.getPassword())) {
            map.put("msg", "输入正确的账号密码");
            return "redirect:register";
        }

        byte[] bytes = user.getDept().getBytes("ISO-8859-1");
        user.setDept(new String(bytes, "UTF-8"));

        bytes = user.getGender().getBytes("ISO-8859-1");
        user.setGender(new String(bytes, "UTF-8"));

        bytes = user.getName().getBytes("ISO-8859-1");
        user.setName(new String(bytes, "UTF-8"));

//        System.out.println(user.getDept());
//        System.out.println(user.getGender());
        if(null != userDAO.get(user.getName())) {
            map.put("msg", "用户名已存在");
            return "redirect:register";
        }
        userDAO.add(user);
        httpServletRequest.getSession().setAttribute("user", user);
        return "redirect:userCenter";
    }


    @RequestMapping("userCenter")
    public String userCenter(HttpServletRequest httpServletRequest, Map<String, Object>map,
                             @RequestParam(value = "start", required = false) Integer start)throws IOException {
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

        //        System.out.println("start :" + start);
        if(null == start) {
            start = 0;
        }
//        int start = 0;
        int count = 10;

        int next = start + count;
        int pre = start - count;

        int total = borrowDAO.getTotal(user.getId());
        int last;
        if(0 == total % count) {
            last = total - count;
        } else {
            last = total - total % count;
        }

        pre = pre < 0 ? 0 : pre;
        next = next > last ? last : next;



        //TODO
        List<Borrow> borrows = borrowDAO.getByUid(user.getId(), start, count);
        
        for(Borrow borrow : borrows) {
            System.out.println(borrow);
        }

        map.put("next", next);
        map.put("pre", pre);
        map.put("last", last);
        map.put("borrows", borrows);

        return "userCenter";
    }


}
