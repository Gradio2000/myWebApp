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
<h2>Войти</h2>
        <sf:form action="/login/process" method="post">
            <label>Логин</label>
            <input type="text" name="login" size="50">
            <br/>
            <br/>
            <label>Пароль</label>
            <input type="password" name="password" size="20">

            <br/>
            <br/>
            <input type="submit">
        </sf:form>
<c:if test="${error}">
    <p>Ошибка входа</p>
</c:if>

</body>
</html>
