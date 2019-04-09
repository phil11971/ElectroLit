<%@ page import="entities.BookEntity" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entities.AuthorEntity" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: Vladislav
  Date: 19.03.2019
  Time: 22:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title><c:out value='${pagename}'></c:out></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

    <div class="container" align="center">
        <div class="row">
            <div class="col"></div>

            <div class="col-6">

                <h3 class="display-4">Книга</h3>

                    <input hidden id="action" name="action" value="<%=request.getAttribute("action")%>">
                    <%
                        BookEntity a = (BookEntity) request.getAttribute("book");
                        Map<Integer,String> dictionary = (Map<Integer,String>) request.getAttribute("idPOList");
                        ArrayList<AuthorEntity> authors = (ArrayList<AuthorEntity>) request.getAttribute("authors");
                    %>
                        <table class="table">
                            <tr>
                                <td>
                                    <input hidden id="bookId" value="<%=a.getId_b()%>">
                                    Название книги:
                                </td>
                                <td>
                                    <input type="text" id="title" name="title" value="<%=a.getName()%>" required autocomplete="off">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Количество:
                                </td>
                                <td>
                                    <input type="number" id="pages" name="Cnt" min="0" max="1000" value="<%=a.getCnt()%>">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Цена:
                                </td>
                                <td>
                                    <input type="number" id="price" name="Price" min="1" max="10000" value="<%=a.getPrice()%>">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Дата издания:
                                </td>
                                <td>
                                    <input type="number" id="year" name="BookYear" min="1000" max="3000" value="<%=a.getYear_pub()%>">
                                </td>
                            </tr>
                            <tr>
                                <td>Издательство:
                                </td>
                                <td>
                                    <select id="poId">
                                        <c:set var="idpo" value="${idpo}"/>
                                        <c:forEach var="i" items="${idPOList}">
                                            <c:if test = "${i.key == idpo}">
                                                <option selected value =  ${i.key} >
                                                        ${i.value}
                                                </option>
                                            </c:if>
                                            <c:if test = "${i.key != idpo}">
                                                <option value =  ${i.key} >
                                                        ${i.value}
                                                </option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Авторы:
                                </td>
                                <td>
                                        <select id="authors" class="custom-select" multiple>
                                            <%for(AuthorEntity authorEntity:authors)
                                            {
                                                if(!a.getAuthorEntities().contains(authorEntity)){%>
                                                    <option value="<%=authorEntity.getId_a()%>"><%=authorEntity.getLname()+" "+authorEntity.getFname()%></option>
                                                <%}%>
                                                <%if(a.getAuthorEntities().contains(authorEntity)){%>
                                                    <option selected value="<%=authorEntity.getId_a()%>"><%=authorEntity.getLname()+" "+authorEntity.getFname()%></option>
                                                <%}%>
                                            <%}%>
                                        </select>
                                </td>
                            </tr>
                        </table>

                <div align="center" class="mt-3">
                    <button type="button" class="btn btn-dark" onclick="window.location='/book'">Назад</button>
                    <button type="button" class="btn btn-dark" onclick="editBook()">Создать</button>
                </div>

            </div>

            <div class="col"></div>
        </div>
    </div>


    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <script>
        function editBook() {
            var action = document.getElementById("action");
            var bookId = document.getElementById("bookId");
            var title = document.getElementById("title");
            var year = document.getElementById("year");
            var pages = document.getElementById("pages");
            var price = document.getElementById("price");
            var poId = document.getElementById("poId");
            var authors = Array.prototype.slice.call(document.querySelectorAll('#authors option:checked'),0).map(function(v,i,a) {
                return v.value;
            });

            if (title.value === "" || year.value === "" || pages.value === "" || price.value === "" || poId.value === "") {
                alert("Введите недостающие поля");
                return;
            }

            var xhr = new XMLHttpRequest();
            var body = 'action=' + encodeURIComponent(action.value) +'&bookId=' + encodeURIComponent(bookId.value) +'&title=' + encodeURIComponent(title.value) + '&year=' + encodeURIComponent(year.value)+ '&pages=' + encodeURIComponent(pages.value)+ '&price=' + encodeURIComponent(price.value)+ '&poId=' + encodeURIComponent(poId.value);
            for (var i=0; i<authors.length; i++){
                body += '&authors='+authors[i];
            }

            xhr.open('POST', '/editBook', false);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
            xhr.send(body);
            window.location = '/book';
        }
    </script>
</body>
</html>