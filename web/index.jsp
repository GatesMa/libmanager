<%--
  Created by IntelliJ IDEA.
  User: gatesma
  Date: 2019-11-05
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>四川大学小型图书管理系统</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

    <div class="jumbotron" style="width: 80%;margin: 0px auto;margin-top:50px">
        <div style="margin-left: 20px;margin-top: 20px;">
            <img src="http://gatesma.cn/myfile/scu.png"
                 style="margin: 0px auto;width: 300px" style="display: inline-block"/>
            <h1 style="display: inline-block">四川大学小型图书管理系统</h1>
        </div>
        <div style="margin-left: 20px;margin-top: 20px;">
            <p>这是一个较完善的小型图书管理系统，你可以选择登陆或者注册一个用户来体验，系统中有一个管理员用户对图书的增删改查，项目使用SpringMVC完成，待改进...</p>
        </div>
        <a class="btn btn-primary btn-lg" href="book/list" role="button" style="margin-left: 20px">图书</a>
        <a class="btn btn-info btn-lg" href="user/register" role="button" style="margin-left: 20px">注册</a>
        <a class="btn btn-warning btn-lg" href="user/login" role="button" style="margin-left: 20px">登陆</a>
    </div>

</body>
</html>
