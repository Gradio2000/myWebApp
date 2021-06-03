<%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 03.06.2021
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"       prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql"       prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml"       prefix="x"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"  %>
<jsp:useBean id="user" scope="request" type="ru.laskin.myWebApp.model.User"/>


<html>
<head>
    <jsp:include page="../includes/settingsHeader.jsp"/>
    <title>Личный кабинет</title>
</head>
<body>
<jsp:include page="../includes/header.jsp"/>
<div class="wrapper">
    <div class="content">
        <sf:form action="editUser" method="post" modelAttribute="user">
            <sf:hidden path="userId"/>
            <sf:hidden path="email"/>
            <sf:hidden path="adminRole"/>
            <table style="width: 1500px">
                <tr>
                    <td style="width: 300px">Фамилия Имя Отчество</td>
                    <td width=100><textarea id="nameInput" name="name" class="dis" disabled>${user.name}</textarea></td>
                </tr>
                <tr>
                    <td style="width: 300px">Должность</td>
                    <td width=100><textarea id="position" name="position" class="dis"  disabled>${user.position}</textarea></td>
                </tr>
                <tr>
                    <td style="width: 300px">Логин</td>
                    <td width=100><textarea id="login" name="login" class="dis" disabled>${user.login}</textarea></td>
                </tr>

            <%--                <tr>--%>
<%--                    <td style="width: 300px">Новый пароль</td>--%>
<%--                    <td width=100><input id="password" name="password" type="password" placeholder="Введите пароль" disabled value="${user.password}"></td>--%>
<%--                    <td><button id="3" type="button" onclick="antidisabled('password', this.id)" class="btn warning">Редактировать</button> </td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td style="width: 300px">Подтвердите пароль</td>--%>
<%--                    <td width=100><input type="password" name="confirmPassword" placeholder="Подтвердите пароль"></td>--%>
<%--                    <sf:errors path="confirmPassword"/>--%>
<%--                </tr>--%>
            </table>
            <button id="3" type="button" onclick="antidisabled()" class="btn warning">Редактировать</button>
            <button id="4" type="button" onclick="document.location = '/adminModule'" class="btn danger" hidden>Отмена</button>
            <button id="submit" type="submit" class="btn success" hidden>Сохранить</button>
        </sf:form>

    </div>
</div>
<jsp:include page="../includes/footer.jsp"/>
</body>
<jsp:include page="../includes/styles.jsp"/>
</html>
<script>
    function antidisabled(){
        const el = document.getElementsByClassName("dis");
        for (let i = 0; i < el.length; i++) {
            el[i].removeAttribute("disabled");
        }
        document.getElementById("3").setAttribute("hidden", "hidden");
        document.getElementById("submit").removeAttribute("hidden");
        document.getElementById("4").removeAttribute("hidden");
    }
</script>
