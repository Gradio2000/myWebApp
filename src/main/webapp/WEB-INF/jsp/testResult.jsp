
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


<html>
<head>
    <jsp:include page="../includes/settingsHeader.jsp"/>
    <title>Результат</title>
</head>

<body>
<jsp:include page="../includes/header.jsp"/>

    <div class="wrapper">
        <div class="content">
            <form method="post" action="/detailResult">
                <table cellpadding="8" cellspacing="0">
                    <tr>
                        <th>Дата</th>
                        <th>Название теста</th>
                        <th>Результат</th>
                    </tr>
                    <tr>
                        <td class="mytd">${statistic.date}</td>
                        <td>${statistic.test.testName}</td>
                        <td class="mytd">${statistic.testResult}</td>
                        <td>
                            <button type="submit" id="detailBtn" class="btn warning">Подробнее</button>
                        </td>
                    </tr>
                </table>
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
            </form>


            <br/>
        </div>
    </div>
<jsp:include page="../includes/footer.jsp"/>
</body>
<jsp:include page="../includes/styles.jsp"/>
</html>
