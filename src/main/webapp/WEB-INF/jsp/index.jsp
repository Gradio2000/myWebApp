<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="user" scope="request" class="ru.laskin.myWebApp.model.User"/>

<html>
<body>
<h2>Hello Alex!</h2>
<a href="/add_user">Добавить пользователя</a>
<br/>
<h2>Список пользователей</h2>

<c:forEach var='user' items='${users}'>
    <c:out value="${user.login}"></c:out>
    <c:out value="${user.email}"></c:out>
    <c:out value="${user.adminRole}"></c:out>
    <c:out value="${user.userRole}"></c:out>
    <br/>
</c:forEach>
</body>
</html>
