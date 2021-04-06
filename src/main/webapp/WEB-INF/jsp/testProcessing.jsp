<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 30.03.2021
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"       prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql"       prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml"       prefix="x"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"  %>
<%@ taglib prefix="z" uri="http://www.springframework.org/tags/form" %>


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
        <p>${question.questionName}<p>
        <input name="questionId" type="hidden" value="${question.questionId}">
            <c:forEach var="answer" items="${question.answers}">
                <input type="checkbox" name="check" value="${answer.answerId}">${answer.answerName}<br>
            </c:forEach>
        </c:forEach>
    <br/>
    <input type="submit">
</form>

</body>
</html>
