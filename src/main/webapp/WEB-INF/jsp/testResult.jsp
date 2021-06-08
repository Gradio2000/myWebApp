
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
                    <th>Название теста</th>
                    <th>Результат</th>
                </tr>
                <tr>
                    <td class="mytd">${data}</td>
                    <td>${tests.testName}</td>
                    <td class="mytd">${testResult}</td>
                    <td>
                        <button id="detailBtn" class="btn warning" onclick="document.location = '/detailResult'">Подробнее</button>
                    </td>
                </tr>
            </table>


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
            <br/>
        </div>
    </div>
<jsp:include page="../includes/footer.jsp"/>
</body>
<jsp:include page="../includes/styles.jsp"/>
</html>
