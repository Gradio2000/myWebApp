<%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 05.06.2021
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"       prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql"       prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml"       prefix="x"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"  %>


<html>
<head>
    <jsp:include page="../includes/settingsHeader.jsp"/>
    <title>Изменение пароля</title>
</head>
<body>
<jsp:include page="../includes/header.jsp"/>
<div class="wrapper">
    <div class="content">
        <sf:form id="form" action="/changePassword" method="post" onsubmit="return confirmPass()">
            <table style="  width: 1500px">
                <tr>
                    <td style="width: 300px">Новый пароль</td>
                    <td width=100><input id="password" name="password" type="password" placeholder="Введите пароль" required></td>
                </tr>
                <tr>
                    <td style="width: 300px">Подтвердите пароль</td>
                    <td width=100><input id="confirmPassword" type="password" name="confirmPassword" placeholder="Подтвердите пароль" required></td>
                    <sf:errors path="confirmPassword"/>
                </tr>
            </table>
            <input type="submit" class="btn success" value="Сохранить пароль"></button>
            <button type="button" class="btn danger">Отмена</button>
        </sf:form>
    </div>
</div>
<jsp:include page="../includes/footer.jsp"/>
<script>
    function confirmPass() {
        // console.log($("#password"    ).val());
        if ($("#password").val() !== $("#confirmPassword").val()) {
            alert("Пароли не совпадают");
            return false;
        }
    }
</script>
</body>
<jsp:include page="../includes/styles.jsp"/>

</html>
