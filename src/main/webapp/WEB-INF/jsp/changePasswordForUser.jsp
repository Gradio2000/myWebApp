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
<div class="wrapper">
  <div class="content">
    <div class="my-box">
      <form id="form" action="/recovery" method="post" onsubmit="return confirmPass()">
        <input type="hidden" name="key" value="${key}">
        <input type="hidden" name="userId" value="${userId}">
        <h5>Новый пароль</h5>
        <input class="my-input" id="password" name="password" type="password" placeholder="Введите пароль" required>

        <h5>Подтвердите пароль</h5>
        <input class="my-input" id="confirmPassword" type="password" name="confirmPassword" placeholder="Подтвердите пароль" required>

        <br>
        <br>
        <input type="submit" class="btn success" value="Сохранить пароль"></button>
      </form>
    </div>
  </div>
</div>
<script>
  function confirmPass() {
    if ($("#password").val() !== $("#confirmPassword").val()) {
      alert("Пароли не совпадают");
      return false;
    }
  }
</script>
</body>
<jsp:include page="../includes/styles.jsp"/>

</html>
