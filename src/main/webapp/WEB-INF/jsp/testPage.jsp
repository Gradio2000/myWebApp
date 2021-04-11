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

<jsp:useBean id="test" scope="request" class="ru.laskin.myWebApp.model.Test"/>



<html>
<head>
    <title>Tests</title>
</head>

<SCRIPT type="text/javascript">

    <!--

    function validate_form ( )
    {
        valid = true;

        if ( document.test_form.testId.selectedIndex == 0 )
        {
            alert ( "Пожалуйста, выберите тест." );
            valid = false;
        }

        return valid;
    }

    //-->

</SCRIPT>

<body>
    <p>Добый день, ${user.name}</p><br>
    <p>Здесь будут описания задачи</p>
        <form name="test_form" action="/startTest" method="post" modelAttribute="test" onsubmit="return validate_form()"/>
        <select name="testId">
            <option value="">Выберете тест</option>
            <c:forEach var="test" items="${allTest}">
                <option value="${test.testId}">${test.testName}</option>
            </c:forEach>
        </select>
        <input type="submit" name="send" value="Начать тест">
<form/>
<br/>
<a href="/logout">Выход</a>
</body>
</html>
