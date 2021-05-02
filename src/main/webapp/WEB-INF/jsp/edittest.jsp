<%--
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
<%@ taglib prefix="cf" uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:useBean id="tests" class="ru.laskin.myWebApp.model.Test"/>
<jsp:useBean id="q" class="ru.laskin.myWebApp.model.Question"/>

<!doctype html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>


    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="page_type" content="np-template-header-footer-from-plugin">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/Site2/nicepage.css" media="screen">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/Site2/Все-тесты.css" media="screen">
    <script class="u-script" type="text/javascript" src="/resources/Site2/jquery.js" defer=""></script>
    <script class="u-script" type="text/javascript" src="/resources/Site2/nicepage.js" defer=""></script>
    <meta name="generator" content="Nicepage 3.12.0, nicepage.com">
    <link id="u-theme-google-font" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i|Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i">


    <script type="application/ld+json">{
        "@context": "http://schema.org",
        "@type": "Organization",
        "name": "",
        "url": "index.html",
        "logo": "/resources/Site2/images/horizontal_on_white_by_logaster.png"
    }</script>

    <meta property="og:title" content="О нас">
    <meta property="og:type" content="website">
    <meta name="theme-color" content="#478ac9">
    <link rel="canonical" href="index.html">
    <meta property="og:url" content="index.html">

    <title>Редактирование теста</title>
</head>

<body>

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
            <ul class="u-nav u-unstyled u-nav-1"><li class="u-nav-item"><a class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="/login" style="padding: 10px 20px;">Главная</a>
            </li><li class="u-nav-item"><a class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="/allTests" style="padding: 10px 20px;">Все тесты</a>
            </li><li class="u-nav-item"><a class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="/logout" style="padding: 10px 20px;">Выход</a>
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

<section class="main-content">
    <main class="u-body">
        <sf:form name="form1" action="/updateTest" method="post" modelAttribute="test" onsubmit="return getCount()">
            <c:set var="IDTest" value="${test.testId}"/>
            <sf:hidden path="testId"/>
            <sf:hidden path="groupId"/>

            <div class="container-my-big">
                <label for="textTestName"><h2>Название теста</h2></label>
                <textarea class="form-control" name="testName" id="textTestName">${test.testName}</textarea>
                <div class="container-my-md">
                    <c:forEach var="ques" items="${test.questions}">
                        <input hidden name="questionId" value="${ques.questionId}">
                        <label for="textQuestionName"><h3>Вопрос № ${ques.questionId}</h3></label>
                        <textarea class="form-control" name="question" id="textQuestionName" placeholder="Введите вопрос" required>${ques.questionName}</textarea>
                        <div class="container-my-sm">
                            <label for="textAnswerName"><h4>Ответы на вопрос № ${ques.questionId}</h4></label>
                            <c:forEach var="answer" items="${ques.answers}">
                                <input hidden name="answerId" value="${answer.answerId}">
                                <input hidden name="quesAnsId" value="${answer.question.questionId}">
                                <textarea class="form-control" name="answer" id="textAnswerName" placeholder="Введите ответ" required>${answer.answerName}</textarea>
                                <cf:if test="${answer.right==true}">
                                    <label><input type="checkbox" name="isRight" value="${a}" checked> Правильный ответ</label>
                                </cf:if>
                                <cf:if test="${answer.right == false}">
                                    <label><input type="checkbox" name="isRight" value=${a}> Правильный ответ</label>
                                </cf:if>
                            </c:forEach>

                            <div id="forAddAnswer"></div>

                            <button type="button" class="btn-danger">-</button>
                            <button type="button" class="btn-success" id="addAnswerMain">+</button>
                        </div>
                    </c:forEach>
                </div>
                <button class="btn btn-success" type="submit">Готово</button>
                <!-- Button trigger modal -->
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                    Добавить вопрос
                </button>
            </div>
        </sf:form>

    </main>

    <!-- Modal -->
    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="staticBackdropLabel">Добавление вопроса</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form class="validForm" id="myForm" name="formName" action="/addQuestion" method="POST" onsubmit="return validateForm()">
                        <input hidden name="IDTest" value="${IDTest}">

                        <div class="form-floating">
                            <textarea name="questionName" class="form-control" placeholder="Leave a comment here" id="floatingTextarea1" style="height: 100px" required></textarea>
                            <label for="floatingTextarea1">Введите вопрос</label>
                        </div>

                        <br/>

                        <div class="answerContainer" id="container">
                        </div>


                        <div class="modal-footer">
                            <button type="button" class="btn btn-warning" id="addAnswer">Добавить ответ</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                            <button type="submit" class="btn btn-primary">Добавить</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="rasp"></div>
    </div>
</section>

<footer class="footer u-align-center u-clearfix u-grey-80 " id="sec-3569">
    <div class="u-clearfix u-sheet u-sheet-1">
        <p class="u-small-text u-text u-text-variant u-text-1">Пример текста. Кликните, чтобы выбрать текстовый блок. Кликните еще раз или сделайте двойной клик, чтобы начать редактирование текста.</p>
    </div>
</footer>

</body>
<script>

    // функции для проверки checkbox
    function validate(){
        if(!validateForm()){
            alert("You must check atleast one of the checkboxes");
            return false;
        }
        return true;
    }

    function validateForm() {
        var c = document.getElementsByTagName('input');
        for (var i = 0; i<c.length; i++){
            if (c[i].type ==='checkbox') {
                if (c[i].checked) {
                    return true;
                }
            }
        }
        return false;
    }

    //функция динамического добавления полей главной страницы
    $('#addAnswerMain').click(function(e) {
        //создаем поле
        $('<textarea/>', {
            name: 'answer',
            class: 'form-control',
            style: 'height: 100px',
            id: 'textAnswerName',
            placeholder: 'Введите ответ',
            required: ''
        }).appendTo($('#forAddAnswer'));

        e.preventDefault();
        console.log("ops");
        return false;
    });


    //переменная для использования при создании checkbox
    var count = 0;

    //функция динамического добавления полей модальной формы
    $('#addAnswer').click(function(e) {

        //создаем div
        var divAnswer = $('<div/>', {
            'class': 'answerBlock',
            'id': 'miniContainer'
        });

        //создаем div
        var divForm = $('<div/>', {
           'class': 'form-floating'
        });

        //создаем поле и добавляем его в div
        $('<textarea/>', {
            name: 'answerName',
            class: 'form-control',
            style: 'height: 100px',
            cols: '50',
            rows: '3',
            required: ''
        }).appendTo(divForm);

        //добавляем div в div
        divForm.appendTo(divAnswer);

        //создаем label и добавляем его в div
        var check = $('<label/>').html("Правильный ответ ").appendTo(divAnswer);

        //создаем checkbox и добавляем его в label
        $('<input/>', {
            id: 'mycheck',
            class: 'validCheck',
            type: 'checkbox',
            name: 'isRight',
            value: count
        }).appendTo(check);

        //перенос строки
        $('<br/>').appendTo(divAnswer);

        //создаем кнопку отмены и добавляем ее в div и назначаем событие на клик
        $('<input/>', {
            value: 'Удалить ответ',
            type: 'button',
            class: 'deleteAnswer btn-danger'
        }).appendTo(divAnswer).click(function(e) {
            $(this).parent().remove();
            e.preventDefault();
            return false;
        });

        //перенос строки
        $('<br/>').appendTo(divAnswer);

        //добавляе div в div
        divAnswer.appendTo($('#container'));

        //счетчик для right
        count = count + 1;

        e.preventDefault();
        return false;
    });

    //Функция удаления  поля
    $('.deleteAnswer').click(function(e) {
        $(this).parent().remove();

        e.preventDefault();
        return false;
    });

    function isChecked(){

    }

    function getCount(){
        if(document.getElementsByName("answer").length == 0){
            alert("Добавьте ответ");
            return false;
        }

    var el = document.getElementsByName("isRight");
        for (let i = 0; i < el.length ; i++) {
            el[i].setAttribute("value", i)
        }
    }
</script>

<style>
    .container-my-big {
        width: 100%;
        padding-right: var(--bs-gutter-x, 0.75rem);
        padding-left: var(--bs-gutter-x, 0.75rem);
        margin-right: auto;
        margin-left: auto;
        max-width: 1080px;
    }

    .container-my-md {
        width: 100%;
        padding-right: var(--bs-gutter-x, 0.75rem);
        padding-left: var(--bs-gutter-x, 0.75rem);
        /*margin-right: 0;*/
        margin-left: auto;
        max-width: 960px;
    }

    .container-my-sm {
        width: 100%;
        padding-right: var(--bs-gutter-x, 0.75rem);
        padding-left: var(--bs-gutter-x, 0.75rem);
        /*margin-right: 0;*/
        margin-left: auto;
        max-width: 720px;
    }

    .footer{
        position: relative;
        left:0px;
        bottom:0px;
        height:100px;
        width:100%;
    }

    .rasp{
        height: 100px;
    }

    html{
        height: 100%;
    }
    body{
        display: flex;
        flex-direction: column;
        height: 100%;
    }

    header{
        /* 0 flex-grow, 0 flex-shrink, auto flex-basis */
        flex: 0 0 auto;
    }
    .main-content{
        /* 1 flex-grow, 0 flex-shrink, auto flex-basis */
        flex: 1 0 auto;
    }
    footer{
        /* 0 flex-grow, 0 flex-shrink, auto flex-basis */
        flex: 0 0 auto;
    }

</style>

</body>
</html>
