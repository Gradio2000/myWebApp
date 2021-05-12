
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"       prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql"       prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml"       prefix="x"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"  %>
<%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 05.05.2021
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <script class="u-script" type="text/javascript" src="${pageContext.request.contextPath}/resources/Site2/jquery.js" defer=""></script>
    <script class="u-script" type="text/javascript" src="${pageContext.request.contextPath}/resources/Site2/nicepage.js" defer=""></script>
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

    <jsp:useBean id="group" class="ru.laskin.myWebApp.model.GroupTest"/>

    <title>Группы тестов</title>
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
                    </li><li class="u-nav-item"><a class="u-button-style u-nav-link" href="${pageContext.request.contextPath}/allTests" style="padding: 10px 20px;">Все тесты</a>
                    </li><li class="u-nav-item"><a class="u-button-style u-nav-link" href="${pageContext.request.contextPath}/logout" style="padding: 10px 20px;">Выход</a>
                    </li></ul>
                </div>
            </div>
            <div class="u-black u-menu-overlay u-opacity u-opacity-70"></div>
        </div>
    </nav>
</div>
</header>


<div class="content">
    <form method="post" action="/updateGroup">
        <div class="content-inside">
            <table class="my-table">
                <tr>
                    <th>id группы</th>
                    <th>Группа тестов</th>
                    <th></th>
                </tr>
                <c:forEach var='group' items='${groupTests}'>
                    <input hidden name="grouptestId" value="${group.groupTestId}"/>
                    <tr>
                        <td><c:out value="${group.groupTestId}"/></td>
                        <td width=100><textarea name="name">${group.name}</textarea></td>
<%--                        <td><button class="btn danger" onclick="document.location = 'group/delete?id=${group.groupTestId}'">Удалить</button></td>--%>
                        <td><a href="/group/delete?id=${group.groupTestId}" class="btn danger">Удалить</a> </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

            <!-- Button trigger modal -->
            <button type="button" class="btn success" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                Добавить новую группу
            </button>
        <button class="btn info" type="submit">Сохранить и закрыть</button>
    </form>
</div>

<%--модальная форма--%>
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel">Добавление группы</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <sf:form class="validForm" id="myForm" name="formName" action="/addGroup" method="POST" modelAttribute="groupTests">
                    <div class="form-floating">
                        <textarea name="name" class="form-control" placeholder="Введите группу" id="floatingTextarea" style="height: 100px" required></textarea>
                        <label for="floatingTextarea">Наименование группы</label>
                    </div>

                    <br/>

                    <div class="modal-footer">
                        <button type="button" class="btn close" data-bs-dismiss="modal">Закрыть</button>
                        <button type="submit" class="btn info">Добавить</button>
                    </div>
                </sf:form>
            </div>
        </div>
    </div>
</div>



<%--<div class="modal fade" id="staticBackdrop1" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">--%>
<%--    <div class="modal-dialog">--%>
<%--        <div class="modal-content">--%>
<%--            <div class="modal-header">--%>
<%--                <h5 class="modal-title" id="staticBackdropLabel1">Редактирование группы</h5>--%>
<%--                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>--%>
<%--            </div>--%>
<%--            <div class="modal-body">--%>
<%--                <sf:form class="validForm" id="myForm" name="formName" action="/addGroup" method="POST" modelAttribute="groupTests">--%>
<%--                    <div class="form-floating">--%>
<%--                        <textarea name="name" class="form-control" placeholder="Введите группу" id="floatingTextarea1" style="height: 100px" required></textarea>--%>
<%--                        <label for="floatingTextarea">Наименование группы</label>--%>
<%--                    </div>--%>

<%--                    <br/>--%>

<%--                    <div class="modal-footer">--%>
<%--                        <button type="button" class="btn close" data-bs-dismiss="modal">Закрыть</button>--%>
<%--                        <button type="submit" class="btn info">Добавить</button>--%>
<%--                    </div>--%>
<%--                </sf:form>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>


<footer class="footer u-align-center u-clearfix u-grey-80 " id="sec-3569">
    <div class="u-clearfix u-sheet u-sheet-1">
        <p class="u-small-text u-text u-text-variant u-text-1">Пример текста. Кликните, чтобы выбрать текстовый блок. Кликните еще раз или сделайте двойной клик, чтобы начать редактирование текста.</p>
    </div>
</footer>
</body>
<style>

    th, td {
        width: 10px;
    }

    textarea {
        width: -webkit-fill-available;
    }


    html, body {
        height: 100%;
        margin: 0;
    }
    .content {
        min-height: 100%;
    }
    .content-inside {
        padding: 20px;
        padding-bottom: 50px;
    }
    .footer {
        height: 50px;
        margin-top: -50px;
    }

    table {
        border-collapse: collapse;
        border-spacing: 0;
        width: 100%;
        border: 1px solid #ddd;
    }

    th, td {
        text-align: left;
        padding: 16px;
    }

    tr:nth-child(even) {
        background-color: #f2f2f2
    }

    .btn {
        border: none;
        background-color: inherit;
        padding: 14px 28px;
        font-size: 16px;
        cursor: pointer;
        display: inline-block;
    }

    .mybtn {
        border: none;
        background-color: inherit;
        padding: 14px 28px;
        font-size: 16px;
        cursor: pointer;
        display: inline-block;
    }

    /* Green */
    .success {
        color: green;
    }

    .success:hover {
        background-color: #4CAF50;
        color: white;
    }

    /* Blue */
    .info {
        color: dodgerblue;
    }

    .info:hover {
        background: #2196F3;
        color: white;
    }

    .close{
        color: #81888b;
    }

    .close:hover {
        background: #99a1a5;
        color: white;
    }

    /* Orange */
    .warning {
        color: orange;
    }

    .warning:hover {
        background: #ff9800;
        color: white;
    }

    /* Red */
    .danger {
        color: red;
    }

    .danger:hover {
        background: #f44336;
        color: white;
    }

    /* Gray */
    .default {
        color: black;
    }

    .default:hover {
        background: #e7e7e7;
    }

    .cancelbtn {
        border: none;
        color: white;
        padding: 14px 28px;
        font-size: 16px;
        cursor: pointer;
        background-color: #f44336;
    }
    .cancelbtn:hover {background: #da190b;}

    .footer{
        position: relative;
        left:0px;
        bottom:0px;
        height:100px;
        width:100%;
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
</html>
