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
</head>
<body>
<section class="container">
    <div class="login">
        <h1>Добавление записи</h1>
        <table>
            <td>
            <td><b>Название: </b></td>
            <td><input required type="text" id="namePO"></td></tr>
            <td>
            <td><b>Адрес: </b></td>
            <td><input required type="text" id="adr"></td></tr>
        </table>
        <td><input class="submit1" type="button" value="Назад" onclick= "window.location='/publishingOffice'"></td>
        <td><input class="submit2" type="button" value="Создать" onclick="addPO()"></td>
    </div>
</section>

<script>
    function addPO() {
        var namePlace = document.getElementById("namePO");
        var adr = document.getElementById("adr");
        var xhr = new XMLHttpRequest();
        var body = 'namePO=' + encodeURIComponent(namePlace.value) + '&adr=' + encodeURIComponent(adr.value);
        xhr.open('POST', '/addPublishingOffice', false);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        xhr.send(body);
        window.location = '/publishingOffice';
    }
</script>
</body>
</html>
