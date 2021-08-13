
<%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 30.03.2021
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"       prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql"       prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml"       prefix="x"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"  %>
<%@ taglib prefix="z" uri="http://www.springframework.org/tags/form" %>


<jsp:useBean id="question" class="ru.laskin.myWebApp.model.Question"/>
<jsp:useBean id="answer" class="ru.laskin.myWebApp.model.Answer"/>
<jsp:useBean id="test" class="ru.laskin.myWebApp.model.Test"/>
<jsp:useBean id="user" class="ru.laskin.myWebApp.model.User"/>

<html>
<jsp:include page="../includes/settingsHeader.jsp"/>

<head>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Прохождение теста</title>


</head>
<body>
<jsp:include page="../includes/header.jsp"/>

<div class="wrapper">
    <div class="content">

            <div class="tab">
                <c:forEach var="quest" items="${tests.questions}" varStatus="count">
<%--                    <button id="${count.count}" class="quesLinkBtn" onclick="openCity(event, ${count.count})" >${count.count}</button>--%>
                    <button id="${count.count}" class="tablinks quesLinkBtn" onclick="openCity(event, ${count.count})">${count.count}</button>
                </c:forEach>
            </div>

            <c:forEach var="quest" items="${tests.questions}" varStatus="count">
                <sf:form name="answForm" method="post" action="oper">
                    <input hidden name="attemptId" value="${attemptId}">
                    <input hidden name="testId" value="${tests.testId}">
                    <div align="left" id="content${count.count}" class="tabcontent my-box">
                        <h4>Вопрос № ${count.count}</h4>
                        <h4>${quest.questionName}</h4>
                        <input hidden name="questionId" value="${quest.questionId}">
                        <c:forEach var="answ" items="${quest.answers}">
                            <table>
                                <tr>
                                    <label class="checkes"><input class="checkes" type="checkbox" name="check" value="${answ.answerId}"> ${answ.answerName}</label>
                                </tr>
                            </table>
                            <br/>
                        </c:forEach>
                        <input id="answBtn${count.count}" type="submit" class="butt btn success" value="Ответить"/>
                        <input id="skipBtn${count.count}" class="butt btn warning" name="skip" onclick=iSkip(${count.count}) type="button" value="Пропустить"/>
                    </div>
                </sf:form>
            </c:forEach>

            <c:if test="${tests.time != 0}">
                <div id="countdown" class="countdown" style="margin-top: 20px">
                    <span class="hours countdown-time"></span>
                    <span> : </span>
                    <span class="minutes countdown-time"></span>
                    <span> : </span>
                    <span class="seconds countdown-time"></span>
                </div>
            </c:if>


        <form id="testForm" name="testForm" method="post" action="/finish">
            <div style="margin-top: 20px">
                <input id="timeOfAttempt" hidden name="timeOfAttempt"/>
                <input hidden name="attemptId" value="${attemptId}"/>
                <button type="submit" id="btnfinish" class="mybtn my-btn success" style="color: whitesmoke">Завершить тест</button>
            </div>
        </form>
    </div>
</div>

<jsp:include page="../includes/footer.jsp"/>
</body>
<jsp:include page="../includes/styles.jsp"/>

<script>

    $('[name="testForm"]').on('submit', function(e) {

        var clock = document.getElementById("countdown");
        var time = ${tests.time} * 60 - (Number (clock.querySelector('.seconds').innerHTML) +
                Number (clock.querySelector('.minutes').innerHTML) * 60 +
                Number (clock.querySelector('.hours').innerHTML) * 3600);

        document.getElementById("timeOfAttempt").setAttribute("value", time);

    });


    function openCity(evt, count) {
        let i, tabcontent, tablinks;
        tabcontent = document.getElementsByClassName("tabcontent");
        for (i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablinks");
        for (i = 0; i < tablinks.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" active", "");
        }
        document.getElementById("content" + count).style.display = "block";
        evt.currentTarget.className += " active";
    }

    $('[name="answForm"]').on('submit', function(e) {
        e.preventDefault();

        const elem = document.getElementsByClassName("active");
        let id = elem[0].id;
        if (!valid(id)){
            alert('Необходимо выбрать хоть один ответ!');
            return false;
        }

        $.ajax({
            type: $(this).attr('method'),
            url : $(this).attr('action'),
            data: $(this).serialize()
        }).done(function() {
            const elem = document.getElementsByClassName("active");
            let id = elem[0].id;

            //блокируем кнопки
            const divElem = document.getElementById("content" + id);
            const btnElem = divElem.getElementsByClassName("butt");
            for (let i = 0; i < btnElem.length; i++) {
                btnElem[i].setAttribute("disabled", "disabled");
            }
            //Блокируем checks
            const checkElem = divElem.getElementsByClassName("checkes");
            for (let i = 0; i < checkElem.length ; i++) {
                checkElem[i].setAttribute("disabled", "disabled");
            }

            //пометить кнопку с вопросом серым
            const el = document.getElementById(id);
            el.className = el.className.replace(" warning borderWarning", "");
            el.className += " success borderSuccses";

            //перейти к следующему вопросу
            id++;
            document.getElementById(id).click();
        }).fail(function() {
            alert("Ответы не приняты. Обратитесь к администратору!");
        });
    });

    function iSkip(divId) {

        //пометить div как skipped
        document.getElementById("content" + divId).className += " skipped";

        //перейти к следующему вопросу
        const nextId = divId + 1;

        //пометить кнопку желтым
        const el = document.getElementById(divId);
        el.className += " warning borderWarning";

        //перейти к следующему элементу, если он не последний
        if (divId < document.getElementsByClassName("tablinks").length) {
            document.getElementById(nextId).click();
        }

    }

    function valid(divId){
        // проверить отмечен ли checkbox?
        const division = document.getElementById("content" + divId);
        const checks = division.querySelectorAll('input[type=checkbox]');
        let count = 0;
        for (const check of checks ) {
            if (check.checked){
                count++;
                break;
            }
        }
        return count !== 0;
    }

    function getTimeRemaining(endtime) {
        const t = Date.parse(endtime) - Date.parse(new Date());
        var seconds = Math.floor((t / 1000) % 60);
        var minutes = Math.floor((t / 1000 / 60) % 60);
        var hours = Math.floor((t / (1000 * 60 * 60)) % 24);
        var days = Math.floor(t / (1000 * 60 * 60 * 24));
        return {
            'total': t,
            'days': days,
            'hours': hours,
            'minutes': minutes,
            'seconds': seconds
        };
    }

    function initializeClock(id, endtime) {
        var clock = document.getElementById(id);
        var daysSpan = clock.querySelector('.days');
        var hoursSpan = clock.querySelector('.hours');
        var minutesSpan = clock.querySelector('.minutes');
        var secondsSpan = clock.querySelector('.seconds');

        function updateClock() {
            var t = getTimeRemaining(endtime);
            hoursSpan.innerHTML = ('0' + t.hours).slice(-2);
            minutesSpan.innerHTML = ('0' + t.minutes).slice(-2);
            secondsSpan.innerHTML = ('0' + t.seconds).slice(-2);

            if (t.total <= 0) {
                document.getElementById("btnfinish").click();
            }
        }

        updateClock();
        var timeinterval = setInterval(updateClock, 1000);
    }

    const deadline = new Date(Date.parse(new Date()) + ${tests.time} * 60 * 1000); // for endless timer

    if(${tests.time != 0}){
        initializeClock('countdown', deadline);
    }

    // Get the element with id="1" and click on it
    document.getElementById("1").click();

</script>
</html>
