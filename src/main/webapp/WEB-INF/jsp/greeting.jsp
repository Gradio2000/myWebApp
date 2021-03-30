<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 26.03.2021
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"       prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql"       prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml"       prefix="x"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"  %>
<jsp:useBean id="user" scope="request" class="ru.laskin.myWebApp.model.User"/>
<jsp:useBean id="position" scope="request" class="ru.laskin.myWebApp.model.Position"/>

<html>
<head>
    <title>Приветствие</title>
</head>
<body>
<h2>
    Привет, ${authUser.name}!
</h2>

<h2>
    Заполните сведения о себе
</h2>
    <sf:form action="/reUpdate" method="post" modelAttribute="authUser">
    <div>
        <sf:hidden path="userId"/>
        <sf:hidden path="adminRole"/>
        <sf:hidden path="name"/>
        <sf:hidden path="password"/>
        <sf:hidden path="login"/>
    </div>
<br/>
    <div>
        <sf:label path="position">Должность</sf:label>
        <sf:select path="position">
        <c:forEach var="position" items="${posSet}">
        <option>${position}</option>
        </c:forEach>
        </sf:select>
    <div/>
<br/>
    <div>
        <sf:label path="email">E-mail</sf:label>
        <sf:input path="email"/>
    </div>
        <br/>


    <br/>
    <br/>

        <input type="submit">
    </sf:form>

        <br/>
        <br/>
        <a href="/logout">Выход</a>

</body>
</html>