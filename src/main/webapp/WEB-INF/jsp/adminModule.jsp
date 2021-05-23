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
<div class="content">
    <section class="main-content">
        <section class="u-clearfix u-section-1" id="sec-8947">
                <div class="u-clearfix u-sheet u-sheet-1">
                    <div class="u-align-center u-container-style u-grey-5 u-group u-radius-17 u-shape-round u-group-1" data-href="/allUsers">
                        <div class="u-container-layout u-valign-middle-lg u-valign-middle-md u-valign-middle-sm u-valign-middle-xs u-container-layout-1">
                            <h4 class="u-text u-text-1">ПОЛЬЗОВАТЕЛИ</h4>
                        </div>
                    </div>
                    <div class="u-align-center u-container-style u-grey-5 u-group u-radius-17 u-shape-round u-group-1" data-href="/groupTests">
                        <div class="u-container-layout u-valign-middle u-container-layout-2">
                            <h4 class="u-text u-text-2">ГРУППЫ ТЕСТОВ</h4>
                        </div>
                    </div>
                    <div class="u-align-center u-container-style u-grey-5 u-group u-radius-17 u-shape-round u-group-1" data-href="/allTests">
                        <div class="u-container-layout u-valign-middle u-container-layout-2">
                            <h4 class="u-text u-text-2">ТЕСТЫ</h4>
                        </div>
                    </div>
                </div>
        </section>
    </section>
</div>

<jsp:include page="../includes/footer.jsp"/>

</body>
<jsp:include page="../includes/styles.jsp"/>
</html>
