<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Табель отсутствия на работе</h1>
<br/>
<form method="post" action="/my-app/form">
    <ul>
        <li>
            <label for="name">ФИО:</label>
            <input type="text" id="name" name="name">
        </li>
        <li>
            <label for="post">Должность:</label>
            <input type="text" id="post" name="post">
        </li>
        <li>
            <label for="date">Дата отсутствия:</label>
            <input type="date" id="date" name="date">
        </li>
        <li>
            <label for="time">Время отсутствия:</label>
            <input type="text" id="time" name="time">
        </li>
        <li>
            <label for="reason">Причина отсутствия:</label>
            <textarea id="reason" name="reason"></textarea>
        </li>
    </ul>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>
