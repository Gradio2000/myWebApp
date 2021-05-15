
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
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>


<jsp:useBean id="question" class="ru.laskin.myWebApp.model.Question"/>
<jsp:useBean id="answer" class="ru.laskin.myWebApp.model.Answer"/>
<jsp:useBean id="test" class="ru.laskin.myWebApp.model.Test"/>
<jsp:useBean id="user" class="ru.laskin.myWebApp.model.User"/>

<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        * {box-sizing: border-box}
        body {font-family: "Lato", sans-serif;}

        /* Style the tab */
        .tab {
            float: left;
            border: 1px solid #ccc;
            background-color: #f1f1f1;
            width: 30%;
            height: 300px;
        }

        /* Style the buttons inside the tab */
        .tab button {
            display: block;
            background-color: inherit;
            color: black;
            padding: 22px 16px;
            width: 100%;
            border: none;
            outline: none;
            text-align: left;
            cursor: pointer;
            transition: 0.3s;
            font-size: 17px;
        }

        /* Change background color of buttons on hover */
        .tab button:hover {
            background-color: #ddd;
        }

        /* Create an active/current "tab button" class */
        .tab button.active {
            background-color: #ccc;
        }

        /* Style the tab content */
        .tabcontent {
            float: left;
            padding: 0 12px;
            border: 1px solid #ccc;
            width: 70%;
            border-left: none;
            height: 300px;
        }
    </style>

    <title>Прохождение теста</title>
</head>
<body>

<div class="tab">
    <c:forEach var="quest" items="${tests.questions}" varStatus="count">
        <button id="${count.count}" class="tablinks" onclick="openCity(event, ${count.count})">Вопрос ${count.count}</button>
    </c:forEach>
</div>

    <sf:form method="post" action="saveUserAnswer">
        <c:forEach var="quest" items="${tests.questions}" varStatus="count">
                <input hidden name="questionId" value="${quest.questionId}">
                <input hidden name="attemptId" value="${attemptId}">
                <input hidden name="testId" value="${tests.testId}">
                <div id="content${count.count}" class="tabcontent">
                    <h3>${quest.questionName}</h3>
                    <c:forEach var="answ" items="${quest.answers}">
                        <label><input type="checkbox" name="check" value="${answ.answerId}"> ${answ.answerName}</label>
                        <br/>
                    </c:forEach>
                    <input name="next" onclick=iNext() type="button" value="Далее"/>
                </div>
        </c:forEach>
        <input type="submit" value="Ответить">
    </sf:form>


<script>

    function openCity(evt, count) {
        var i, tabcontent, tablinks;
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

    function iNext(){
        const elem = document.getElementsByClassName("active");
        let id = elem[0].id;
        id++;
        const el = document.getElementById(id);
        el.click();
    }

    // Get the element with id="defaultOpen" and click on it
    document.getElementById("1").click();



</script>

</body>
</html>
