<%--
  Created by IntelliJ IDEA.
  User: Xoma163
  Date: 01.04.2018
  Time: 18:41
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
<p>
    <section class="container">
        <div class="login">
            <h1>Изменение записи</h1>
            <table>
                <td>
                <td><b>ID главы: </b></td>
                <td id="chapterId"></td></tr>
                <td>
                <td><b>Название:</b></td>
                <td><input required  type="text" id="title" name="title"></td></tr>
                <td>
                <td><b>ID книги:</b></td>
                <td>
                    <select id = "bookId" >
                        <c:forEach var="i" items="${idBookList}">
                            <option value = ${i}>
                                    ${i}
                            </option>
                        </c:forEach>
                    </select></td></tr>
            </table>
            <td><input type="button" value="Назад" onclick= "window.location='/book'"></td>
            <td><input type="button" value="Изменить" onclick="updateChapter()"></td>

        </div>
    </section>
</p>
<script>
    var locations = document.location.toString();
    var arr=locations.split('=');

    var chapterId = decodeURI(arr[1]);
    var title = decodeURI(arr[2]);
    var bookId = decodeURI(arr[3]);

    document.getElementById("chapterId").innerHTML = decodeURI(chapterId);
    document.getElementById("title").value = decodeURI(title);
    document.getElementById("bookId").value = decodeURI(bookId);

    function updateChapter() {
        var title = document.getElementById("title");
        var bookId = document.getElementById("bookId");
        var xhr = new XMLHttpRequest();
        var body = 'chapterId=' + chapterId + '&title=' + encodeURIComponent(title.value) + '&bookId=' + encodeURIComponent(bookId.value);
        xhr.open("POST", "/updateChapter", false);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        xhr.send(body);
        window.location='/chapter';
    }
</script>
</body>
</html>

