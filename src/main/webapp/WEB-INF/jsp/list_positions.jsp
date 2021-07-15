
<%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 15.07.2021
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"       prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql"       prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml"       prefix="x"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"  %>
<jsp:useBean id="pos" class="ru.laskin.myWebApp.model.Position"/>
<html>
<head>
    <jsp:include page="../includes/settingsHeader.jsp"/>
    <title>Должности</title>
</head>
<body>
<jsp:include page="../includes/header.jsp"/>
<div class="wrapper">
    <div class="content">
        <form action="/editPosition" method="post">
            <table style="margin-left: auto;margin-right: auto;">
                <tr>
                    <th>id</th>
                    <th>Должность</th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach var="position" items="${positions}">
                    <input name="idPosition" hidden value="${position.idPosition}"/>
                    <tr>
                        <td>${position.idPosition}</td>
                        <td><input type="text" name="position" value="${position.position}"/>
                        </td>
                        <td><button class="btn danger" style="margin-right: initial" onclick="document.location = ''">Удалить</button></td>
                    </tr>
                </c:forEach>
            </table><br/>
            <!-- Button trigger modal -->
            <button type="button" class="btn success" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                Добавить новую группу
            </button>
            <button class="btn info" type="submit">Сохранить и закрыть</button>
        </form>
    </div>
</div>
<jsp:include page="../includes/footer.jsp"/>
</body>
<jsp:include page="../includes/styles.jsp"/>
</html>
