<%--
  Created by IntelliJ IDEA.
  User: Xoma163
  Date: 02.04.2018
  Time: 15:55
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

                <h3 class="display-4">Добавление записи</h3>

                <table class="table">
                    <td>
                    <td><b>Фамилия: </b></td>
                    <td><input required type="text" id="lname"></td></tr>
                    <td>
                    <td><b>Имя: </b></td>
                    <td><input required type="text" id="fname"></td></tr>
                    <td>
                    <td><b>Отчество: </b></td>
                    <td><input required type="text" id="patr"></td></tr>
                    <td>
                    <td><b>Мыло: </b></td>
                    <td><input required type="email" id="mail"></td></tr>
                    <td>
                    <td><b>Дата рождения: </b></td>
                    <td><input required type="date" id="dob"></td></tr>
                </table>

                <div align="center" class="mt-3">
                    <button type="button" class="btn btn-dark" onclick="window.location = '/author'">Назад</button>
                    <button type="button" class="btn btn-dark" onclick="addAuthor()">Создать</button>
                </div>

            </div>

            <div class="col"></div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <script>
        function addAuthor() {
            var lname = document.getElementById("lname");
            var fname = document.getElementById("fname");
            var patr = document.getElementById("patr");
            var mail = document.getElementById("mail");
            var dob = document.getElementById("dob");

            var xhr = new XMLHttpRequest();
            var body = 'lname=' + encodeURIComponent(lname.value) + '&fname=' + encodeURIComponent(fname.value)+ '&patr=' + encodeURIComponent(patr.value)+ '&mail=' + encodeURIComponent(mail.value)+ '&dob=' + encodeURIComponent(dob.value);
            xhr.open('POST', '/addAuthor', false);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
            xhr.send(body);
            window.location = '/author';
        }
    </script>
</body>
</html>
