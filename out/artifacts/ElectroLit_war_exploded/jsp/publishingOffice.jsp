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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

    <div class="container" align="center">
        <div class="row">
            <div class="col"></div>

            <div class="col-6">

                <h3 class="display-4">Издательство</h3>

                <table id="tables" class="table">
                    <thead class="thead-dark">
                        <tr>
                            <c:forEach var="nameColumn" items="${columnList}">
                                <th scope="col">${nameColumn}</th>
                            </c:forEach>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="list" items="${tableList}">
                            <tr>
                                <td><input type="radio" name="r"></td>
                                <c:forEach var="item" items="${list}">
                                    <td>${item}</td>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <ul class="list-group list-group-horizontal">
                    <li onclick="window.location='/addPublishingOffice'" class="list-group-item"><a href="#">Добавить запись</a></li>
                    <li onclick="updatePO()" class="list-group-item"><a href="#">Изменить запись</a></li>
                    <li onclick="deletePO()" class="list-group-item"><a href="#">Удалить запись</a></li>
                </ul>

                <div align="center" class="mt-3">
                    <button type="button" class="btn btn-dark" onclick="window.location='/'">Назад</button>
                </div>

            </div>

            <div class="col"></div>
        </div>
    </div>

    <script>
        function getnumb() {
            var count;
            var z = document.getElementsByName("r");
            var table = document.getElementById('tables');
            for (var i = 0; i < z.length; i++) {
                if (z[i].checked) {
                    count = i;
                    break;
                }
            }
            return count;
        }
        function deletePO() {
            var count = getnumb();
            if (count == undefined) {
                alert("Выберите нужную строку!")
            }
            else {//   alert(table.rows[count + 1].cells[i].innerHTML);
                var xhr = new XMLHttpRequest();
                var table = document.getElementById('tables');
                var body = 'action=' + 'delete' + '&id_po=' + encodeURIComponent(table.rows[count + 1].cells[1].innerHTML);
                xhr.open("POST", "/publishingOffice", false);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
                xhr.send(body);
                window.location = '/publishingOffice';
            }
        }
        function updatePO() {
            var count = getnumb();
            if (count == undefined) {
                alert("Выберите нужную строку!")
            }
            var table = document.getElementById('tables');
            var location = '/updatePublishingOffice?=';
            location += encodeURI(table.rows[count + 1].cells[1].innerHTML)+"="+encodeURI(table.rows[count + 1].cells[2].innerHTML)
                +"="+encodeURI(table.rows[count + 1].cells[3].innerHTML);
            window.location = location;
        }
    </script>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

</body>
</html>