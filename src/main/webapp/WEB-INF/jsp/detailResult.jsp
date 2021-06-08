<%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 08.06.2021
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Детализация</title>
</head>
<body>
<div class="detail">
    <table>
        <c:forEach var="ques" items="${questionList}" varStatus="count">
            <h5>Вопрос № ${count.count}:
                <br>
                        ${ques.questionName}
                </br>
            </h5>
            <h5>
                <c:if test="${falseAnswerSet.contains(ques.questionId)}">
                    <p class="false">Не правильный ответ</p>
                </c:if>
                <c:if test="${!falseAnswerSet.contains(ques.questionId)}">
                    <p class="true">Правильный ответ</p>
                </c:if>
            </h5>

            <table border="1">
                <tr>
                    <th>Варианты ответов</th>
                    <th>Ваши ответы</th>
                    <th>Правильные ответы</th>
                </tr>
                <c:forEach var="answ" items="${ques.answers}" varStatus="count">
                    <tr style="height: 90px">
                        <td>${answ.answerName}</td>
                        <td class="mytd">
                            <c:if test="${listOfUsersAnswers.contains(answ.answerId)}">
                                <p>V</p>
                            </c:if>
                        </td>
                        <td class="mytd">
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
</body>
</html>
