<%--
  Created by IntelliJ IDEA.
  User: Xoma163
  Date: 02.04.2018
  Time: 16:01
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
        <h1>Изменение записи</h1>
        <table>
            <td>
            <td><b>ID издательства: </b></td>
            <td id="poId"></td></tr>
            <td>
            <td><b>Название: </b></td>
            <td><input required type="text" id="namePO"></td></tr>
            <td>
            <td><b>Адрес: </b></td>
            <td><input required type="text" id="adr"></td></tr>
        </table>
        <td><input class="submit1" type="button" value="Назад" onclick= "window.location='/publishingOffice'"></td>
        <td><input class="submit2" type="button" value="Изменить" onclick="updatePO()"></td>
    </div>
</section>
<script>
    var locations = document.location.toString();
    var arr=locations.split('=');

    var poId = decodeURI(arr[1]);
    var namePO = decodeURI(arr[2]);
    var adr = decodeURI(arr[3]);

    document.getElementById("poId").innerHTML = decodeURI(poId);
    document.getElementById("namePO").value = decodeURI(namePO);
    document.getElementById("adr").value = decodeURI(adr);

    function updatePO() {
        var namePO = document.getElementById("namePO");
        var adr = document.getElementById("adr");
        var xhr = new XMLHttpRequest();
        var body = 'poId='+poId+'&namePO=' + encodeURIComponent(namePO.value)+'&adr=' + encodeURIComponent(adr.value);
        xhr.open("POST", "/updatePublishingOffice", false);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        xhr.send(body);
        window.location='/publishingOffice';
    }
</script>
</body>
</html>
