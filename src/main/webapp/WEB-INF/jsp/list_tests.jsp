<%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 15.04.2021
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"       prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql"       prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml"       prefix="x"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"  %>

<jsp:useBean id="test" class="ru.laskin.myWebApp.model.Test"/>
<jsp:useBean id="groppTest" class="ru.laskin.myWebApp.model.GroupTest"/>

<html style="font-size: 16px;">
<head>
    <jsp:include page="../includes/settingsHeader.jsp"/>
    <title>Тесты</title>
</head>
<body>
<jsp:include page="../includes/header.jsp"/>
<div class="wrapper">
    <div class="content">
        <table>
        <tr>
            <th>id теста</th>
            <th>Наименование теста</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach var='test' items='${alltests}'>
            <tr>
                <td><c:out value="${test.testId}"></c:out></td>
                <td><c:out value="${test.testName}"></c:out></td>
                <td><button class="btn warning" onclick="document.location = 'tests/update?id=${test.testId}'">Редактировать</button></td>
                <td><button class="btn danger" onclick="document.location = 'tests/delete?id=${test.testId}'">Удалить</button></td>
            </tr>
        </c:forEach>
    </table>

    <!-- Button trigger modal -->
    <button type="button" class="btn success" data-bs-toggle="modal" data-bs-target="#staticBackdrop1">
        Добавить тест
    </button>


<div class="modal fade" id="staticBackdrop1" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel">Добавление теста</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <sf:form class="validForm" id="myForm" name="formName" action="/addTest" method="POST" modelAttribute="test">
                    <div>
                        <label>Группа тестов</label>
                        <select name="groupId">
                            <c:forEach var="group" items="${allgrouptest}">
                                <option value="${group.groupTestId}">${group.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-floating">
                        <textarea name="testName" class="form-control" placeholder="Введите название теста" id="floatingTextarea" style="height: 100px" required></textarea>
                        <label for="floatingTextarea">Название теста</label>
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

</div>
</div>
<jsp:include page="../includes/footer.jsp"/>
</body>
<jsp:include page="../includes/styles.jsp"/>
</html>
