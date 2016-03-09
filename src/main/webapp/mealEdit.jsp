<%--
  Created by IntelliJ IDEA.
  User: bolshakov-as
  Date: 09.03.2016
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit meal</title>
</head>
<body>

<form method="post" action="meals">
    <c:param name="meal">
        <input type="text"  readonly name="id" value="${meal.getId()}" />
        <input type="datetime"  name="dateTime" value="${meal.getDateTime()}" />
        <input type="text"  name="description" value="${meal.getDescription()}" />
        <input type="number"  name="calories" value="${meal.getCalories()}" />
        <input type="submit" name="but" value="EditOK">
    </c:param>
</form>
</body>
</html>
