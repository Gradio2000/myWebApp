<%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 29.03.2021
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"          prefix="c"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"           prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql"           prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml"           prefix="x"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"     prefix="fn"  %>
<%@ taglib uri="http://www.springframework.org/tags/form"   prefix="sf"  %>

<jsp:useBean id="position" scope="request" class="ru.laskin.myWebApp.model.Position"/>

<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Редактирование пользователя</h2>
<sf:form action="/updateUser" method="post" modelAttribute="user">
    <div>
        <sf:hidden path="userId"/>
        <sf:hidden path="adminRole"/>
        <sf:hidden path="login"/>
    </div>
    <div>
        <sf:label path="name">Ф.И.О.</sf:label>
        <sf:input path="name"/>
        <sf:errors path="name"/>
    </div>


    <div>
        <sf:label path="email">E-mail</sf:label>
        <sf:input path="email"/>
        <sf:errors path="email"/>
    </div>

<div>
    <sf:label path="position">Должность</sf:label>
    <sf:select path="position">
        <c:forEach var="position" items="${posSet}">
        <option>${position}</option>
        </c:forEach>
    </sf:select>
    <div/>


    <input type="submit">
</sf:form>
</body>
</html>

