<%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 23.05.2021
  Time: 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"          prefix="c"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"           prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql"           prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml"           prefix="x"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"     prefix="fn"  %>
<%@ taglib uri="http://www.springframework.org/tags/form"   prefix="sf"  %>
<jsp:useBean id="user" scope="request" type="ru.laskin.myWebApp.model.User"/>
<jsp:useBean id="statisticList" scope="request" type="java.util.List"/>

<html>
<head>
    <jsp:include page="../includes/settingsHeader.jsp"/>
    <title>Статистика</title>
</head>
<body>
<jsp:include page="../includes/header.jsp"/>
<div class="wrapper">
    <div class="content">
        <div>
            <p>Имя пользователя: ${user.name}</p>
            <p>Должность: ${user.position}</p>
        </div>

        <div class="pagination">
                <a href="#" onclick="stepLeft()">&laquo;</a>
                <c:forEach var="f" items="${statisticList}" step="5" varStatus="count">
                    <a class="pag" id="${count.count}" href="#" onclick="getActive(this.id)">${count.count}</a>
                </c:forEach>
                <a href="#" onclick="stepRight()">&raquo;</a>
            </div>

        <table id="table0">
            <tr>
                <th>Дата и время</th>
                <th>Название теста</th>
                <th>Количество вопросов</th>
                <th>Количество правильных ответов</th>
                <th>Количество неправильных ответов</th>
                <th>Результат</th>
            </tr>

            <c:forEach var="statistic" items="${statisticList}" varStatus="count">

                <tr>
                    <td>${statistic.date}</td>
                    <td>${statistic.test.testName}</td>
                    <td class="mytd">${statistic.test.questions.size()}</td>
                    <td class="mytd">${statistic.trueAnswer}</td>
                    <td class="mytd">${statistic.falseAnswerSet.size()}</td>
                    <td class="mytd">${statistic.testResult}</td>
                    <td><button id="btn${count.count}" class="btn info" onclick="openDetail(${count.count})">Подробнее</button> </td>
                </tr>

                <tr id="hidden${count.count}" hidden>
                    <th>Варианты ответов</th>
                    <th>Ответы пользователя</th>
                    <th>Правильные ответы</th>
                </tr>

                <tr class="table${count.count}" hidden>
                    <td></td>
                    <td colspan="4">
                        <table>
                            <c:forEach var="ques" items="${statistic.test.questions}" varStatus="counter">
                                <tr>
                                    <td bgcolor="#a9a9a9">Вопрос № ${counter.count} ${ques.questionName}</td
                                </tr>
                                <tr>
                                    <td colspan="7">
                                        <table border>
                                            <tr>
                                                <th>Варианты ответов</th>
                                                <th>Ответы пользователя</th>
                                                <th>Правильные ответы</th>
                                            </tr>
                                            <c:forEach var="answer" items="${ques.answers}">
                                                <tr>
                                                    <td>${answer.answerName}</td>
                                                    <td class="mytd">
                                                        <c:if test="${statistic.listOfUserAnswer.contains(answer.answerId)}">
                                                            <p>V</p>
                                                        </c:if>
                                                    </td>
                                                    <td class="mytd">
                                                        <c:if test="${answer.right}">
                                                            <p>V</p>
                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </td>
                    <td colspan="2"></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<jsp:include page="../includes/footer.jsp"/>
</body>
<script>
    document.addEventListener("DOMContentLoaded", ready);
    function ready(){
        document.getElementById("1").className += " active";
        const begin = 0;
        const end = 4;
    }

    function getActive(id){
        const el = document.getElementsByClassName("active")[0];
        el.className = el.className.replace(" active", "");
        document.getElementById(id).className += " active";
    }

    function stepLeft(){
        let id = document.getElementsByClassName("active")[0].id;
        id--;
        if (id >= 1){
            document.getElementById(id).click();
        }
    }

    function stepRight(){
        let id = document.getElementsByClassName("active")[0].id;
        id++;
        if (id <= document.getElementsByClassName("pag").length){
            document.getElementById(id).click();
        }
    }

    function openDetail(count){
        const button = document.getElementById("btn" + count);
        if (button.innerText === "Подробнее"){
            button.innerText = "Скрыть";
            button.className = button.className.replace("info", "warning");
            const el = document.getElementsByClassName("table" + count);
            for (let i = 0; i < el.length; i++) {
                el[i].removeAttribute("hidden");
            }
        }
        else if (button.innerText === "Скрыть"){
            button.innerText = "Подробнее";
            button.className = button.className.replace("warning", "info");
            const el = document.getElementsByClassName("table" + count);
            for (let i = 0; i < el.length; i++) {
                el[i].setAttribute("hidden", true);
            }
        }
    }

    function hideDetail(){
        document.getElementById("table").removeAttribute("hidden");
        document.getElementById("table2").setAttribute("hidden", true);
        document.getElementById("forHide").setAttribute("hidden", true);
    }

</script>
<jsp:include page="../includes/styles.jsp"/>
</html>
