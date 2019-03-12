<%--
  Created by IntelliJ IDEA.
  User: Xoma163
  Date: 02.04.2018
  Time: 14:29
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
        <h1>Добавление записи</h1>
        <table>
            <td>
            <td><b>id книги:</b></td>
            <td><select id = "bookId" >
                <c:forEach var="i" items="${bookList}">
                    <option value =  ${i}>
                            ${i}
                    </option>
                </c:forEach>
            </select></td></tr>
            <td>
            <td><b>id автора:</b></td>
            <td><select id = "authorId" >
                <c:forEach var="i" items="${authorList}">
                    <option value =  ${i}>
                            ${i}
                    </option>
                </c:forEach>
            </select></td></tr>
            <td>
            <td>Описание:</td>
            <td><input required type="text" id="desc"></td></tr>
        </table>
        <td><input class="submit1" type="button" value="Назад" onclick= "window.location='/bookAuthor'"></td>
        <td><input class="submit2" type="button" value="Создать" onclick="addBookAuthor()"></td>
    </div>
</section>
</p>

<script>
    function addBookAuthor() {
        var bookId = document.getElementById("bookId");
        var authorId = document.getElementById("authorId");
        var desc = document.getElementById("desc");

        var xhr = new XMLHttpRequest();
        var body = 'bookId=' + encodeURIComponent(bookId.value) + '&authorId=' + encodeURI(authorId.value) + '&desc=' + encodeURI(desc.value);

        xhr.open('POST', '/addBookAuthor', false);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        xhr.send(body);
        window.location = '/bookAuthor?='+encodeURI(bookId.value);
    }
</script>
</body>
</html>
