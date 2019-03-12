<%--
  Created by IntelliJ IDEA.
  User: Xoma163
  Date: 02.04.2018
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>
        <c:out value="${title}"></c:out>
    </title>
</head>
<body>
<section class="container">
    <div class="login">
        <h1>Изменение записи</h1>
        <table>
            <td>
            <td><b>id книги: </b></td>
            <td id="bookId"></td></tr>
            <td>
            <td><b>id автора: </b></td>
            <td id="authorId"></td></tr>
            <td>
            <td>Описание: </td>
            <td><input required type="text" id="desc"></td></tr>
            </tr>
        </table>
        <td><input class="submit1" type="button" value="Назад" onclick= "window.location='/teacherTextbook'"></td>
        <td><input class="submit2" type="button" value="Изменить" onclick="updateTeacherTextbook()"></td>
    </div>
</section>
<script>
    var locations = document.location.toString();
    var arr=locations.split('=');

    var bookId = decodeURI(arr[1]);
    var authorId = decodeURI(arr[2]);

    document.getElementById("bookId").value=decodeURI(bookId);
    document.getElementById("authorId").value=decodeURI(authorId);
    document.getElementById("bookId").innerHTML=decodeURI(bookId);
    document.getElementById("authorId").innerHTML=decodeURI(authorId);

    function updateTeacherTextbook() {
        var desc = document.getElementById("desc");
        var xhr = new XMLHttpRequest();
        var body = 'bookId='+bookId+'&authorId=' + authorId+ '&desc=' + encodeURIComponent(desc.value);
        xhr.open("POST", "/updateBookAuthor", false);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        xhr.send(body);
        window.location='/bookAuthor';
    }
</script>
</body>
</html>

