
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
<jsp:useBean id="listOfUsersAnswers" scope="request" type="java.util.List"/>


<html>
<head>
    <jsp:include page="../includes/settingsHeader.jsp"/>
    <title>Результат</title>
</head>

<body>
<jsp:include page="../includes/header.jsp"/>

    <div class="wrapper">
        <div class="content">
            <table cellpadding="8" cellspacing="0">
                <tr>
                    <th>Дата</th>
                    <th>Имя</th>
                    <th>Должность</th>
                    <th>Название теста</th>
                    <th>Результат</th>
                </tr>
                <td class="mytd">${data}</td>
                <td>${users.name}</td>
                <td>${users.position}</td>
                <td>${tests.testName}</td>
                <td class="mytd">${testResult}</td>
                <td>
                    <button id="detailBtn" class="btn warning" onclick=detail(this.id)>Подробнее</button>
                </td>

            </table>

            <div hidden class="detail">
                <table style="width: 500px">
                    <tr>
                        <td>Затраченное время</td>
                        <td class="mytd">${time}</td>
                    </tr>
                    <tr>
                        <td>Количество заданных вопросов</td>
                        <td class="mytd">${questionList.size()}</td>
                    </tr>
                    <tr>
                        <td>Количество правильных ответов</td>
                        <td class="mytd">${trueAnswer}</td>
                    </tr>
                    <tr>
                        <td>Количество неправильных ответов</td>
                        <td class="mytd">${falseAnswerSet.size()}</td>
                    </tr>
                    <tr>
                        <td>Критерий прохождения теста</td>
                        <td class="mytd">${tests.criteria}%</td>
                    </tr>
                    <tr>
                        <td>Результат</td>
                        <td class="mytd">${result}%</td>
                    </tr>
                </table>
                <table>
                    <c:forEach var="ques" items="${questionList}" varStatus="count">
                        <h5 style="padding-top: 40px" class="my-format">Вопрос № ${count.count} ${ques.questionName}
                            <c:if test="${falseAnswerSet.contains(ques.questionId)}">
                                <p1 class="false">Не правильный ответ</p1>
                            </c:if>
                            <c:if test="${!falseAnswerSet.contains(ques.questionId)}">
                                <p2 class="true">Правильный ответ</p2>
                            </c:if>
                        </h5>

                            <table border="1" class="my-format">
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
            <br/>
        </div>
    </div>
<jsp:include page="../includes/footer.jsp"/>
</body>
<jsp:include page="../includes/styles.jsp"/>
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
