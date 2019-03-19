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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

    <div class="container" align="center">
        <div class="row">
            <div class="col"></div>

            <div class="col-6">

                <h3 class="display-4">Изменение записи</h3>

                <table class="table">
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

                <div align="center" class="mt-3">
                    <button type="button" class="btn btn-dark" onclick="window.location = '/chapter'">Назад</button>
                    <button type="button" class="btn btn-dark" onclick="updateChapter()">Изменить</button>
                </div>

            </div>

            <div class="col"></div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
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

