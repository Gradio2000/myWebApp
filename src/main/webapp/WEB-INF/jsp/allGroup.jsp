
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"       prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql"       prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml"       prefix="x"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"  %>
<%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 05.05.2021
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:useBean id="group" class="ru.laskin.myWebApp.model.GroupTest"/>
<head>
    <jsp:include page="../includes/settingsHeader.jsp"/>
    <title>Группы тестов</title>
</head>
<body>
<jsp:include page="../includes/header.jsp"/>
<div class="wrapper">
    <div class="content">
        <form method="post" action="${pageContext.request.contextPath}/updateGroup">
            <table>
                    <tr>
                        <th>id группы</th>
                        <th>Группа тестов</th>
                        <th></th>
                    </tr>
                    <c:forEach var='group' items='${groupTests}'>
                        <input hidden name="grouptestId" value="${group.groupTestId}"/>
                        <input type="hidden" name="companyId" value="${group.companyId}"/>
                        <tr>
                            <td><c:out value="${group.groupTestId}"/></td>
                            <td width=100><textarea class="my-input" name="name">${group.name}</textarea></td>
                            <td><a href="${pageContext.request.contextPath}/group/delete?id=${group.groupTestId}" class="btn danger">Удалить</a> </td>
                        </tr>
                    </c:forEach>
            </table>

            <!-- Button trigger modal -->
            <button type="button" class="btn success" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                Добавить новую группу
            </button>
            <button class="btn info" type="submit">Сохранить и закрыть</button>
        </form>
    </div>
</div>


<%--модальная форма--%>
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel">Добавление группы</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <sf:form class="validForm" id="myForm" name="formName" action="/addGroup" method="POST" modelAttribute="groupTests">
                    <div class="form-floating">
                        <textarea name="name" class="form-control" placeholder="Введите группу" id="floatingTextarea" style="height: 100px" required></textarea>
                        <label for="floatingTextarea">Наименование группы</label>
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
