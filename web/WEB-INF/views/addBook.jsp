<%--
  Created by IntelliJ IDEA.
  User: gatesma
  Date: 2019-11-06
  Time: 17:04
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
<head>
    <meta charset="UTF-8"/>
    <%--    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
    <title>添加书籍</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>


<div class="page-header" style="width:400px;margin: 0px auto;">
    <h3>四川大学小型图书管理系统 <small> - 添加书籍👏！</small></h3>
</div>
<body>
    <form:form action="${pageContext.request.contextPath}/admin/addBook" method="post" modelAttribute="book" style="width:400px;margin: auto;margin-top: 40px">
        <input type="hidden" name="_method" value="PUT"/>
        <div class="form-group">
            <label for="bookid">书号：</label>
            <form:input path="bookid" id="bookid" class="form-control"/>
        </div>

        <div class="form-group">
            <label for="name">书名：</label>
            <form:input path="name" id="name" class="form-control"/>
        </div>

        <div class="form-group">
            <label for="press">出版商：</label>
            <form:input path="press" id="press" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="publishdate">出版日期：</label>
            <form:input path="publishdate" id="publishdate" class="form-control"/>
            <form:errors path="publishdate"></form:errors>
        </div>
        <div class="form-group">
            <label for="author">作者：</label>
            <form:input path="author" id="author" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="content">内容简介：</label>
            <form:textarea path="content" id="content" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="stock">库存：</label>
            <form:input path="stock" id="stock" class="form-control"/>
        </div>
        <input type="submit" class="form-control btn btn-primary" value="添加">
    </form:form>
</body>
</html>
