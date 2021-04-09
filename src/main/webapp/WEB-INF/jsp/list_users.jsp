<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="user" scope="request" class="ru.laskin.myWebApp.model.User"/>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        table {
            border-collapse: collapse;
            border-spacing: 0;
            width: 100%;
            border: 1px solid #ddd;
        }

        th, td {
            text-align: left;
            padding: 16px;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2
        }

        .btn {
            border: none;
            background-color: inherit;
            padding: 14px 28px;
            font-size: 16px;
            cursor: pointer;
            display: inline-block;
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


    </style>
</head>
<body>


<h1>Добрый день, ${authUser}!</h1>
<br/>
<h2>Список зарегистрированных пользователей</h2>

<table>
        <tr>
            <th>id</th>
            <th>Должность</th>
            <th>Имя</th>
            <th>email</th>
            <th>Роль</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach var='user' items='${users}'>
            <tr>
                <td><c:out value="${user.userId}"></c:out></td>
                <td><c:out value="${user.position}"></c:out></td>
                <td><c:out value="${user.name}"></c:out></td>
                <td><c:out value="${user.email}"></c:out></td>
                <td><c:out value="${user.adminRole}"></c:out></td>
                <td><button class="btn info">Статистика</button></td>
                <td><button class="btn warning" onclick="document.location = 'users/update?id=${user.userId}'">Редактировать</button></td>
                <td><button class="btn danger" onclick="document.location = 'users/delete?id=${user.userId}'">Удалить</button></td>
            </tr>
        </c:forEach>
</table>
<br/>
<button class="cancelbtn" onclick="document.location = '/logout'">Выход</button>
</body>
</html>

