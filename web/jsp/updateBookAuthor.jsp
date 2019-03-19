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

                <div align="center" class="mt-3">
                    <button type="button" class="btn btn-dark" onclick="window.location = '/bookAuthor'">Назад</button>
                    <button type="button" class="btn btn-dark" onclick="updateBookAuthor()">Изменить</button>
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

