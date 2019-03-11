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
            <td><b>ID автора: </b></td>
            <td id="aId"></td></tr>
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
            <td><input required type="text" id="mail"></td></tr>
            <td>
            <td><b>Дата рождения: </b></td>
            <td><input required type="text" id="dob"></td></tr>
        </table>
        <td><input class="submit1" type="button" value="Назад" onclick= "window.location='/author'"></td>
        <td><input class="submit2" type="button" value="Изменить" onclick="updateAuthor()"></td>
    </div>
</section>
<script>
    var locations = document.location.toString();
    var arr=locations.split('=');

    var aId = decodeURI(arr[1]);
    var lname = decodeURI(arr[2]);
    var fname = decodeURI(arr[3]);
    var patr = decodeURI(arr[4]);
    var mail = decodeURI(arr[5]);
    var dob = decodeURI(arr[6]);

    document.getElementById("aId").innerHTML = decodeURI(aId);
    document.getElementById("lname").value = decodeURI(lname);
    document.getElementById("fname").value = decodeURI(fname);
    document.getElementById("patr").value = decodeURI(patr);
    document.getElementById("mail").value = decodeURI(mail);
    document.getElementById("dob").value = decodeURI(dob);


    function updateAuthor() {
        var lname = document.getElementById("lname");
        var fname = document.getElementById("fname");
        var patr = document.getElementById("patr");
        var mail = document.getElementById("mail");
        var dob = document.getElementById("dob");

        var xhr = new XMLHttpRequest();
        var body = 'aId='+aId+'&lname=' + encodeURIComponent(lname.value)+'&fname=' + encodeURIComponent(fname.value)+'&patr=' + encodeURIComponent(patr.value)+'&mail=' + encodeURIComponent(mail.value)+'&dob=' + encodeURIComponent(dob.value);
        xhr.open("POST", "/updateAuthor", false);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        xhr.send(body);
        window.location='/author';
    }
</script>
</body>
</html>
