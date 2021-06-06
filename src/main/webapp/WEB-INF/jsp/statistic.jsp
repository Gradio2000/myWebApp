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
<jsp:useBean id="statisticList" scope="request" type="java.util.List"/>
<jsp:useBean id="userForStatistic" scope="request" type="ru.laskin.myWebApp.model.User"/>


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
            <p>Имя пользователя: ${userForStatistic.name}</p>
            <p>Должность: ${userForStatistic.position}</p>
        </div>

        <div class="pagination">
                <a onclick="stepLeft()">&laquo;</a>
                <c:forEach var="f" items="${statisticList}" step="5" varStatus="count">
                    <a class="pag" id="${count.count}" onclick="getActive(this.id)">${count.count}</a>
                </c:forEach>
                <a onclick="stepRight()">&raquo;</a>
            </div>


            <table id="table0">
                <tr>
                    <th>Дата и время</th>
                    <th>Название теста</th>
                    <th>Результат</th>
                </tr>
                <c:forEach var="statistic" items="${statisticList}" varStatus="count">
                    <tr id="line${count.count}" class="line" hidden>
                        <td>${statistic.date}</td>
                        <td>${statistic.test.testName}</td>
                        <td class="mytd">${statistic.testResult}</td>
                        <td><button id="btn${count.count}" class="btn info" onclick="openDetail(${count.count})">Подробнее</button> </td>
                    </tr>
                    <tr class="tr${count.count}" hidden>
                        <td colspan="4">
                            <table id="table1">
                                <tr class="tr"${count.count}>
                                    <td colspan="7">
                                        <table style="width: 500px">
                                            <tr>
                                                <td>Затраченное время</td>
                                                <td class="mytd">${statistic.time}</td>
                                            </tr>
                                            <tr>
                                                <td>Количество заданных вопросов</td>
                                                <td class="mytd">${statistic.test.questions.size()}</td>
                                            </tr>
                                            <tr>
                                                <td>Количество правильных ответов</td>
                                                <td class="mytd">${statistic.trueAnswer}</td>
                                            </tr>
                                            <tr>
                                                <td>Количество неправильных ответов</td>
                                                <td class="mytd">${statistic.falseAnswerSet.size()}</td>
                                            </tr>
                                            <tr>
                                                <td>Критерий прохождения теста</td>
                                                <td class="mytd">${statistic.test.criteria}%</td>
                                            </tr>
                                            <tr>
                                                <td>Результат</td>
                                                <td class="mytd">${statistic.result}%</td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr class="tr${count.count}" hidden>
                        <td colspan="4">
                            <c:forEach var="ques" items="${statistic.test.questions}" varStatus="counter">
                                <table>

                                        <h5 style="padding-top: 40px" class="my-format">Вопрос № ${counter.count}:
                                            <br>${ques.questionName}</h5>
                                        <tr>
                                            <th>Варианты ответов</th>
                                            <th>Ответы пользователя</th>
                                            <th>Правильные ответы</th>
                                        </tr>
                                        <tr>
                                            <c:forEach var="answer" items="${ques.answers}">
                                        <tr style="height: 90px">
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
                                        </tr>
                                        </c:forEach>
                            </table>
                        </td>
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
        const el = document.getElementsByClassName("line");
        for (let i = 0; i < 5; i++) {
            el[i].removeAttribute("hidden");
        }
    }

    function getActive(id){
        const el = document.getElementsByClassName("active")[0];
        el.className = el.className.replace(" active", "");
        document.getElementById(id).className += " active";
        const start = (id - 1) * 5;
        const end = (id - 1) * 5 + 4;

        const line = document.getElementsByClassName("line");

        for (let i = 0; i < line.length; i++) {
            line[i].setAttribute("hidden", true);
        }

        for (let i = start; i <= end; i++) {
                line[i].removeAttribute("hidden");
        }

        const detailBtn = document.getElementsByClassName("detail-btn");
        if (detailBtn.length !== 0){
            for (let i = 0; i < detailBtn.length; i++) {
                detailBtn[i].click();
                detailBtn[i].className = detailBtn[i].className.replace(" detail-btn", "");
            }
        }
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
            button.className += " detail-btn";
            const eltr = document.getElementsByClassName("tr" + count);
            for (let i = 0; i < eltr.length; i++) {
                eltr[i].removeAttribute("hidden");
            }
        }
        else if (button.innerText === "Скрыть"){
            button.innerText = "Подробнее";
            button.className = button.className.replace("warning", "info");
            const eltr = document.getElementsByClassName("tr" + count);
            for (let i = 0; i < eltr.length; i++) {
                eltr[i].setAttribute("hidden", true);
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
