<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: gatesma
  Date: 2019-11-05
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<head>
    <meta charset="UTF-8"/>
    <%--    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
    <title>登陆</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<%

    String msg  = request.getParameter("msg");
    System.out.println(msg);
    request.setAttribute("msg", msg);
%>

<c:if test="${msg != null}">
    <div class="alert alert-danger" style="width:80%;margin: 0px auto;margin-top: 20px" role="alert">${msg}</div>
</c:if>
<div class="page-header" style="width:400px;margin: 0px auto;">
    <h3>四川大学小型图书管理系统 <small> - 登陆👏！</small></h3>
</div>
<form action="login" method="post" style="width:400px;margin: auto;margin-top: 40px" >
    <div class="form-group">
        <label for="username">姓名：</label>
        <input type="text" name="username" id="username" class="form-control" />
    </div>
    <div class="form-group">
        <label for="password">密码：</label>
        <input type="password" name="password" class="form-control" id="password" />
    </div>
    <button type="submit" class="form-control btn btn-primary" value="登陆">登陆</button>
    <br>
    <br>
    <button class="form-control btn btn-default"><a href="register">注册</a> </button>
</form>