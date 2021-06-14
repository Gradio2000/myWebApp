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
<%@ taglib prefix="cf" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="groupTest" scope="session" class="ru.laskin.myWebApp.model.GroupTest"/>

<html style="font-size: 16px;">
<head>
    <jsp:include page="../includes/settingsHeader.jsp"/>
    <title>Главная</title>
</head>
<body class="u-body">
<jsp:include page="../includes/header.jsp"/>
<div class="wrapper">
    <div class="content">
        <c:forEach  var="group" items="${allTestGroup}">
            <cf:if test="${group.testList.size() != 0}">
                <div class="my-box">
                    <h4>${group.name}</h4>
                    <c:forEach var="test" items="${group.testList}">
                        <c:if test="${test.questions.size() != 0}">
                            <p>${test.testName}</p>
                            <p><button type="button" class="u-btn" data-href="/getTest?testId=${test.testId}">Начать тест</button></p>
                        </c:if>
                    </c:forEach>
                </div>
            </cf:if>
        </c:forEach>
    </div>
</div>
<jsp:include page="../includes/footer.jsp"/>
</body>
<jsp:include page="../includes/styles.jsp"/>
</html>