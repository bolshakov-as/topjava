<%--
  Created by IntelliJ IDEA.
  User: bolshakov-as
  Date: 04.03.2016
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    </tr>
    </thead>
    <c:forEach items="${meals}" var="meal">
        <tr class="${meal.isExceed()?"red":"normal"}">
            <td>${meal.getDateTime()}</td>
            <td>${meal.getDescription()}</td>
            <td>${meal.getCalories()}</td>
            <td>
                <form method="post" action="meals">
                    <input type="hidden" name="id" value="${meal.getId()}" />
                    <input type="submit" name="but" value="Delete">
                    <input type="submit" name="but" value="Edit">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
