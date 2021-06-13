<%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 14.04.2021
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"       prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql"       prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml"       prefix="x"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"  %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html style="font-size: 16px;">
<head>
    <jsp:include page="../includes/settingsHeader.jsp"/>
    <title>Модуль администратора</title>
</head>
<body>
<jsp:include page="../includes/header.jsp"/>
<div class="wrapper">
    <div class="content">
        <div class="my-box my-box-hover" style="text-align: center" data-href="/allUsers">
            <h4 class="u-text u-text-1">ПОЛЬЗОВАТЕЛИ</h4>
        </div>
        <div class="my-box my-box-hover" style="text-align: center" data-href="/groupTests">
            <h4 class="u-text u-text-2">ГРУППЫ ТЕСТОВ</h4>
        </div>
        <div class="my-box my-box-hover" style="text-align: center" data-href="/allTests">
            <h4 class="u-text u-text-2">ТЕСТЫ</h4>
        </div>
    </div>
</div>

<jsp:include page="../includes/footer.jsp"/>

</body>
<jsp:include page="../includes/styles.jsp"/>
</html>
