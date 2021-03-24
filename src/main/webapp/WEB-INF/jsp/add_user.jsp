<%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 24.03.2021
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"       prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql"       prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml"       prefix="x"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"  %>

<html>
<head>
    <title>Add user</title>
</head>
<body>
    <sf:form action="/new_user" method="post" modelAttribute="user">
        <div>
            <sf:label path="login">Логин</sf:label>
            <sf:input path="login"/>
            <sf:errors path="login"/>
        </div>

        <div>
            <sf:label path="email">E-mail</sf:label>
            <sf:input path="email"/>
            <sf:errors path="email"/>
        </div>

        <div>
            <sf:label path="adminRole">Администратор</sf:label>
            <sf:checkbox path="adminRole"/>
            <sf:errors path="adminRole"/>
        </div>
        <input type="submit">
    </sf:form>

</body>
</html>
