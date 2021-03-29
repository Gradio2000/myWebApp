<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="user" scope="request" class="ru.laskin.myWebApp.model.User"/>

<html>
<body>
<h2>Hello ${authUser}!</h2>
<a href="/new_user">Добавить пользователя</a>
<br/>
<h2>Список зарегистрированных пользователей</h2>
<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <th>id</th>
        <th>Имя</th>
        <th>Логин</th>
        <th>email</th>
        <th>Администратор</th>
    </tr>
    <c:forEach var='user' items='${users}'>
        <tr>
            <td><c:out value="${user.userId}"></c:out></td>
            <td><c:out value="${user.name}"></c:out></td>
            <td><c:out value="${user.login}"></c:out></td>
            <td><c:out value="${user.email}"></c:out></td>
            <td><c:out value="${user.adminRole}"></c:out></td>
            <td><a href="users/update?id=${user.userId}"/>Редактировать</td>
            <td><a href="users/delete?id=${user.userId}"/>Удалить</td>
        </tr>
    </c:forEach>
</table>

<br/>
<a href="/logout">Выход</a>

</body>
</html>
