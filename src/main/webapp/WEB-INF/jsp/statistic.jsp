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
<jsp:useBean id="statisticList" scope="session" type="java.util.List"/>
<jsp:useBean id="userForStatistic"  scope="session" type="ru.laskin.myWebApp.model.User"/>


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

        <div class="pagination" style="display: block">
                <a onclick="stepLeft()">&laquo;</a>
                <c:forEach var="f" items="${statisticList}" step="10" varStatus="count">
                    <div class="div-pag">
                        <a class="pag" id="${count.count}" onclick="getActive(this.id)">${count.count}</a>
                    </div>
                </c:forEach>
                <a onclick="stepRight()">&raquo;</a>
            </div>

        <form action="/detailResultForAdmin" method="post">
            <input id="statisticId" hidden name="statisticId" />
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
                        <td><button type="button" id="btn${count.count}" class="btn info" onclick="openDetail(${count.count})">Подробнее</button> </td>
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
                                            <tr>
                                                <td>
                                                    <button class="tr${count.count} btn info" type="submit" hidden>Еще подробнее</button>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr></tr>
                </c:forEach>
            </table>
        </form>
    </div>
</div>

<jsp:include page="../includes/footer.jsp"/>
</body>
<script>
    document.addEventListener("DOMContentLoaded", ready);
    function ready(){
        document.getElementById("1").className += " active";
        const el = document.getElementsByClassName("line");
        if (el.length > 10){
            for (let i = 0; i < 10; i++) {
                el[i].removeAttribute("hidden");
            }
        }
        else {
            for (let i = 0; i < el.length; i++) {
                el[i].removeAttribute("hidden");
            }
        }
    }


    function getActive(id){
        const el = document.getElementsByClassName("active")[0];
        el.className = el.className.replace(" active", "");
        document.getElementById(id).className += " active";
        const start = (id - 1) * 10;
        const end = (id - 1) * 10 + 9;

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
        document.getElementById("statisticId").setAttribute("value", count-1);
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
