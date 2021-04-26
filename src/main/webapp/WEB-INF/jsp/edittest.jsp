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
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

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
                <form class="validForm" id="myForm" name="formName" action="/addQuestion" method="POST" onsubmit="return validateForm()">
                    <input hidden name="IDTest" value="${IDTest}">

                    <div class="form-floating">
                        <textarea name="questionName" class="form-control" placeholder="Leave a comment here" id="floatingTextarea1" style="height: 100px" required></textarea>
                        <label for="floatingTextarea1">Введите вопрос</label>
                    </div>

                    <br/>

                    <div class="answerContainer" id="container">
                    </div>


                    <div class="modal-footer">
                        <button type="button" class="btn btn-warning" id="addAnswer">Добавить ответ</button>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                        <button type="submit" class="btn btn-primary">Добавить</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<style>
    .add{
        float: left;
        color: #ffffff;
        background-color: #fcdb04;
        border-color: #af761b;
    }
</style>

<script>
    function validate(){
        if(!validateForm()){
            alert("You must check atleast one of the checkboxes");
            return false;
        }
        return true;
    }

    function validateForm() {
        var c = document.getElementsByTagName('input');
        for (var i = 0; i<c.length; i++){
            if (c[i].type ==='checkbox') {
                if (c[i].checked) {
                    return true;
                }
            }
        }
        return false;
    }

    var count = 0;

    $('#addAnswer').click(function(e) {

        var num = document.getElementsByName("answerBlock").length;

        var divAnswer = $('<div/>', {
            'class': 'answerBlock',
            'id': 'miniContainer'
        });

        var divForm = $('<div/>', {
           'class': 'form-floating'
        });

        $('<textarea/>', {
            name: 'answerName',
            class: 'form-control',
            style: 'height: 100px',
            cols: '50',
            rows: '3',
            required: ''
        }).appendTo(divForm);

        divForm.appendTo(divAnswer);

        var check = $('<label/>').html("Правильный ответ ").appendTo(divAnswer);

        $('<input/>', {
            id: 'mycheck',
            class: 'validCheck',
            type: 'checkbox',
            name: 'isRight',
            value: count
        }).appendTo(check);

        $('<br/>').appendTo(divAnswer);

        $('<input/>', {
            value: 'Удалить ответ',
            type: 'button',
            class: 'DeleteDynamicExtraField btn-danger'
        }).appendTo(divAnswer).click(function(e) {
            $(this).parent().remove();
            e.preventDefault();
            return false;
        });

        $('<br/>').appendTo(divAnswer);

        divAnswer.appendTo($('#container'));

        count = count + 1;

        e.preventDefault();
        return false;



    });

    //Для удаления первого поля
    $('.DeleteDynamicExtraField').click(function(e) {
        $(this).parent().remove();

        e.preventDefault();
        return false;
    });

</script>


</body>
</html>
