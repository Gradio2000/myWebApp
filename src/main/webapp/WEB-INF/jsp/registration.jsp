<%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 26.03.2021
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"       prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql"       prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml"       prefix="x"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"  %>

<%--<html>--%>
<%--<head>--%>
<%--    <title>Login</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<h2>Регистрация нового пользователя</h2>--%>
<%--<sf:form action="/new_user" method="post" modelAttribute="user">--%>
<%--    <div>--%>
<%--        <sf:hidden path="userId"/>--%>
<%--        <sf:hidden path="email"/>--%>
<%--        <sf:hidden path="adminRole"/>--%>
<%--        <sf:hidden path="name"/>--%>
<%--    </div>--%>

<%--    <div>--%>
<%--        <sf:label path="login">Логин</sf:label>--%>
<%--        <sf:input path="login"/>--%>
<%--        <sf:errors path="login"/>--%>
<%--    </div>--%>

<%--    <div>--%>
<%--        <sf:label path="password">Пароль</sf:label>--%>
<%--        <sf:password path="password"/>--%>
<%--        <sf:errors path="password"/>--%>
<%--    </div>--%>

<%--    <div>--%>
<%--        <sf:label path="confirmPassword">Подтвердите пароль</sf:label>--%>
<%--        <sf:password path="confirmPassword"/>--%>
<%--        <sf:errors path="confirmPassword"/>--%>
<%--    </div>--%>

<%--    <input type="submit">--%>
<%--</sf:form>--%>
<%--</body>--%>
<%--</html>--%>


<html>
<style>
    body {font-family: Arial, Helvetica, sans-serif;}
    * {box-sizing: border-box}

    /* Full-width input fields */
    input[type=text], input[type=password] {
        width: 100%;
        padding: 15px;
        margin: 5px 0 22px 0;
        display: inline-block;
        border: none;
        background: #f1f1f1;
    }

    input[type=text]:focus, input[type=password]:focus {
        background-color: #ddd;
        outline: none;
    }

    hr {
        border: 1px solid #f1f1f1;
        margin-bottom: 25px;
    }

    /* Set a style for all buttons */
    button {
        background-color: #4CAF50;
        color: white;
        padding: 14px 20px;
        margin: 8px 0;
        border: none;
        cursor: pointer;
        width: 100%;
        opacity: 0.9;
    }

    button:hover {
        opacity:1;
    }

    /* Extra styles for the cancel button */
    .cancelbtn {
        padding: 14px 20px;
        background-color: #f44336;
    }

    /* Float cancel and signup buttons and add an equal width */
    .cancelbtn, .signupbtn {
        float: left;
        width: 50%;
    }

    /* Add padding to container elements */
    .container {
        padding: 16px;
    }

    /* Clear floats */
    .clearfix::after {
        content: "";
        clear: both;
        display: table;
    }

    /* Change styles for cancel button and signup button on extra small screens */
    @media screen and (max-width: 300px) {
        .cancelbtn, .signupbtn {
            width: 100%;
        }
    }
</style>
<body>

<sf:form action="/new_user" style="border:1px solid #ccc" method="post" modelAttribute="user">
            <sf:hidden path="userId"/>
            <sf:hidden path="email"/>
            <sf:hidden path="adminRole"/>
            <sf:hidden path="name"/>
    <div class="container">
        <h1>Регистрация</h1>
        <p>Пожалуйста, заполните все поля для регистрации в системе.</p>
        <hr>

        <label><b>Логин</b></label>
        <input type="text" placeholder="Придумайте логин" name="login" required>
        <sf:errors path="login"/><br/>

        <label><b>Пароль</b></label>
        <input type="password" placeholder="Придумайте пароль" name="password" required>

        <label><b>Повторите пароль</b></label>
        <input type="password" placeholder="Введите пароль ещё раз" name="confirmPassword" required>
        <sf:errors path="confirmPassword"/>

        <div class="clearfix">
            <button type="button" class="cancelbtn" onclick="document.location='/login'">Отмена</button>
            <button type="submit" class="signupbtn">Зарегистрироваться</button>
        </div>
    </div>
</sf:form>

</body>
</html>