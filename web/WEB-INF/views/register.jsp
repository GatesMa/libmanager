<%--
  Created by IntelliJ IDEA.
  User: gatesma
  Date: 2019-11-05
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    /*浏览器提交的数据在提交给服务器之前设置编码方式为UTF-8*/
    request.setCharacterEncoding("UTF-8");
%>
<%

    String msg  = request.getParameter("msg");
    System.out.println(msg);
    request.setAttribute("msg", msg);
%>
<head>
    <meta charset="UTF-8"/>
    <%--    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
    <title>注册</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>


<div class="page-header" style="width:400px;margin: 0px auto;">
    <h3>四川大学小型图书管理系统 <small> - 注册👏！</small></h3>
</div>
<c:if test="${msg != null}">
    <div class="alert alert-danger" style="width:80%;margin: 0px auto;margin-top: 20px" role="alert">${msg}</div>
</c:if>
<form:form action="${pageContext.request.contextPath}/user/register" method="post" modelAttribute="user" style="width:400px;margin: auto;margin-top: 40px">
    <input type="hidden" name="_method" value="PUT"/>
    <div class="form-group">
        <label for="name">姓名：</label>
        <form:input path="name" id="name" class="form-control"/>
    </div>

    <div class="form-group">
        <label for="password">密码：</label>
        <form:password path="password" id="password" class="form-control"/>
    </div>

    <div class="form-group">
        <label for="stud">学号：</label>
        <form:input path="stud" id="stud" class="form-control"/>
    </div>
    <div class="form-group">
        <label for="dept">系名：</label>
        <form:input path="dept" id="dept" class="form-control"/>
    </div>
    <div class="form-group">
        <label for="grade">年级：</label>
        <form:input path="grade" id="garde" class="form-control"/>
    </div>
    <div class="form-group">
        <label for="gender">性别：</label>
        <form:input path="gender" id="gender" class="form-control"/>
    </div>
    <input type="submit" class="form-control btn btn-primary" value="注册">
    <br>
    <br>
    <button class="form-control btn btn-default"><a href="/libmanager/user/login">登陆</a> </button>
</form:form>