<%--
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
<jsp:useBean id="questi" class="ru.laskin.myWebApp.model.Question"/>
<html>
<head>
    <title>Test</title>
</head>
<body>
<sf:form action="/updateTest" method="post" modelAttribute="test">
    <sf:hidden path="testId"/>
    <label><br>Название теста<br></label>
    <sf:input path="testName" size="200"/>
        <c:forEach var="ques" items="${test.questions}">
            <label><br>Вопрос id = ${ques.questionId}<br></label>
            <input value="${ques.questionName}"><br>
            <c:forEach var="answer" items="${ques.answers}">
                <input value="${answer.answerName}"><br>
            </c:forEach>
        </c:forEach>
    <input type="submit">
</sf:form>
</body>
</html>
