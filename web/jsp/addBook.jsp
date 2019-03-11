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
            <td>
            <td><b>Год издания:</b></td>
            <td><input required type="number" id="year"></td></tr>
            <td><b>Количество книг:</b></td>
            <td><input required type="number" id="pages"></td></tr>
            <td>
            <td><b>Цена, р:</b></td>
            <td><input required type="number" id="price"></td></tr>
            <td>
            <td><b>ID издательства:</b></td>
            <td>
                <select id = "poId" >
                    <c:forEach var="i" items="${idPOList}">
                        <option value =  ${i}>
                                ${i}
                        </option>
                    </c:forEach>
                </select></td></tr>
        </table>
        <td><input type="button" value="Назад" onclick= "window.location='/book'";></td>
        <td><input type="button" value="Создать" onclick="addBook()"></td>
    </div>
</section>

<script>
    function addBook() {
        var title = document.getElementById("title");
        var year = document.getElementById("year");
        var pages = document.getElementById("pages");
        var price = document.getElementById("price");
        var poId = document.getElementById("poId");

        var xhr = new XMLHttpRequest();
        var body = 'title=' + encodeURIComponent(title.value) + '&year=' + encodeURIComponent(year.value)+ '&pages=' + encodeURIComponent(pages.value)+ '&price=' + encodeURIComponent(price.value)+ '&poId=' + encodeURIComponent(poId.value);
        xhr.open('POST', '/addBook', false);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        xhr.send(body);
        window.location = '/book';
    }
</script>
</body>
</html>
