<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="../includes/settingsHeader.jsp"/>
    <title>Статистика</title>
</head>
<body>
<jsp:include page="../includes/header.jsp"/>
<div class="wrapper">
    <div class="content">
        <table   style="width: 60%;margin-left: auto;margin-right: auto;">
            <tr>
                <th>id</th>
                <th>Должность</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var='position' items='${}'>
                <tr>
                    <td><c:out value="${user.userId}"></c:out></td>
                    <td><c:out value="${user.position.position}"></c:out></td>
                    <td><c:out value="${user.name}"></c:out></td>
                    <td><c:out value="${user.email}"></c:out></td>
                    <td><c:out value="${user.adminRole}"></c:out></td>
                    <td><button class="btn info" onclick="document.location = 'users/statistic?id=${user.userId}'">Статистика</button></td>
                    <td><button class="btn warning" onclick="document.location = 'users/update?id=${user.userId}'">Редактировать</button></td>
                    <td><button class="btn danger" onclick="document.location = 'users/delete?id=${user.userId}'">Удалить</button></td>
                </tr>
            </c:forEach>
        </table><br/>
    </div>
</div>
<jsp:include page="../includes/footer.jsp"/>
</body>
<jsp:include page="../includes/styles.jsp"/>
</html>