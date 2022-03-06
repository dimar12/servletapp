<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Табель отсутствия на работе</h1>
<br/>
<% List<String> names = (List) request.getAttribute("names"); %>
<% List<String> posts = (List) request.getAttribute("posts"); %>
<form method="post" action="/my-app/form">
    <ul>
        <li>
        <label for="name">ФИО:</label>
        <select id="name" name="name">
            <% for (String name : names) { %>
                <option><%= name %></option>
            <% } %>
        </select>
        </li>
        <li>
            <label for="post">Должность:</label>
            <select id="post" name="post">
                <% for (String post : posts) { %>
                <option><%= post %></option>
                <% } %>
            </select>
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
