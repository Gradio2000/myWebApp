
<%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 03.04.2021
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>



<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"          prefix="c"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"           prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql"           prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml"           prefix="x"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"     prefix="fn"  %>
<%@ taglib uri="http://www.springframework.org/tags/form"   prefix="sf"  %>


<jsp:useBean id="question" class="ru.laskin.myWebApp.model.Question"/>
<jsp:useBean id="falseAnswerSet" scope="request" type="java.util.Set"/>
<jsp:useBean id="trueAnswer" scope="request" type="java.lang.Integer"/>
<jsp:useBean id="questionList" scope="request" type="java.util.List"/>
<jsp:useBean id="users" scope="request" type="ru.laskin.myWebApp.model.User"/>
<jsp:useBean id="tests" scope="request" type="ru.laskin.myWebApp.model.Test"/>
        

<html>
<head>
    <title>Результат</title>
</head>

<style>
    .false{
        color: #da190b;
    }

    .true{
        color: #2c9751;
    }
</style>
<body>

<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <th>Дата</th>
        <th>Имя</th>
        <th>Должность</th>
        <th>Название теста</th>
        <th>Количество вопросов</th>
        <th>Количество правильных ответов</th>
        <th>Количество неправильных ответов</th>
    </tr>
    <td>${data}</td>
    <td>${users.name}</td>
    <td>${users.position}</td>
    <td>${tests.testName}</td>
    <td>${questionList.size()}</td>
    <td>${trueAnswer}</td>
    <td>${falseAnswerSet.size()}</td>

</table>

<button id="detailBtn" onclick=detail(this.id)>Подробнее</button>
<div hidden class="detail">
    <table>
        <c:forEach var="ques" items="${questionList}" varStatus="count">
            <p>Вопрос № ${count.count} ${ques.questionName}
                <c:if test="${falseAnswerSet.contains(ques.questionId)}">
                    <p1 class="false">Не верно</p1>
                </c:if>
                <c:if test="${!falseAnswerSet.contains(ques.questionId)}">
                    <p2 class="true">Верно</p2>
                </c:if>
            </p>

            <table border="1">
                <tr>
                    <th>Варианты ответов</th>
                    <th>Ответы пользователя</th>
                    <th>Правильные ответы</th>
                </tr>
                <c:forEach var="answ" items="${ques.answers}" varStatus="count">
                    <jsp:useBean id="listOfUsersAnswers" scope="request" type="java.util.List"/>
                    <tr>
                        <td>${answ.answerName}</td>
                        <td>
                            <c:if test="${listOfUsersAnswers.contains(answ.answerId)}">
                                <p>V</p>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${answ.right}">
                                <p>V</p>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:forEach>
    </table>
</div>
<br/>
<a href="/logout">Выход</a>
</body>
<script>
    function detail(id){
        if (id === 'detailBtn'){
            document.getElementsByClassName("detail")[0].removeAttribute("hidden");
            const button = document.getElementById("detailBtn");
            button.innerText = 'Скрыть';
            button.id = 'hide';
        }
        if (id === 'hide'){
            document.getElementsByClassName("detail")[0].setAttribute("hidden", true);
            const button = document.getElementById("hide");
            button.innerText = 'Подробнее';
            button.id = 'detailBtn';
        }
    }
</script>
</html>
