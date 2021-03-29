<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 26.03.2021
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" scope="request" class="ru.laskin.myWebApp.model.User"/>

<html>
<head>
    <title>Приветствие</title>
</head>
<body>
Привет, ${authUser}!
    <br/>
    <a href="/allUsers">Все пользователи</a>

    <br/>
    <a href="/logout">Выход</a>
</body>
</html>