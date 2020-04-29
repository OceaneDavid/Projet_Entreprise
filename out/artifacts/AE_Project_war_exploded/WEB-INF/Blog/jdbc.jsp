<%--
  Created by IntelliJ IDEA.
  User: svenja
  Date: 27/04/2020
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset = "utf-8"
    <title>Tests JDBS</title>
    <link type = "text/css" rel = "stylesheet" href = "<c:url value = "/inc/form.css"/>" />
</head>
<body>
    <h1>Tests JDBC</h1>

    <c:forEach items = "${ messages }" var = "message" varStatus = "boucle">
        <p>${ boucle.count }. ${ message }</p>
    </c:forEach>
</body>
</html>
