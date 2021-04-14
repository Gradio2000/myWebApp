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
<jsp:useBean id="groupTest" scope="request" class="ru.laskin.myWebApp.model.GroupTest"/>


<!DOCTYPE html>
<html style="font-size: 16px;">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <meta name="keywords" content="Все тесты">
    <meta name="description" content="">
    <meta name="page_type" content="np-template-header-footer-from-plugin">
    <title>Главная</title>
    <link rel="stylesheet" href="resources/1/nicepage.css" media="screen">
    <link rel="stylesheet" href="resources/1/Главная.css" media="screen">
    <script class="u-script" type="text/javascript" src="resources/1/jquery.js" defer=""></script>
    <script class="u-script" type="text/javascript" src="resources/1/nicepage.js" defer=""></script>
    <meta name="generator" content="Nicepage 3.12.0, nicepage.com">
    <link id="u-theme-google-font" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i|Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i">



    <script type="application/ld+json">{
        "@context": "http://schema.org",
        "@type": "Organization",
        "name": "",
        "url": "index.html",
        "logo": "images/default-logo.png"
    }</script>
    <meta property="og:title" content="Главная">
    <meta property="og:type" content="website">
    <meta name="theme-color" content="#478ac9">
    <link rel="canonical" href="index.html">
    <meta property="og:url" content="index.html">
</head>
<body data-home-page="Главная.html" data-home-page-title="Главная" class="u-body"><header class="u-clearfix u-header u-sticky u-header" id="sec-1032"><div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
    <a href="https://nicepage.com" class="u-image u-logo u-image-1">
        <img src="images/default-logo.png" class="u-logo-image u-logo-image-1">
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
            </li><li class="u-nav-item"><a class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="Личный-кабинет.html" style="padding: 10px 20px;">Личный кабинет</a>
            </li><li class="u-nav-item"><a class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="/logout" style="padding: 10px 20px;">Выход</a>
            </li></ul>
        </div>
        <div class="u-custom-menu u-nav-container-collapse">
            <div class="u-black u-container-style u-inner-container-layout u-opacity u-opacity-95 u-sidenav">
                <div class="u-sidenav-overflow">
                    <div class="u-menu-close"></div>
                    <ul class="u-align-center u-nav u-popupmenu-items u-unstyled u-nav-2"><li class="u-nav-item"><a class="u-button-style u-nav-link" href="Главная.html" style="padding: 10px 20px;">Главная</a>
                    </li><li class="u-nav-item"><a class="u-button-style u-nav-link" href="Личный-кабинет.html" style="padding: 10px 20px;">Личный кабинет</a>
                    </li><li class="u-nav-item"><a class="u-button-style u-nav-link" href="/logout" style="padding: 10px 20px;">Выход</a>
                    </li></ul>
                </div>
            </div>
            <div class="u-black u-menu-overlay u-opacity u-opacity-70"></div>
        </div>
    </nav>
</div></header>
<section class="u-clearfix u-section-1" id="sec-6192">
    <div class="u-clearfix u-sheet u-sheet-1">
        <div class="u-align-center u-container-style u-group u-group-1">
            <div class="u-container-layout u-valign-middle u-container-layout-1">
                <h2 class="u-text u-text-1">Все тесты</h2>
                <p class="u-text u-text-2">На этой странице Вы можете выбрать тест</p>
            </div>
        </div>
        <form name="test_form" action="/startTest" method="get" modelAttribute="test" onsubmit="return validate_form()">
            <c:forEach var="test" items="${allTest}">

                <div class="u-clearfix u-expanded-width u-gutter-54 u-layout-wrap u-layout-wrap-1">
                    <div class="u-gutter-0 u-layout">
                        <div class="u-layout-row">
                            <div class="u-size-60">
                                <div class="u-layout-col">
                                    <div class="u-align-left u-container-style u-layout-cell u-left-cell u-size-60 u-layout-cell-1">
                                        <div class="u-container-layout u-container-layout-2">
                                            <p class="u-text u-text-3">${test.testName}</p>
<%--                                            <a href="https://nicepage.com/ru/shablony-veb-saytov" class="u-btn u-button-style u-btn-1">Начать тест</a>--%>
                                            <p><button name="testId" value="${test.testId}" type="submit" class="u-btn u-button-style u-btn-1">Начать тест</button></p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </form>
    </div>
</section>
<style class="u-overlap-style">.u-overlap:not(.u-sticky-scroll) .u-header {
}</style>



<footer class="u-align-center u-clearfix u-footer u-grey-80 u-footer" id="sec-2ad8"><div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
    <p class="u-small-text u-text u-text-variant u-text-1">Система автоматизированного тестирования сотрудников</p>
</div></footer>
<section class="u-backlink u-clearfix u-grey-80">
    <a class="u-link" href="https://nicepage.com/website-templates" target="_blank">
        <span>Website Templates</span>
    </a>
    <p class="u-text">
        <span>created with</span>
    </p>
    <a class="u-link" href="https://nicepage.com/" target="_blank">
        <span>Website Builder Software</span>
    </a>.
</section>
</body>
</html>