<%@ page import="com.example.demo.controllers.MainController" %><%--
  Created by IntelliJ IDEA.
  User: doand
  Date: 15/11/2020
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
    Home
    <form id="myform" action="download" method="Post">
        <input name="authozToken" type="hidden" value="<%=MainController.authozToken%>">
        <button type="submit">CLICK TO DOWN</button>
    </form>

</body>
</html>
