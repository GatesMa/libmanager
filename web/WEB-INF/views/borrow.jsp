<%--
  Created by IntelliJ IDEA.
  User: gatesma
  Date: 2019-11-05
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <%--    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
    <title>借书</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>


<div class="page-header" style="width:400px;margin: 0px auto;">
    <h3>四川大学小型图书管理系统 <small> - 借阅👏！</small></h3>
</div>
<form action="borrow" method="post" style="width:400px;margin: auto;margin-top: 40px">
    <input type="hidden" name="bid" id="bid" value="${book.id}"  class="form-control" />
    <div class="form-group">
        <label for="bookid">书号：</label>
        <input type="text" name="bookid" value="${book.bookid}" readonly="readonly" class="form-control" id="bookid" />
    </div>

    <div class="form-group">
        <label for="name">书名：</label>
        <input type="text" name="name" id="name" value="${book.name}" readonly="readonly" class="form-control" />
    </div>

    <div class="form-group">
        <label for="content">内容简介：</label>
        <input type="text" name="content" id="content" value="${book.content}" readonly="readonly" class="form-control" />
    </div>

    <div class="form-group">
        <label for="bdays">借书天数：</label>
        <input type="number" name="bdays" id="bdays" value="1"  class="form-control" />
    </div>

    <input type="submit" class="form-control btn btn-primary" value="借书">
</form>

</body>
</html>
