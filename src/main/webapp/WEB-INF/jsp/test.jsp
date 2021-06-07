























<html>
<head>

    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous"/>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="/resources/Site2/nicepage.css" media="screen"/>
    <script class="u-script" type="text/javascript" src="/resources/Site2/jquery.js" defer=""></script>
    <script class="u-script" type="text/javascript" src="/resources/Site2/nicepage.js" defer=""></script>
    <meta name="generator" content="Nicepage 3.12.0, nicepage.com"/>
    <link id="u-theme-google-font" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i|Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i"/>


    <title>Результат</title>
</head>

<body>

<header class="u-clearfix u-grey-15 u-header u-sticky u-header" id="sec-1274">
    <div class="u-clearfix u-sheet u-sheet-1">
        <div class="u-image u-logo u-image-1" data-image-width="330" data-image-height="150">
            <img src="/resources/Site2/images/horizontal_on_white_by_logaster.png" class="u-logo-image u-logo-image-1" data-image-width="97"/>
        </div>
        <nav class="u-menu u-menu-dropdown u-offcanvas u-menu-1">
            <div class="u-custom-menu u-nav-container">
                <ul class="u-nav u-unstyled u-nav-1"><li class="u-nav-item"><a class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="/login" style="padding: 10px 20px;">Главная</a>
                </li><li class="u-nav-item"><a class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="/allTests" style="padding: 10px 20px;">Все тесты</a>
                </li><li class="u-nav-item"><a class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="/room" style="padding: 10px 20px;">Пупкин Иван Федорович</a>
                </li><li class="u-nav-item"><a class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="/logout" style="padding: 10px 20px;">Выход</a>
                </li></ul>
            </div>
        </nav>
    </div>
</header>

<div class="wrapper">
    <div class="content">
        <table cellpadding="8" cellspacing="0">
            <tr>
                <th>Дата</th>
                <th>Название теста</th>
                <th>Результат</th>
            </tr>
            <tr>
                <td class="mytd">07.06.2021 22:41</td>
                <td>Для ревизии</td>
                <td class="mytd">Тест не пройден</td>
                <td>
                    <button id="detailBtn" class="btn warning" onclick=detail(this.id)>Подробнее</button>
                </td>
            </tr>
        </table>

        <div hidden class="detail">
            <table style="width: 500px">
                <tr>
                    <td>Затраченное время</td>
                    <td class="mytd">Не учитывалось</td>
                </tr>
                <tr>
                    <td>Количество заданных вопросов</td>
                    <td class="mytd">1</td>
                </tr>
                <tr>
                    <td>Количество правильных ответов</td>
                    <td class="mytd">0</td>
                </tr>
                <tr>
                    <td>Количество неправильных ответов</td>
                    <td class="mytd">1</td>
                </tr>
                <tr>
                    <td>Критерий прохождения теста</td>
                    <td class="mytd">10.0%</td>
                </tr>
                <tr>
                    <td>Результат</td>
                    <td class="mytd">0.0%</td>
                </tr>
            </table>
            <table>
                <h5>Вопрос № 1:
                    <br>
                    Вопрос 4
                    </br>
                </h5>
                <h5>
                    <p class="false">Не правильный ответ</p>
                </h5>

                <table border="1">
                    <tr>
                        <th>Варианты ответов</th>
                        <th>Ваши ответы</th>
                        <th>Правильные ответы</th>
                    </tr>

                    <tr style="height: 90px">
                        <td>Ответ на вопрос 4</td>
                        <td class="mytd">

                        </td>
                        <td class="mytd">

                        </td>
                    </tr>

                    <tr style="height: 90px">
                        <td>Ответ на вопрос 4</td>
                        <td class="mytd">

                        </td>
                        <td class="mytd">

                            <p>V</p>

                        </td>
                    </tr>

                    <tr style="height: 90px">
                        <td>Ответ на вопрос 4</td>
                        <td class="mytd">

                            <p>V</p>

                        </td>
                        <td class="mytd">

                        </td>
                    </tr>

                </table>
            </table>
        </div>
        <br/>
        <button type="button" class="btn danger" onclick="document.location = '/start'">Вперед</button>
    </div>
</div>

<footer class="footer u-align-center u-clearfix u-grey-80 " id="sec-3569">
    <div class="u-clearfix u-sheet u-sheet-1">
        <p class="u-small-text u-text u-text-variant u-text-1">Программа тестирования сотрудников организации.</p>
    </div>
</footer>
</body>
<style>

    th, td {
        width: 10px;
    }

    textarea {
        width: -webkit-fill-available;
    }
    .mytd{
        text-align: center;
    }

    * {
        margin: 0;
        padding: 0;
    }
    .content {
        min-height: calc(100vh - 80px);
        margin-left: 50px;
        margin-right: 50px;
        width: 70%;
        padding: 10px;
        margin: auto;
    }

    .content-inside {
        padding: 20px;
        padding-bottom: 50px;
    }

    table {
        border-collapse: collapse;
        border-spacing: 0;
        width: 100%;
        /*border: 1px solid #ddd;*/
    }

    td {
        text-align: left;
        padding: 16px;
        width: revert;
    }
    th {
        text-align: center;
        padding: 16px;
        width: revert;
    }

    tr:nth-child(even) {
        background-color: #f2f2f2
    }

    .mytr{
        background-color: antiquewhite;
    }

    .btn {
        border: none;
        background-color: inherit;
        padding: 14px 28px;
        font-size: 16px;
        cursor: pointer;
        display: inline-block;
    }

    .mybtn {
        border: none;
        background-color: #929998;
        /*padding: 14px 28px;*/
        font-size: 16px;
        cursor: pointer;
        display: inline-block;
    }
    .my-btn {
        padding-top: 20px;
        padding-left: 40px;
        padding-right: 40px;
        padding-bottom: 20px;
    }


    .false{
        color: #da190b;
    }

    .true{
        color: #2c9751;
    }

    /* Green */
    .success {
        color: green;
    }

    .success:hover {
        background-color: #4CAF50;
        color: white;
    }

    /* Blue */
    .info {
        color: dodgerblue;
    }

    .info:hover {
        background: #2196F3;
        color: white;
    }

    .close{
        color: #81888b;
    }

    .close:hover {
        background: #99a1a5;
        color: white;
    }

    /* Orange */
    .warning {
        color: orange;
    }

    .warning:hover {
        background: #ff9800;
        color: white;
    }

    /* Red */
    .danger {
        color: red;
    }

    .danger:hover {
        background: #f44336;
        color: white;
    }

    /* Gray */
    .default {
        color: black;
    }

    .default:hover {
        background: #e7e7e7;
    }

    .cancelbtn {
        border: none;
        color: white;
        padding: 14px 28px;
        font-size: 16px;
        cursor: pointer;
        background-color: #f44336;
    }
    .cancelbtn:hover {background: #da190b;}

    .footer{
        position: relative;
        left:0px;
        bottom:0px;
        height:100px;
        width:100%;
    }
    html{
        height: 100%;
    }
    body{
        display: flex;
        flex-direction: column;
        height: 100%;
    }

    header{
        /* 0 flex-grow, 0 flex-shrink, auto flex-basis */
        flex: 0 0 auto;
    }
    .main-content{
        /* 1 flex-grow, 0 flex-shrink, auto flex-basis */
        flex: 1 0 auto;
    }
    footer{
        /* 0 flex-grow, 0 flex-shrink, auto flex-basis */
        flex: 0 0 auto;
    }

    .container-my-big {
        width: 100%;
        padding-right: var(--bs-gutter-x, 0.75rem);
        padding-left: var(--bs-gutter-x, 0.75rem);
        margin-right: auto;
        margin-left: auto;
        max-width: 1080px;
    }

    .container-my-md {
        width: 100%;
        padding-right: var(--bs-gutter-x, 0.75rem);
        padding-left: var(--bs-gutter-x, 0.75rem);
        /*margin-right: 0;*/
        margin-left: auto;
        max-width: 960px;
    }

    .container-my-sm {
        width: 100%;
        padding-right: var(--bs-gutter-x, 0.75rem);
        padding-left: var(--bs-gutter-x, 0.75rem);
        /*margin-right: 0;*/
        margin-left: auto;
        max-width: 720px;
    }

    .pagination a {
        color: black;
        float: left;
        padding: 8px 16px;
        text-decoration: none;
        transition: background-color .3s;
    }

    .pagination a.active {
        background-color: dodgerblue;
        color: white;
    }

    .pagination a:hover:not(.active) {
        background-color: #ddd;
    }

    .countdown{
        color: #da190b;
        font-size: x-large;
        width: 175.75px;
        text-align: center;
    }

    .my-box {
        margin-top: 20px;
        -webkit-box-shadow: 7px 10px 11px -4px rgba(34, 60, 80, 0.18);
        -moz-box-shadow: 7px 10px 11px -4px rgba(34, 60, 80, 0.18);
        box-shadow: 7px 10px 11px -4px rgba(34, 60, 80, 0.18);
        background-color: #f8f6f6;
        padding-top: 20px;
        padding-left: 40px;
        padding-right: 40px;
        padding-bottom: 20px;
    }

    input[type=text], select, textarea, password {
        width: 100%;
        padding: 12px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
        margin-top: 6px;
        margin-bottom: 16px;
        resize: vertical;
    }

    .my-input {
        width: 100%;
        padding: 12px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
        margin-top: 6px;
        margin-bottom: 16px;
        resize: vertical;
    }

</style>
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
