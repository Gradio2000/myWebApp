<%@ page import="ru.laskin.myWebApp.model.Question" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 15.04.2021
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"       prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql"       prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml"       prefix="x"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"  %>
<jsp:useBean id="tests" class="ru.laskin.myWebApp.model.Test"/>
<jsp:useBean id="q" class="ru.laskin.myWebApp.model.Question"/>
<html>
<head>
    <title>Test</title>
</head>
<body>
<sf:form action="/updateTest" method="post" modelAttribute="test">
    <sf:hidden path="testId"/>
    <sf:hidden path="groupId"/>

    <label><br>Название теста<br></label>
    <sf:input path="testName" size="200"/>
        <c:forEach var="ques" items="${test.questions}">
            <label><br>Вопрос id = ${ques.questionId}<br></label>
            <input name="question" value="${ques.questionName}"><br>
            <input hidden name="questionId" value="${ques.questionId}">
<%--            <input hidden name="testIdInQuestion" value="${ques.test.testId}">--%>
            <c:forEach var="answer" items="${ques.answers}">
                <input name="answer" value="${answer.answerName}"><br>
                <input hidden name="answerId" value="${answer.answerId}">
                <input hidden name="isRight" value="${answer.right}">
                <input hidden name="quesAnsId" value="${answer.question.questionId}">
            </c:forEach>
        </c:forEach>
    <input type="submit">
</sf:form>

</body>
</html>
