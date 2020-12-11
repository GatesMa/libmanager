<%--
  Created by IntelliJ IDEA.
  User: gatesma
  Date: 2019-11-05
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>个人中心</title>
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

<%

    String msg  = request.getParameter("msg");
    System.out.println(msg);
    request.setAttribute("msg", msg);
    String type  = request.getParameter("type");
    System.out.println(type);
    request.setAttribute("type", type);
%>

<c:if test="${msg != null}">
    <c:if test="${type != null}">
        <div class="alert alert-success" style="width:80%;margin: 0px auto;" role="alert">${msg}</div>
    </c:if>
    <div class="alert alert-danger" style="width:80%;margin: 0px auto;" role="alert"><%= msg %></div>
</c:if>

<div style="width:80%; margin:10px auto">
    <span class="label label-primary large">我的借阅</span>
    <button class="btn" ><a href="/libmanager/book/list">全部图书</a></button>
</div>
<table style="width:80%; margin:44px auto" class="table table-striped table-bordered table-hover  table-condensed" align='center' border='1' cellspacing='0'>
    <tr>
        <td>id</td>
        <td>书号</td>
        <td>书名</td>
        <td>借书时间</td>
        <td>借书天数</td>
        <td>还书</td>
    </tr>
    <c:forEach items="${borrows}" var="borrow" varStatus="st">
        <tr>
            <td>${borrow.id}</td>
            <td>${borrow.book.bookid}</td>
            <td>${borrow.book.name}</td>
            <td>${borrow.btime}</td>
            <td>${borrow.bdays}</td>

            <td><a href="/libmanager/book/returnBook?id=${borrow.id}">还这本书</a></td>
        </tr>
    </c:forEach>
</table>
<c:if test="${borrows.size() == 0}">
    <div class="alert alert-warning" style="width:80%;margin: 0px auto;" role="alert">一本书都没借，去借一本把，求你了</div>
</c:if>
<nav class="pager">
    <ul class="pager">
        <li><a href="?start=0">[首 页]</a></li>
        <li><a href="?start=${pre}">[上一页]</a></li>
        <li><a href="?start=${next}">[下一页]</a></li>
        <li><a href="?start=${last}">[末 页]</a></li>
        <li><a href="/libmanager/book/list">图书大厅</a></li>
    </ul>
</nav>



</body>
</html>
