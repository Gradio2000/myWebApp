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
<jsp:useBean id="answer" class="ru.laskin.myWebApp.model.Answer"/>
<jsp:useBean id="test" class="ru.laskin.myWebApp.model.Test"/>
<jsp:useBean id="user" class="ru.laskin.myWebApp.model.User"/>
<html>
<head>
    <title>TestProcessing</title>
</head>
<body>
<h3>Оппа!</h3>
<form name="testResult" method="post" action="/testResult">
    <input type="hidden" name="testId" value="${testId}">
    <input type="hidden" name="userId" value="${userId}">
    <c:forEach var="question" items="${questions}">
        <option>${question.questionName}</option>
            <c:forEach var="answer" items="${question.answers}">
                <option>${answer.answerName} </option>
                <input type="checkbox" name="check">
            </c:forEach>
        </c:forEach>
    <input type="submit">
</form>

</body>
</html>
