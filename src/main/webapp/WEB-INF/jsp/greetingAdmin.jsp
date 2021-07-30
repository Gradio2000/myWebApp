<%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 30.07.2021
  Time: 18:46
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"       prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql"       prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml"       prefix="x"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"  %>
<jsp:useBean id="user" scope="request" class="ru.laskin.myWebApp.model.User"/>
<jsp:useBean id="position" scope="request" class="ru.laskin.myWebApp.model.Position"/>

<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<html>
<style>
    body {font-family: Arial, Helvetica, sans-serif;}
    * {box-sizing: border-box}

    /*Full-width input fields*/
    input[type=text], input[type=email] {
        width: 100%;
        padding: 15px;
        margin: 5px 0 22px 0;
        display: inline-block;
        border: none;
        background: #f1f1f1;
    }

    input[type=text]:focus, input[type=email]:focus {
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
        width: 50%;
        margin-left: auto;
        margin-right: auto;
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


    /*the container must be positioned relative:*/
    .custom-select {
        position: relative;
        font-family: Arial;
    }
    .custom-select select {
        display: none; /*hide original SELECT element:*/
    }
    .select-selected {
        background-color: darkgrey;
    }
    /*style the arrow inside the select element:*/
    .select-selected:after {
        position: absolute;
        content: "";
        top: 14px;
        right: 10px;
        width: 0;
        height: 0;
        border: 6px solid transparent;
        border-color: #fff transparent transparent transparent;
    }
    /*point the arrow upwards when the select box is open (active):*/
    .select-selected.select-arrow-active:after {
        border-color: transparent transparent #fff transparent;
        top: 7px;
    }
    /*style the items (options), including the selected item:*/
    .select-items div,.select-selected {
        color: #ffffff;
        padding: 8px 16px;
        border: 1px solid transparent;
        border-color: transparent transparent rgba(0, 0, 0, 0.1) transparent;
        cursor: pointer;
        user-select: none;
    }
    /*style items (options):*/
    .select-items {
        position: absolute;
        background-color: darkgray;
        top: 100%;
        left: 0;
        right: 0;
        z-index: 99;
    }
    /*hide the items when the select box is closed:*/
    .select-hide {
        display: none;
    }
    .select-items div:hover, .same-as-selected {
        background-color: rgba(0, 0, 0, 0.1);
    }
    * {
        outline: 0;
        font-family: sans-serif
    }
    body {
        background-color: #fafafa
    }
    span.msg,
    span.choose {
        color: #555;
        padding: 5px 0 10px;
        display: inherit
    }


</style>
<body>

<sf:form action="/reUpdate" method="post" modelAttribute="user">
    <sf:hidden path="userId"/>
    <sf:hidden path="login"/>
    <sf:hidden path="adminRole"/>
    <sf:hidden path="password"/>
    <sf:hidden path="key"/>
    <sf:hidden path="position"/>
    <sf:hidden path="company"/>
    <input type="hidden" name="pos_id" value="${user.position.idPosition}">
    <input type="hidden" name="company_id" value="${user.company.idCompany}">
    <div class="container">
        <h1>Завершение регистрации Администратора</h1>
        <p>Пожалуйста, заполните все поля для завершения регистрации Администратора в системе</p>
        <hr>

        <sf:errors id="err" path="position" cssStyle="color: red"/>
        <br/>
        <label><b>Фамилия, имя, отчество</b></label>
        <input type="text" placeholder="Фамилия Имя Отчество" name="name" required>
        <label><b>Email</b></label>
        <input type="email" placeholder="Email" name="email" required>


        <div class="clearfix" style="margin-top: 10px">
            <button type="button" class="cancelbtn" onclick="document.location='/logout'">Отмена</button>
            <button type="submit" class="signupbtn">Зарегистрироваться</button>
        </div>
    </div>
</sf:form>

</body>
</html>
