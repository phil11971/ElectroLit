<%--
  Created by IntelliJ IDEA.
  User: Xoma163
  Date: 01.04.2018
  Time: 18:33
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
            <td><b>Название:</b></td>
            <td><input required  type="text" id="title" name="title"></td></tr>
            <td><b>ID книги:</b></td>
            <td>
                <select id = "bookId" >
                    <c:forEach var="i" items="${idBookList}">
                        <option value =  ${i}>
                                ${i}
                        </option>
                    </c:forEach>
                </select></td></tr>
        </table>
        <td><input type="button" value="Назад" onclick= "window.location='/chapter'";></td>
        <td><input type="button" value="Создать" onclick="addChapter()"></td>
    </div>
</section>

<script>
    function addChapter() {
        var title = document.getElementById("title");
        var bookId = document.getElementById("bookId");

        var xhr = new XMLHttpRequest();
        var body = 'title=' + encodeURIComponent(title.value) + '&bookId=' + encodeURIComponent(bookId.value);
        xhr.open('POST', '/addChapter', false);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        xhr.send(body);
        window.location = '/chapter';
    }
</script>
</body>
</html>
