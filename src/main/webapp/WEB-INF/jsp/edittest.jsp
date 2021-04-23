<%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 15.04.2021
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"       prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql"       prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml"       prefix="x"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"  %>


<jsp:useBean id="tests" class="ru.laskin.myWebApp.model.Test"/>
<jsp:useBean id="q" class="ru.laskin.myWebApp.model.Question"/>

<!doctype html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">

    <title>Test</title>


</head>
<body>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>

<sf:form name="form1" action="/updateTest" method="post" modelAttribute="test">
    <c:set var="IDTest" value="${test.testId}"/>
    <sf:hidden path="testId"/>
    <sf:hidden path="groupId"/>

    <label><br>Название теста<br></label>
    <sf:input path="testName" size="200"/>
        <c:forEach var="ques" items="${test.questions}">
            <label><br>Вопрос id = ${ques.questionId}<br></label>
            <input name="question" value="${ques.questionName}"><br>
            <input hidden name="questionId" value="${ques.questionId}">
            <c:forEach var="answer" items="${ques.answers}">
                <input name="answer" value="${answer.answerName}" ><br>
                <input hidden name="answerId" value="${answer.answerId}">
                <input hidden name="isRight" value="${answer.right}">
                <input hidden name="quesAnsId" value="${answer.question.questionId}">
            </c:forEach>
        </c:forEach>
    <button class="btn" type="submit">Готово</button>

</sf:form>

<!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
    Launch static backdrop modal
</button>

<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel">Добавление вопроса</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form name="formName" action="/addQuestion" method="POST">
                    <input hidden name="IDTest" value="${IDTest}">
                    <div class="form-floating">
                        <textarea name="questionName" class="form-control" placeholder="Leave a comment here" id="floatingTextarea1" style="height: 100px" required></textarea>
                        <label for="floatingTextarea2">Введите вопрос</label>
                    </div>
                    <br/>
                    <div class="form-floating">
                        <textarea name="answerName" class="form-control" placeholder="Leave a comment here" id="floatingTextarea2" style="height: 100px" required></textarea>
                        <label for="floatingTextarea2">Введите ответ</label>
                    </div>
                    <label><input type="checkbox" name="isRight"/>Правильный ответ</label>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                        <input type="submit" class="btn btn-primary" onclick="validate()">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>

    function validate(){
        if(!validateForm()){
            alert("You must check atleast one of the checkboxes");
            return false;
        }
        else return true;
    }


    function validateForm() {
        var c=document.getElementsByTagName('input');
        for (var i = 0; i<c.length; i++){
            if (c[i].type ==='checkbox') {
                if (c[i].checked) {
                    return true;
                }
            }
        }
        return false;
    }

</script>

</body>
</html>
