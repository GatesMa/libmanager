<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: gatesma
  Date: 2019-11-05
  Time: 19:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>书籍列表</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<body>
<script>
    $(function(){
        $("a").addClass("btn btn-default btn-xs");
    });
</script>

<div class="page-header" style="width:80%;margin: 0px auto;">
    <h1>${user.name} <small>你好，欢迎👏！</small></h1>

</div>

<div style="width:80%; margin:10px auto">
    <span class="label label-primary large">全部图书</span>
    <button class="btn" ><a href="/libmanager/user/userCenter">我的借阅</a></button>
</div>
<table style="width:80%; margin:10px auto" class="table table-striped table-bordered table-hover  table-condensed" align='center' border='1' cellspacing='0'>
    <tr>
        <td>id</td>
        <td>书号</td>
        <td>书名</td>
        <td>出版社</td>
        <td>出版日期</td>
        <td>作者</td>
        <td>内容摘要</td>
        <td>库存</td>
        <td>借书</td>
    </tr>
    <c:forEach items="${books}" var="book" varStatus="st">

        <tr>
            <td>${book.id}</td>
            <td>${book.bookid}</td>
            <td>${book.name}</td>
            <td>${book.press}</td>
            <td>${book.publishdate}</td>
            <td>${book.author}</td>
            <td>${book.content}</td>
            <td>${book.stock}</td>
            <td><a href="borrow?id=${book.id}">借这本书</a></td>
        </tr>
    </c:forEach>
</table>
<nav class="pager">
    <ul class="pager">
        <li><a href="?start=0">[首 页]</a></li>
        <li><a href="?start=${pre}">[上一页]</a></li>
        <li><a href="?start=${next}">[下一页]</a></li>
        <li><a href="?start=${last}">[末 页]</a></li>
        <li><a href="/libmanager/admin/addBook">添加书籍</a></li>
    </ul>
</nav>


</body>
</html>
