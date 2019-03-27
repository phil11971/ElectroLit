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
                    <td><b>ID книги: </b></td>
                    <td id="bookId"></td></tr>
                    <td>
                    <td><b>Название:</b></td>
                    <td><input required  type="text" id="title" name="title"></td></tr>
                    <td>
                    <td><b>Год издания:</b></td>
                    <td><input required type="number" id="year"></td></tr>
                    <td>
                    <td><b>Количество книг:</b></td>
                    <td><input required type="number" id="pages"></td></tr>
                    <td>
                    <td><b>Цена, р:</b></td>
                    <td><input required type="number" id="price"></td></tr>
                    <td>
                    <td><b>Издательство:</b></td>
                    <td>
                        <select id = "poId" >
                            <c:forEach var="i" items="${idPOList}">
                                <option value =  ${i.key}>
                                        ${i.value}
                                </option>
                            </c:forEach>
                        </select></td></tr>
                </table>

                <div align="center" class="mt-3">
                    <button type="button" class="btn btn-dark" onclick="window.location = '/book'">Назад</button>
                    <button type="button" class="btn btn-dark" onclick="updateBook()">Изменить</button>
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
        var title = decodeURI(arr[2]);
        var year = decodeURI(arr[3]);
        var pages = decodeURI(arr[4]);
        var price = decodeURI(arr[5]);
        var poId = decodeURI(arr[6]);

        document.getElementById("bookId").innerHTML = decodeURI(bookId);
        document.getElementById("title").value = decodeURI(title);
        document.getElementById("year").value = decodeURI(year);
        document.getElementById("pages").value = decodeURI(pages);
        document.getElementById("price").value = decodeURI(price);
        document.getElementById("poId").value = decodeURI(poId);

        function updateBook() {
            var title = document.getElementById("title");
            var year = document.getElementById("year");
            var pages = document.getElementById("pages");
            var price = document.getElementById("price");
            var poId = document.getElementById("poId");
            var xhr = new XMLHttpRequest();
            var body = 'bookId=' + bookId + '&title=' + encodeURIComponent(title.value) + '&year=' + encodeURIComponent(year.value)+ '&pages=' + encodeURIComponent(pages.value)+ '&price=' + encodeURIComponent(price.value)+ '&poId=' + encodeURIComponent(poId.value);
            xhr.open("POST", "/updateBook", false);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
            xhr.send(body);
            window.location='/book';
        }
    </script>
</body>
</html>

