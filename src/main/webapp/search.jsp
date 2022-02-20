<%@ page import="Entities.Sheet" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Поиск запесей об отсутсви на работе</h1>
<br/>
<form method="post" action="/my-app/search">
    <ul>
        <li>
            <label for="name">ФИО:</label>
            <input type="text" id="name" name="name">
        </li>
        <li>
            <label for="date">Дата отсутствия:</label>
            <input type="date" id="date" name="date">
        </li>
    </ul>
    <input type="submit" value="Submit"/>
</form>
<br/>
<% List<Sheet> sheets = (List) request.getAttribute("sheets");
%>
<table>
    <% for (Sheet sheet : sheets) { %>
    <tr>
        <td><%= sheet.getName() %></td>
        <td><%= sheet.getPost() %></td>
        <td><%= sheet.getDate() %></td>
        <td><%= sheet.getTime() %></td>
        <td><%= sheet.getReason() %></td>
    </tr>
<% } %>
</table>
</body>
</html>
