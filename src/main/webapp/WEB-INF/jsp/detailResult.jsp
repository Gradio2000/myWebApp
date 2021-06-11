<%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 08.06.2021
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"          prefix="c"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"           prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql"           prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml"           prefix="x"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"     prefix="fn"  %>
<%@ taglib uri="http://www.springframework.org/tags/form"   prefix="sf"  %>


<html>
<head>
    <title>Детализация</title>
</head>
<style>

    table{
        border-collapse: collapse;
    }

    tr{
        height: 10mm;
    }

    td{
        text-align: justify;
        padding: 1mm;
    }
    .cont{
        /*height:297mm;*/
        width:240mm;
        margin-left: auto;
        margin-right: auto;
        background-color: #fbfbfb;
    }
    .list{
        /*width:220mm;*/
        padding: 10mm;
        margin-left: auto;
        margin-right: auto;
        background-color: whitesmoke;
    }
    .bigth{
        width: 170mm;
    }
    .smallth{
        width: 22mm;
        text-align: center;
    }
</style>
<body>
<div class="wrapper" style="background-color: #d6d6d6">
    <div class="cont">
        <div class="list">
            <h3 align="center">Результаты тестирования</h3>

            <table>
                <tr>
                    <td style="width: 70mm">Тестируемый</td>
                    <td>${user.name}</td>
                </tr>
                <tr>
                    <td>Наименование теста</td>
                    <td>${statistic.test.testName}</td>
                </tr>
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


                <c:forEach var="ques" items="${statistic.test.questions}" varStatus="count">
                    <table style="margin-top: 15mm">
                        <tr>
                            <td style="width: 70mm"><h4>Вопрос № ${count.count}:</td>
                            <td>
                                <h4>
                                    <c:if test="${statistic.falseAnswerSet.contains(ques.questionId)}">
                                        <p class="false">Неправильный ответ</p>
                                    </c:if>
                                    <c:if test="${!statistic.falseAnswerSet.contains(ques.questionId)}">
                                        <p class="true">Правильный ответ</p>
                                    </c:if>
                                </h4>
                            </td>
                        </tr>
                        <table>
                            <tr>
                                <td><h4>${ques.questionName}</h4></td>
                            </tr>
                        </table>

                    </table>


                    <table style="margin-top: 5mm" border="1">
                        <tr>
                            <th class="bigth">Варианты ответов</th>
                            <th class="smallth">Ваши ответы</th>
                            <th class="smallth">Правильные ответы</th>
                        </tr>
                        <c:forEach var="answ" items="${ques.answers}" varStatus="count">
                            <tr>
                                <td class="bigth">${answ.answerName}</td>
                                <td class="smallth">
                                    <c:if test="${statistic.listOfUserAnswer.contains(answ.answerId)}">
                                        <p>V</p>
                                    </c:if>
                                </td>
                                <td class="smallth">
                                    <c:if test="${answ.right}">
                                        <p>V</p>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>

                </c:forEach>

        </div>

    </div>
</div>
</body>
</html>
