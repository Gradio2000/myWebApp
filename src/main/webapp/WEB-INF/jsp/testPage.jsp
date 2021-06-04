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

<jsp:useBean id="test" scope="request" class="ru.laskin.myWebApp.model.Test"/>
<jsp:useBean id="groupTest" scope="request" class="ru.laskin.myWebApp.model.GroupTest"/>
<jsp:useBean id="question" class="ru.laskin.myWebApp.model.Question"/>
<jsp:useBean id="answer" class="ru.laskin.myWebApp.model.Answer"/>

<html style="font-size: 16px;">
<head>
    <jsp:include page="../includes/settingsHeader.jsp"/>
    <title>Главная</title>
</head>
<body class="u-body">
<jsp:include page="../includes/header.jsp"/>
<div class="wrapper">
    <div class="content">
        <section class="u-clearfix u-grey-10 u-section-1" id="sec-6192">
            <div class="u-clearfix u-sheet u-sheet-1">
                <div class="u-align-center u-container-style u-group u-group-1">
                    <div class="u-container-layout u-valign-middle u-container-layout-1">
                    </div>
                </div>
                <c:forEach  var="group" items="${allTestGroup}">
                    <cf:if test="${group.testList.size() != 0}">
                        <h3>${group.name}</h3>
                        <c:forEach var="test" items="${group.testList}">
                            <c:if test="${test.questions.size() != 0}">
                                <h4>${test.testName}</h4>
                                <p><button type="button" class="u-btn" data-href="/getTest?testId=${test.testId}">Начать тест</button></p>
                            </c:if>
                        </c:forEach>
                    </cf:if>
                </c:forEach>
            </div>
        </section>
    </div>
</div>
<jsp:include page="../includes/footer.jsp"/>
</body>
<jsp:include page="../includes/styles.jsp"/>
</html>