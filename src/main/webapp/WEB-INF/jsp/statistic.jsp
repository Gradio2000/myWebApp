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

        <table id="table">
            <tr>
                <th>Дата и время</th>
                <th>Название теста</th>
                <th>Количество вопросов</th>
                <th>Количество правильных ответов</th>
                <th>Количество неправильных ответов</th>
            </tr>
            <jsp:useBean id="statisticList" scope="request" type="java.util.List"/>
            <c:forEach var="statistic" items="${statisticList}" varStatus="count">

                <tr>
                    <td>${statistic.date}</td>
                    <td>${statistic.test.testName}</td>
                    <td class="mytd">${statistic.test.questions.size()}</td>
                    <td class="mytd">${statistic.falseAnswerSet.size()}</td>
                    <td class="mytd">${statistic.trueAnswer}</td>
                    <td><button id="btn${count.count}" class="btn info" onclick="openDetail(${count.count})">Подробнее</button> </td>
                </tr>

                <tr id="hidden${count.count}" hidden>
                    <th>Варианты ответов</th>
                    <th>Ответы пользователя</th>
                    <th>Правильные ответы</th>
                </tr>
            </c:forEach>
        </table>

        <table id="table2" hidden>
            <tr>
                <td>KKK</td>
            </tr>
        </table>
        <button id="forHide" hidden class="btn warning" onclick="hideDetail()">Скрыть</button>
    </div>
</div>

<jsp:include page="../includes/footer.jsp"/>
</body>
<script>
    function openDetail(count){
        const button = document.getElementById("btn" + count);
        document.getElementById("forHide").removeAttribute("hidden")
        document.getElementById("table").setAttribute("hidden", true);
        document.getElementById("table2").removeAttribute("hidden");
    }

    function hideDetail(){
        document.getElementById("table").removeAttribute("hidden");
        document.getElementById("table2").setAttribute("hidden", true);
        document.getElementById("forHide").setAttribute("hidden", true);
    }

</script>
<jsp:include page="../includes/styles.jsp"/>
</html>
