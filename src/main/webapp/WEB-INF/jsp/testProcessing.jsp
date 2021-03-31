<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 30.03.2021
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="question" class="ru.laskin.myWebApp.model.Question"/>
<html>
<head>
    <title>TestProcessing</title>
</head>
<body>
<h3>Оппа!</h3>
        <c:forEach var="question" items="${questions}">
            <option>${question.questionName}</option>
            <option>${question.answers}</option>
        </c:forEach>
</body>
</html>
