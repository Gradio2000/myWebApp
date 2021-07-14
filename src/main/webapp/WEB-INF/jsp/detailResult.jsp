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

<script>
    window.onload = function () {
        document.getElementById("download")
            .addEventListener("click", () => {
                const invoice = this.document.getElementById("invoice");
                console.log(invoice);
                console.log(window);
                var opt = {
                    margin: 1,
                    filename: 'myfile.pdf',
                    image: { type: 'jpeg', quality: 0.98 },
                    html2canvas: { scale: 2 },
                    jsPDF: { unit: 'in', format: 'letter', orientation: 'portrait' }
                };
                html2pdf().from(invoice).set(opt).save();
            })
    }
</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.9.2/html2pdf.bundle.js"></script>

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
        width:220mm;
        margin-left: auto;
        margin-right: auto;
        background-color: #fbfbfb;
    }
    .list{
        /*width:220mm;*/
        padding: 10mm;
        margin-left: auto;
        margin-right: auto;
        background-color: #fbfbfb;
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
<div class="col-md-12 text-right mb-3">
    <button class="btn infolbtn" id="download">Сохранить pdf</button>
    <button class="btn cancelbtn" onclick="document.location='/greeting'">Вернуться</button>
</div>
    <div class="cont">
        <div class="list">
            <div id="invoice">
                <h3 align="center">Результаты тестирования</h3>
                    <table>
                    <tr>
                        <td style="width: 70mm">Тестируемый</td>
                        <td>${user.name}</td>
                    </tr>
                    <tr>
                        <td>Должность</td>
                        <td>${user.position.position}</td>
                    </tr>
                    <tr>
                        <td>Дата и время</td>
                        <td>${statistic.date}</td>
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
                        <td class="mytd">${statistic.quesList.size()}</td>
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
                    <c:forEach var="ques" items="${statistic.quesList}" varStatus="count">
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
<jsp:include page="../includes/styles.jsp"/>
</html>
