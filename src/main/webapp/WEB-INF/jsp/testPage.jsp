<%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 29.03.2021
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"       prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql"       prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml"       prefix="x"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"  %>

<jsp:useBean id="tests" scope="request" class="ru.laskin.myWebApp.model.Test"/>


<html>
<head>
    <title>Tests</title>
</head>

    <form action="/startTest" method="post"/>
        <select name="testSelected">
            <c:forEach var="test" items="${allTest}">
                <option>${test.testName}</option>
            </c:forEach>
        </select>

        <input type="submit" value="Начать"/>
    <form/>

</body>
</html>
