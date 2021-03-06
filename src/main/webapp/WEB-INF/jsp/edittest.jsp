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
<%@ taglib prefix="cf" uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:useBean id="tests" class="ru.laskin.myWebApp.model.Test"/>
<jsp:useBean id="q" class="ru.laskin.myWebApp.model.Question"/>

<!doctype html>
<html>
<head>
    <jsp:include page="../includes/settingsHeader.jsp"/>
    <title>Редактирование теста</title>
</head>

<body>
<jsp:include page="../includes/header.jsp"/>
<div class="wrapper">
    <div class="content">
        <section class="main-content">
            <main class="u-body">
                <sf:form name="form1" action="/updateTest" method="post" modelAttribute="test" onsubmit="return getCount()">
                    <c:set var="IDTest" value="${test.testId}"/>
                    <sf:hidden path="testId"/>
                    <input name="groupId" type="hidden" value="${test.groupTest.groupTestId}">

                    <div class="container-my-big">
                        <label for="textTestName"><h4>Редактирование теста</h4></label>
                        <textarea class="form-control" name="testName" id="textTestName">${test.testName}</textarea>
                        <p>Зарегистрировано вопросов: ${test.questions.size()}</p>
                        <table border="0">
                            <tr class="mytr">
                                <td>
                                    <label>Результат определяется процентным соотношением правильных ответов</label>
                                </td>
                                <td>
                                    <input id="criteria" name="criteria" value="${test.criteria}"> %</input>
                                </td>
                            </tr>
                            <tr class="mytr">
                                <td>
                                    <label>Время для прохождения теста (ноль - если не учитывать время)</label>
                                </td>
                                <td>
                                    <input id="time" name="time" value="${test.time}"> минут</input>
                                </td>
                            </tr>
                            <tr class="mytr">
                                <td>
                                    <label> Количество вопросов для тестирования</label>
                                </td>
                                <td>
                                    <input id="quesAcc" name="quesAmount" value="${test.quesAmount}"/>
                                </td>
                            </tr>
                            <tr class="mytr">
                                <td>
                                    <label> Предоставить вопросы в случайном порядке</label>
                                </td>
                                <td>
                                    <input type="checkbox" id="quesMix" name="quesMix" value="${test.quesMix}" checked/>
                                </td>
                            </tr>
                        </table>

                        <button type="button" class="btn info" data-bs-toggle="modal" data-bs-target="#staticBackdrop2" style="margin-top: 20px">Загрузить вопросы</button>

                        <div>
                            <label for="sel" style="margin-left: auto; margin-top: 10px; margin-bottom: auto; padding-right: 10px">Показывать по</label>
                            <select id="sel" style="width: auto; padding: 0px; margin-bottom: auto" onchange="countOfQues(this.selectedIndex)">
                                <option>5</option>
                                <option selected>10</option>
                                <option>20</option>
                                <option>все</option>
                            </select>
                        </div>


                        <div class="pagination" style="margin-top: 10px">
                            <a id="stepLeft" onclick="stepLeft()">&laquo;</a>
                                    <c:forEach var="f" items="${test.questions}" step="10" varStatus="count">
                                        <div class="div-pag">
                                            <a class="pag" id="${count.count}" onclick="getActive(this.id)">${count.count}</a>
                                        </div>
                                    </c:forEach>
                            <a id="stepRight" onclick="stepRight()">&raquo;</a>

                        </div>

                            <c:forEach var="ques" items="${test.questions}" varStatus="count">

                                <div class="container-my-sm my-box line" hidden>

                                    <input hidden name="questionId" value="${ques.questionId}">
                                    <label for="textQuestionName"><h4>Вопрос № ${count.count} (id ${ques.questionId})</h4></label>
                                    <textarea class="form-control" name="question" id="textQuestionName" placeholder="Введите вопрос" required>${ques.questionName}</textarea>
                                    <button id="deleteQues" type="button" class="btn danger">Удалить вопрос</button>
                                    <hr>
                                    <label for="textAnswerName"><h5>Ответы на вопрос № ${count.count}</h5></label>

                                    <c:forEach var="answer" items="${ques.answers}">
                                        <div id="answerF">

                                            <input hidden name="answerId" value="${answer.answerId}">
                                            <input hidden name="quesAnsId" value="${answer.question.questionId}">
                                            <textarea class="form-control" name="answer" id="textAnswerName" placeholder="Введите ответ" required>${answer.answerName}</textarea>

                                            <cf:if test="${answer.right==true}">
                                                <label><input class="checks" type="checkbox" name="isRight" checked> Правильный ответ</label>
                                            </cf:if>
                                            <cf:if test="${answer.right == false}">
                                                <label><input type="checkbox" name="isRight"> Правильный ответ</label>
                                            </cf:if>

                                            <button id="delete" type="button" class="btn danger">Удалить ответ</button>


                                        </div>
                                    </c:forEach>

                                    <div id="forAddAnswer${ques.questionId}"></div>

                                    <button type="button" class="btn success" onclick="addAnswer(${ques.questionId})">Добавить ответ</button>



                                </div>
                            </c:forEach>
                        <button class="btn success" type="submit" style="margin-top: 20px">Готово</button>


                        <!-- Button trigger modal -->
                        <button type="button" class="btn info" data-bs-toggle="modal" data-bs-target="#staticBackdrop" style="margin-top: 20px">
                            Добавить вопрос
                        </button>
                    </div>
                </sf:form>

            </main>

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

                                    <button type="button" class="btn warning" id="addAnswer" style="margin-left: auto; text-align: center">Добавить ответ</button>
                                    <button type="button" class="btn danger" data-bs-dismiss="modal">Отмена</button>
                                    <button type="submit" class="btn success">Сохранить</button>

                            </form>
                        </div>
                    </div>
                </div>
                <div class="rasp"></div>
            </div>
        </section>
    </div>
</div>

<%--modal2--%>
<div class="modal fade" id="staticBackdrop2" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel2">Загрузка файла</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form method="POST" action="/uploadFile" enctype="multipart/form-data">
                    <input name="id" hidden value="${test.testId}"/>
                    <input type="file" name="file"><br />
                    <input type="submit" class="btn success" value="OK" style="margin-top: 10px; width: 100%">
                </form>
            </div>
        </div>
    </div>
    <div class="rasp"></div>
</div>

<jsp:include page="../includes/footer.jsp"/>

</body>
<jsp:include page="../includes/styles.jsp"/>

<script>

    document.addEventListener("DOMContentLoaded", ready);
    function ready(){
        document.getElementById("1").className += " active";
        const el = document.getElementsByClassName("line");
        if (el.length > 10){
            for (let i = 0; i < 10; i++) {
                el[i].removeAttribute("hidden");
            }
        }
        else {
            for (let i = 0; i < el.length; i++) {
                el[i].removeAttribute("hidden");
            }
        }
    }

    let number = 10;
    function countOfQues(selectedIndex){

        if (selectedIndex === 0){
            number = 5;
        }
        if (selectedIndex === 1){
            number = 10;
        }
        if (selectedIndex === 2){
            number = 20;
        }
        if (selectedIndex === 3){
            number = ${test.questions.size()};
        }
        changeButtonsOfPages();
    }


    function getActive(id) {
        const el = document.getElementsByClassName("active")[0];
        el.className = el.className.replace(" active", "");
        document.getElementById(id).className += " active";
        const start = (id - 1) * number;
        const end = (id - 1) * number + (number - 1);

        const line = document.getElementsByClassName("line");

        for (let i = 0; i < line.length; i++) {
            line[i].setAttribute("hidden", true);
        }

        for (let i = start; i <= end; i++) {
            line[i].removeAttribute("hidden");
        }

        const detailBtn = document.getElementsByClassName("detail-btn");
        if (detailBtn.length !== 0) {
            for (let i = 0; i < detailBtn.length; i++) {
                detailBtn[i].click();
                detailBtn[i].className = detailBtn[i].className.replace(" detail-btn", "");
            }
        }
    }


    //функция внедрения нового количество кнопок страничек
    function changeButtonsOfPages(){

        //удаляем старые кнопочки страниц
        const elForDel = document.getElementsByClassName("div-pag");
        for (let i = 0; i < elForDel.length; i++) {
          const parent = elForDel.item(i).parentNode;
          parent.removeChild(elForDel.item(i));
          i--;
        }

        //вставляем на страницу новые кнопочки страниц

        //создаем элементы
        const countOfQues = ${test.questions.size()};
        let countOfPages = 0;
        if (number === countOfQues){
            countOfPages = 1;
        }
        else {
            countOfPages = Math.ceil(countOfQues/number);
        }

        for (let i = 0; i < countOfPages; i++) {
            //создаем div
            var div_pag = $('<div/>', {
                'class': 'div-pag'
            });

            $('<a/>', {
                class: 'pag',
                id: i + 1,
                onclick: 'getActive(this.id)',
                html: i + 1,
            }).appendTo(div_pag);

            //добавляем div в div
            // div_pag.appendTo($('#forInject'));
            div_pag.insertBefore($('#stepRight'));

        }

        //делаем первую кнопочку активной
        document.getElementById("1").className += " active";
        document.getElementById("1").click();
    }

    function stepLeft(){
        let id = document.getElementsByClassName("active")[0].id;
        id--;
        if (id >= 1){
            document.getElementById(id).click();
        }
    }

    function stepRight(){
        let id = document.getElementsByClassName("active")[0].id;
        id++;
        if (id <= document.getElementsByClassName("pag").length){
            document.getElementById(id).click();
        }
    }

    function validateForm() {
        if(document.getElementsByName("answerName").length === 0){
            alert("Добавьте ответ");
            return false;
        }

        const form = document.getElementById('staticBackdrop');
        const checks = form.querySelectorAll('input[type=checkbox]');
        for (const check of checks ) {
            if (check.checked){
                return true;
            }
        }
        alert('Отметьте правильный ответ!')
        return false;
    }

    //функция динамического добавления полей главной страницы
    function addAnswer(id){
        //создаем поле
        $('<textarea/>', {
            name: 'newAnswer',
            class: 'form-control',
            style: 'height: 75px',
            placeholder: 'Введите ответ',
            required: ''
        }).appendTo($('#forAddAnswer'+id));

        $('<label><input class="modalCheck" type="checkbox" name="isRightForNewAnswer"> Правильный ответ</label>').appendTo($('#forAddAnswer'+id));
        $('<input/>', {
            hidden: '',
            name: 'quesIdForNewAnswer',
            value: id
        }).appendTo($('#forAddAnswer'+id));
    }

    //функция удаления ответа

    let li = document.querySelectorAll('#delete');
    for (var i = 0, len = li.length; i < len; i++) {
        li[i].onclick = function() {
            this.parentNode.remove(this);
        }
    }

    //функция удаления вопроса
    let liq= document.querySelectorAll('#deleteQues');
    for (var i = 0, len = liq.length; i < len; i++) {
        liq[i].onclick = function() {
            this.parentNode.remove(this);
        }
    }


    //переменная для использования при создании checkbox
    let count = 0;

    //функция динамического добавления полей модальной формы
    $('#addAnswer').click(function(e) {

        //создаем div
        var divAnswer = $('<div/>', {
            'class': 'answerBlock',
            'id': 'miniContainer'
        });

        //создаем div
        var divForm = $('<div/>', {
            'class': 'form-floating'
        });

        $('<h6>Ответ</h6>').appendTo(divForm);
        //создаем поле и добавляем его в div
        $('<textarea/>', {
            name: 'answerName',
            class: 'form-control',
            style: 'height: 100px',
            cols: '50',
            rows: '3',
            placeholder: 'Введите ответ',
            required: ''
        }).appendTo(divForm);

        //добавляем div в div
        divForm.appendTo(divAnswer);

        //создаем label и добавляем его в div
        var check = $('<label/>', {
            html: 'Правильный ответ ',
        }).appendTo(divAnswer);

        //создаем checkbox и добавляем его в label
        $('<input/>', {
            id: 'mycheck',
            class: 'validCheck',
            type: 'checkbox',
            name: 'isRight',
            value: count
        }).appendTo(check);

        //перенос строки
        $('<br/>').appendTo(divAnswer);

        //создаем кнопку отмены и добавляем ее в div и назначаем событие на клик
        $('<input/>', {
            value: 'Удалить ответ',
            type: 'button',
            class: 'deleteAnswer btn danger'
        }).appendTo(divAnswer).click(function(e) {
            $(this).parent().remove();
            e.preventDefault();
            return false;
        });

        //перенос строки
        $('<br/>').appendTo(divAnswer);

        //добавляе div в div
        divAnswer.appendTo($('#container'));

        //счетчик для right
        count = count + 1;

        e.preventDefault();
        return false;
    });

    function getCount(){
        if(document.getElementsByName("answer").length === 0 && document.getElementsByName("newAnswer").length === 0 ){
            alert("У теста должен быть хотя бы один вопрос!");
            return false;
        }

        const el = document.getElementsByName("isRight");
        for (let i = 0; i < el.length ; i++) {
            el[i].setAttribute("value", i)
        }

        const el2 = document.getElementsByName("isRightForNewAnswer");
        for (let i = 0; i < el2.length ; i++) {
            el2[i].setAttribute("value", i)
        }

        const questions = document.getElementsByClassName('container-my-sm');
        for (const question of questions ) {
            const checks = question.querySelectorAll('input[type=checkbox]');
            let count = 0;
            for (const check of checks ) {
                if (check.checked){
                    count++;
                    break;
                }
            }
            if (count === 0){
                alert('Необходимо выбрать хоть один правильный ответ!');
                return false;
            }
        }
    }

</script>
</html>
