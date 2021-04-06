<%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 26.03.2021
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"       prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql"       prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml"       prefix="x"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"  %>

<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Регистрация нового пользователя</h2>
<sf:form action="/new_user" method="post" modelAttribute="user">
    <div>
        <sf:hidden path="userId"/>
        <sf:hidden path="email"/>
        <sf:hidden path="adminRole"/>
        <sf:hidden path="name"/>
    </div>

    <div>
        <sf:label path="login">Логин</sf:label>
        <sf:input path="login"/>
        <sf:errors path="login"/>
    </div>

    <div>
        <sf:label path="password">Пароль</sf:label>
        <sf:password path="password"/>
        <sf:errors path="password"/>
    </div>

    <div>
        <sf:label path="confirmPassword">Подтвердите пароль</sf:label>
        <sf:password path="confirmPassword"/>
        <sf:errors path="confirmPassword"/>
    </div>

    <input type="submit">
</sf:form>
</body>
</html>
