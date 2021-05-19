<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %><%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 03.04.2021
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"          prefix="c"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"           prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql"           prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml"           prefix="x"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"     prefix="fn"  %>
<%@ taglib uri="http://www.springframework.org/tags/form"   prefix="sf"  %>

<html>
<head>
    <title>Result</title>
</head>
<body>
<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <th>Дата</th>
        <th>Имя</th>
        <th>Должность</th>
        <th>Название теста</th>
        <th>Количество вопросов</th>
        <th>Количество правильных ответов</th>
        <th>Количество неправильных ответов</th>
    </tr>
    <td>${data}</td>
    <td>${name}</td>
    <td>${position}</td>
    <td>${testName}</td>
    <td>${quesCount}</td>
    <td>${trueAnswer}</td>
    <td>${falseAnswer}</td>


</table>

<br/>
<a href="/logout">Выход</a>
</body>
</html>
