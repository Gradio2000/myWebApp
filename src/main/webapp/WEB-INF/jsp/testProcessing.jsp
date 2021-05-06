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
    <title>Прохождение теста</title>
</head>
<body>


<sf:form name="testResult" method="post" action="/testResult" modelAttribute="tests">
    <input hidden name="testId" value="${tests.testId}">
    <input hidden name="userId" value="${users.userId}">
    <c:forEach var="ques" items="${tests.questions}">
        <input hidden name="questionId" value="${ques.questionId}">
        <p>${ques.questionName}</p>
            <c:forEach var="answer" items="${ques.answers}">
                <input type="checkbox" name="check" value="${answer.answerId}">${answer.answerName}<br>
            </c:forEach>
    </c:forEach>
    <input type="submit">
</sf:form>

</body>
</html>
