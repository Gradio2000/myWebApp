<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="user" scope="request" class="ru.laskin.myWebApp.model.User"/>

<%--<html>--%>
<%--<body>--%>
<%--<h2>Hello ${authUser}!</h2>--%>
<%--<br/>--%>
<%--<h2>Список зарегистрированных пользователей</h2>--%>
<%--<table border="1" cellpadding="8" cellspacing="0">--%>
<%--    <tr>--%>
<%--        <th>id</th>--%>
<%--        <th>Должность</th>--%>
<%--        <th>Имя</th>--%>
<%--        <th>email</th>--%>
<%--        <th>Роль</th>--%>
<%--    </tr>--%>
<%--    <c:forEach var='user' items='${users}'>--%>
<%--        <tr>--%>
<%--            <td><c:out value="${user.userId}"></c:out></td>--%>
<%--            <td><c:out value="${user.position}"></c:out></td>--%>
<%--            <td><c:out value="${user.name}"></c:out></td>--%>
<%--            <td><c:out value="${user.email}"></c:out></td>--%>
<%--            <td><c:out value="${user.adminRole}"></c:out></td>--%>
<%--            <td><a href="users/update?id=${user.userId}"/>Редактировать</td>--%>
<%--            <td><a href="users/delete?id=${user.userId}"/>Удалить</td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>
<%--</table>--%>

<%--<br/>--%>
<%--<a href="/logout">Выход</a>--%>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        table {
            border-collapse: collapse;
            border-spacing: 0;
            width: 100%;
            border: 1px solid #ddd;
        }

        th, td {
            text-align: left;
            padding: 16px;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2
        }
    </style>
</head>
<body>

<h2>Добрый день, ${authUser}!</h2>
<br/>
<h2>Список зарегистрированных пользователей</h2>

<table>
        <tr>
            <th>id</th>
            <th>Должность</th>
            <th>Имя</th>
            <th>email</th>
            <th>Роль</th>
        </tr>
        </tr>
        <c:forEach var='user' items='${users}'>
            <tr>
                <td><c:out value="${user.userId}"></c:out></td>
                <td><c:out value="${user.position}"></c:out></td>
                <td><c:out value="${user.name}"></c:out></td>
                <td><c:out value="${user.email}"></c:out></td>
                <td><c:out value="${user.adminRole}"></c:out></td>
                <td><a href="users/update?id=${user.userId}"/>Редактировать</td>
                <td><a href="users/delete?id=${user.userId}"/>Удалить</td>
            </tr>
        </c:forEach>
</table>

</body>
</html>

</body>
</html>
