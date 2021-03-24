<%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 24.03.2021
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add user</title>
</head>
<body>
    <form action="/new_user" method="post">
        <input type="text" size="20" name="login">
        <input type="email" size="20" name="email">
        <input type="checkbox" name="adminRole">
        <input type="checkbox" name="userRole">
        <input type="submit">
    </form>
</body>
</html>
