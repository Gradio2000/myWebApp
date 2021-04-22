<%@ page import="ru.laskin.myWebApp.model.Question" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 15.04.2021
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"       prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql"       prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml"       prefix="x"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"  %>

<jsp:useBean id="tests" class="ru.laskin.myWebApp.model.Test"/>
<jsp:useBean id="q" class="ru.laskin.myWebApp.model.Question"/>

<html>
<head>
    <title>Test</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="page_type" content="np-template-header-footer-from-plugin">
    <title>Все тесты</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/Site2/nicepage.css" media="screen">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/Site2/Все-тесты.css" media="screen">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/modalForm.css" media="screen">
    <script class="u-script" type="text/javascript" src="${pageContext.request.contextPath}/resources/Site2/jquery.js" defer=""></script>
    <script class="u-script" type="text/javascript" src="${pageContext.request.contextPath}/resources/Site2/nicepage.js" defer=""></script>
    <meta name="generator" content="Nicepage 3.12.0, nicepage.com">
    <link id="u-theme-google-font" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i|Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i">


    <script type="application/ld+json">{
        "@context": "http://schema.org",
        "@type": "Organization",
        "name": "",
        "url": "index.html",
        "logo": "${pageContext.request.contextPath}/resources/Site2/images/horizontal_on_white_by_logaster.png"
    }</script>

    <meta property="og:title" content="О нас">
    <meta property="og:type" content="website">
    <meta name="theme-color" content="#478ac9">
    <link rel="canonical" href="index.html">
    <meta property="og:url" content="index.html">

    <style>
        body {font-family: Arial, Helvetica, sans-serif;}

        /* The Modal (background) */
        .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 1; /* Sit on top */
            padding-top: 100px; /* Location of the box */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgb(0,0,0); /* Fallback color */
            background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
        }

        /* Modal Content */
        .modal-content {
            background-color: #fefefe;
            margin: auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
        }

        /* The Close Button */
        .close {
            color: #aaaaaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: #000;
            text-decoration: none;
            cursor: pointer;
        }

        .btn {
            background-color: #ddd;
            border: none;
            color: black;
            padding: 16px 32px;
            text-align: center;
            font-size: 16px;
            margin: 4px 2px;
            transition: 0.3s;
            width: 200px;

        }
        .btnInForm{
            background-color: #ddd;
            border: none;
            color: black;
            padding: 16px 32px;
            text-align: center;
            font-size: 16px;
            margin: 4px 2px;
            transition: 0.3s;
            width: 200px;
        }


        .btn:hover {
            background-color: #3e8e41;
            color: white;
        }

        .btnInForm:hover {
            background-color: #3e8e41;
            color: white;
        }

        .textArea {
            width: 100%;
            outline: none;
            resize: none;
        }

        .textAreaAnswer {
            width: 50%;
            outline: none;
            resize: none;
        }

    </style>

</head>
<body class="u-body">
<header class="u-clearfix u-grey-15 u-header u-sticky u-header" id="sec-1274"><div class="u-clearfix u-sheet u-sheet-1">
    <a href="https://nicepage.com" class="u-image u-logo u-image-1" data-image-width="330" data-image-height="150">
        <img src="${pageContext.request.contextPath}/resources/Site2/images/horizontal_on_white_by_logaster.png" class="u-logo-image u-logo-image-1" data-image-width="97">
    </a>
    <nav class="u-menu u-menu-dropdown u-offcanvas u-menu-1">
        <div class="menu-collapse" style="font-size: 1rem; letter-spacing: 0px;">
            <a class="u-button-style u-custom-left-right-menu-spacing u-custom-padding-bottom u-custom-top-bottom-menu-spacing u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="#">
                <svg><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#menu-hamburger"></use></svg>
                <svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"><defs><symbol id="menu-hamburger" viewBox="0 0 16 16" style="width: 16px; height: 16px;"><rect y="1" width="16" height="2"></rect><rect y="7" width="16" height="2"></rect><rect y="13" width="16" height="2"></rect>
                </symbol>
                </defs></svg>
            </a>
        </div>
        <div class="u-custom-menu u-nav-container">
            <ul class="u-nav u-unstyled u-nav-1"><li class="u-nav-item"><a class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="Главная.html" style="padding: 10px 20px;">Главная</a>
            </li><li class="u-nav-item"><a class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="О-нас.html" style="padding: 10px 20px;">alltests</a>
            </li><li class="u-nav-item"><a class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="Контакты.html" style="padding: 10px 20px;">Контакты</a>
            </li></ul>
        </div>
        <div class="u-custom-menu u-nav-container-collapse">
            <div class="u-black u-container-style u-inner-container-layout u-opacity u-opacity-95 u-sidenav">
                <div class="u-sidenav-overflow">
                    <div class="u-menu-close"></div>
                    <ul class="u-align-center u-nav u-popupmenu-items u-unstyled u-nav-2"><li class="u-nav-item"><a class="u-button-style u-nav-link" href="Главная.html" style="padding: 10px 20px;">Главная</a>
                    </li><li class="u-nav-item"><a class="u-button-style u-nav-link" href="/allTests" style="padding: 10px 20px;">Все тесты</a>
                    </li><li class="u-nav-item"><a class="u-button-style u-nav-link" href="/logout" style="padding: 10px 20px;">Выход</a>
                    </li></ul>
                </div>
            </div>
            <div class="u-black u-menu-overlay u-opacity u-opacity-70"></div>
        </div>
    </nav>
</div>
</header>



<sf:form name="form1" action="/updateTest" method="post" modelAttribute="test">
    <c:set var="IDTest" value="${test.testId}"/>
    <sf:hidden path="testId"/>
    <sf:hidden path="groupId"/>

    <label><br>Название теста<br></label>
    <sf:input path="testName" size="200"/>
        <c:forEach var="ques" items="${test.questions}">
            <label><br>Вопрос id = ${ques.questionId}<br></label>
            <input name="question" value="${ques.questionName}"><br>
            <input hidden name="questionId" value="${ques.questionId}">
            <c:forEach var="answer" items="${ques.answers}">
                <input name="answer" value="${answer.answerName}" ><br>
                <input hidden name="answerId" value="${answer.answerId}">
                <input hidden name="isRight" value="${answer.right}">
                <input hidden name="quesAnsId" value="${answer.question.questionId}">
            </c:forEach>
        </c:forEach>
    <button class="btn" type="submit">Готово</button>

    <!-- Trigger/Open The Modal -->
    <button class="btn" id="myBtn" type="button">Добавить вопрос</button>
</sf:form>

<!-- The Modal -->
<div id="myModal" class="modal">

    <!-- Modal content -->
    <div class="modal-content">
        <span class="close">X</span>
        <h3>Форма добавления вопроса</h3>
        <form name="formName" id="modalForm" action="/addQuestion" method="POST" class="u-clearfix u-form-spacing-10 u-form-vertical u-inner-form" style="padding: 10px" source="custom">
            <input hidden name="IDTest" value="${IDTest}">
            <div class="u-form-group u-form-message">
                <label for="message-9257" class="u-form-control-hidden u-label"></label>
                <textarea placeholder="Введите вопрос" rows="2" cols="50" id="message-9257" name="questionName" class="u-border-1 u-border-grey-75 u-border-no-left u-border-no-right u-border-no-top u-input u-input-rectangle u-white u-input-1" required="required"></textarea>
            </div>
            <div class="u-form-group u-form-group-2">
                <label for="textarea-6123" class="u-form-control-hidden u-label"></label>
                <textarea placeholder="Введите ответ" rows="2" cols="25" id="textarea-6123" name="answerName" class="u-border-1 u-border-grey-75 u-border-no-left u-border-no-right u-border-no-top u-input u-input-rectangle u-white u-input-2"></textarea>
            </div>
            <div class="u-align-right u-form-group u-form-submit">
<%--                <button class="u-btn u-btn-submit u-button-style" type="submit">Готово</button>--%>
                <button class="btn" type="submit" onclick="closeform()">Готово</button>
            </div>
        </form>
    </div>

</div>

<script>
    // Get the modal
    var modal = document.getElementById('myModal');

    // Get the button that opens the modal
    var btn = document.getElementById("myBtn");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

    // When the user clicks the button, open the modal
    btn.onclick = function() {
        modal.style.display = "block";
    }

    // When the user clicks on <span> (x), close the modal
    span.onclick = function() {
        modal.style.display = "none";
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    }

    function closeform(){
        modal.style.display = "none"
        document.getElementById("modalForm").submit();
    }
</script>

<footer class="u-align-center u-clearfix u-footer u-grey-80 u-footer" id="sec-3569">
        <div class="u-clearfix u-sheet u-sheet-1">
            <p class="u-small-text u-text u-text-variant u-text-1">Пример текста. Кликните, чтобы выбрать текстовый блок. Кликните еще раз или сделайте двойной клик, чтобы начать редактирование текста.</p>
        </div>
    </footer>

</body>
</html>
