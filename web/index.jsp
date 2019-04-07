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
  <link rel="stylesheet" href="css/main.css">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

    <div class="container" align="center">
      <div class="row">
        <div class="col"></div>

        <div class="col-6">

          <h3 class="display-4">Выбор таблиц</h3>

          <div class="list-group">
            <a href="/publishingOffice" class="list-group-item list-group-item-action">Издательство</a>
            <a href="/author" class="list-group-item list-group-item-action">Автор</a>
            <a href="/book" class="list-group-item list-group-item-action">Книга</a>
            <a href="/chapter" class="list-group-item list-group-item-action">Глава</a>
          </div>

        </div>

        <div class="col"></div>
      </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

</body>
</html>
