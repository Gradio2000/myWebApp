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
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>


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
    .but {
        background-color: #4CAF50;
        color: white;
        padding: 14px 20px;
        margin: 8px 0;
        border: none;
        cursor: pointer;
        width: 100%;
        opacity: 0.9;
    }

    .but:hover {
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
        margin-right: auto;
        margin-left: auto;
        padding: 16px;
        position: relative;

        /*display: block;*/

        /*padding-left: 35px;*/
        /*margin-bottom: 12px;*/
        /*cursor: pointer;*/
        /*font-size: 22px;*/
        /*-webkit-user-select: none;*/
        /*-moz-user-select: none;*/
        /*-ms-user-select: none;*/
        /*user-select: none;*/
    }

    /* Clear floats */
    .clearfix::after {
        content: "";
        clear: both;
        display: table;
    }
    .checkmark {
        position: absolute;
        top: 0;
        left: 0;
        height: 25px;
        width: 25px;
        background-color: #eee;
    }

    .container:hover input ~ .checkmark {
        background-color: #ccc;
    }

    .container input:checked ~ .checkmark {
        background-color: #2196F3;
    }

    .checkmark:after {
        content: "";
        position: absolute;
        display: none;
    }

    .container input:checked ~ .checkmark:after {
        display: block;
    }

    .container .checkmark:after {
        left: 9px;
        top: 5px;
        width: 5px;
        height: 10px;
        border: solid white;
        border-width: 0 3px 3px 0;
        -webkit-transform: rotate(45deg);
        -ms-transform: rotate(45deg);
        transform: rotate(45deg);
    }

    .container .check {
        position: absolute;
        opacity: 0;
        cursor: pointer;
    }

</style>
<body>

    <sf:form action="/new_user" method="post">

        <div class="container">
            <h1>Регистрация</h1>
            <p>Пожалуйста, заполните все поля для регистрации в системе</p>
            <hr>

            <label><b>Логин</b></label>
            <input type="text" placeholder="Придумайте логин" name="login" required>
<%--            <sf:errors path="login" cssStyle="color: #CE272D"/><br/>--%>
            <label style="color: #da190b">${loginError}</label><br/>

            <label><b>Пароль</b></label>
            <input type="password" placeholder="Придумайте пароль" name="password" required>

            <label><b>Повторите пароль</b></label>
            <input type="password" placeholder="Введите пароль ещё раз" name="confirmPassword" required>
<%--            <sf:errors path="confirmPassword" cssStyle="color: #CE272D"/><br/>--%>
            <label style="color: #da190b">${confirmPassword}</label><br/>

            <input id="checkbox" type="checkbox" name="admin">
            <label for="checkbox"><b> Администратор</b> (новая компания)</label><br/>

            <label hidden><b>Название организации</b></label>
            <input id="text" type="text" placeholder="Введите на звание вашей организации " name="companyName" style="display: none">

            <div class="clearfix" style="margin-top: 10px">
                <button type="button" class="cancelbtn but" onclick="document.location='/login'">Отмена</button>
                <button type="submit" class="signupbtn but">Зарегистрироваться</button>
            </div>
        </div>
    </sf:form>

</body>
<script>
    $('#checkbox').click(function(){
        if ($(this).is(':checked')){
            $('#text').show();
        } else {
            $('#text').hide().val();
        }
    });
</script>
</html>