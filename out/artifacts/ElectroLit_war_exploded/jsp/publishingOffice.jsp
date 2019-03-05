<%--
  Created by IntelliJ IDEA.
  User: Vladik
  Date: 04.03.2019
  Time: 23:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <meta charset="utf-8">
        <title> <c:out value='${pagename}'></c:out></title>
    </head>
    <body>

        <h3 align="center">Учитель</h3>
        <ul class="menu-main">
            <li ><a href="#">Добавить запись</a></li>
            <li onclick="updateTeacher()"><a href="#">Изменить запись</a></li>
            <li onclick="deletes()"><a href="#">Удалить запись</a></li>
            <li>Сортировка</li>
        </ul>
        <table id="tables">
            <tr>
                <th>#</th>
                <c:forEach var="nameColumn" items="${columnList}">
                    <th onclick="th(this)()">${nameColumn}</th>
                </c:forEach>
            </tr>
            <c:forEach var="list" items="${tableList}">
                <tr onclick="selectTR(this)">
                    <td><input type="radio" name="q"></td>
                    <c:forEach var="item" items="${list}">
                        <td>${item}</td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
        <div align=center>
            <td><input class="submit" type="button" value="Назад" onclick= "window.location=''" ;></td>
        </div>

    </body>
</html>
