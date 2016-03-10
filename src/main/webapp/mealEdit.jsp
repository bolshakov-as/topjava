<%--
  Created by IntelliJ IDEA.
  User: bolshakov-as
  Date: 09.03.2016
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit meal</title>
</head>
<body>
<jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.UserMeal"/>
<form method="post" action="meals">
<input type="hidden"  readonly name="id" value="${meal.id}" />
    <input type="datetime-local"  name="dateTime" value="${meal.dateTime}" />
    <input type="text"  name="description" value="${meal.description}" />
    <input type="number"  name="calories" value="${meal.calories}" />
    <button type="submit">Save</button>
    <button onclick="window.history.back()">Cancel</button>
</form>
</body>
</html>
