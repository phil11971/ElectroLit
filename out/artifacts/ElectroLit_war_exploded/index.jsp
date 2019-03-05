<%--
  Created by IntelliJ IDEA.
  User: Vladik
  Date: 04.03.2019
  Time: 12:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
  <title>Главная страница</title>
</head>
<body>
<div align=center class="category-wrap">
  <h3>Выбор таблиц</h3>
  <ul>
    <li><a href="/publishingOffice">Издательство</a></li>
    <li><a href="/AuthorServlet">Автор</a></li>
    <li><a href="/BookServlet">Книга</a></li>
    <li><a href="/BookAuthorServlet">Книга-Автор</a></li>
    <li><a href="/ChapterServlet">Глава</a></li>

  </ul>
</div>
</body>
</html>
