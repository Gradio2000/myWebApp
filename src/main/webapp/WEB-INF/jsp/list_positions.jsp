
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
        <sf:form action="/editPosition" method="post">
            <table style="margin-left: auto;margin-right: auto;">
                <tr>
                    <th>id</th>
                    <th>Должность</th>
                    <th></th>
                    <th></th>
                </tr>
                <jsp:useBean id="positions" scope="session" type="java.util.List"/>
                <c:forEach var="position" items="${positions}" varStatus="count">
                    <input name="companyId" type="hidden" value="${position.companyId}"/>
                    <input name="idPosition" hidden value="${position.idPosition}"/>
                    <tr>
                        <td>${position.idPosition}</td>
                        <td><input type="text" name="position" value="${position.position}"/></td>
                        <td><button type="button" id="${count.count}" class="btn danger" onclick="document.location = '/deletePosition?posId=${position.idPosition}'">Удалить</button> </td>
                    </tr>
                </c:forEach>
            </table><br/>
            <p>${errorMessage}</p>
            <!-- Button trigger modal -->
            <button type="button" class="btn success" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                Добавить должность
            </button>
            <button class="btn info" type="submit">Сохранить и закрыть</button>
        </sf:form>
    </div>
</div>

<%--модальная форма--%>
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel">Добавление должности</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <%--@elvariable id="pos" type="ru.laskin.myWebApp.model.Position"--%>
                <sf:form class="validForm" id="myForm" name="formName" action="/addPosition" method="POST" modelAttribute="pos">
                    <input name="companyId" type="hidden" value="${user.company.idCompany}"/>
                    <div class="form-floating">
                        <textarea name="position" class="form-control" placeholder="Введите должность" id="floatingTextarea" style="height: 100px" required></textarea>
                        <label for="floatingTextarea">Новая должность</label>
                    </div>

                    <br/>

                    <div class="modal-footer">
                        <button type="button" class="btn close" data-bs-dismiss="modal">Закрыть</button>
                        <button type="submit" class="btn info">Добавить</button>
                    </div>
                </sf:form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../includes/footer.jsp"/>
</body>
<jsp:include page="../includes/styles.jsp"/>
</html>
