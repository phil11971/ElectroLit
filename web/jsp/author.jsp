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

<h3 align="center">Автор</h3>
<ul class="menu-main">
    <li onclick="window.location='/addAuthor'"><a href="#">Добавить запись</a></li>
    <li onclick="updateAuthor()"><a href="#">Изменить запись</a></li>
    <li onclick="deleteAuthor()"><a href="#">Удалить запись</a></li>
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
<script>
    function getnumb() {
        var count;
        var z = document.getElementsByName("q");
        var table = document.getElementById('tables');
        for (var i = 0; i < z.length; i++) {
            if (z[i].checked) {
                count = i;
                break;
            }
        }
        return count;
    }
    function deleteAuthor() {
        var count = getnumb();
        if (count == undefined) {
            alert("Выберите нужную строку!")
        }
        else {
            //   alert(table.rows[count + 1].cells[i].innerHTML);
            var xhr = new XMLHttpRequest();
            var table = document.getElementById('tables');
            var body = 'action=' + 'delete' + '&id_a=' + encodeURIComponent(table.rows[count + 1].cells[1].innerHTML);
            xhr.open("POST", "/author", false);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
            xhr.send(body);
            window.location = '/author';
        }
    }
    function updateAuthor() {
        var count = getnumb();
        if (count == undefined) {
            alert("Выберите нужную строку!")
        }
        var table = document.getElementById('tables');
        var location = '/updateAuthor?=';
        location += encodeURI(table.rows[count + 1].cells[1].innerHTML)+"="+encodeURI(table.rows[count + 1].cells[2].innerHTML)
            +"="+encodeURI(table.rows[count + 1].cells[3].innerHTML)+"="+encodeURI(table.rows[count + 1].cells[4].innerHTML);
        window.location = location;

    }
</script>
</body>
</html>