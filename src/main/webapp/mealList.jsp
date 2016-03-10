<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %><%--
  Created by IntelliJ IDEA.
  User: bolshakov-as
  Date: 04.03.2016
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>List of meal</title>
    <style>
        tr.red {
            color: red;
        }
    </style>
</head>
<body>

<table border="1">
    <thead>
    <tr>
        <td>Date</td>
        <td>Description</td>
        <td>Calories</td>
        <td></td>
        <td></td>
    </tr>
    </thead>
    <c:forEach items="${mealList}" var="meal">
        <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.UserMealWithExceed"/>
        <tr class="${meal.exceed?"red":"normal"}">
            <td>
                <%=TimeUtil.dateToFormat(meal.getDateTime())%>
            </td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td>
                <a href="meals?action=delete&id=${meal.id}">Delete</a>
            </td>
            <td>
                <a href="meals?action=edit&id=${meal.id}">Edit</a>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td><a href="meals?action=add">+ ADD</a></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
</table>


</body>
</html>
