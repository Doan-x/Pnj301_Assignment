<%-- 
    Document   : home
    Created on : Mar 2, 2024, 5:26:50 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello ${sessionScope.user.displayname}</h1>
        <a href="login">logout</a>,<br/>
        <a href="lecturer/timetable">time</a>
        <a href="student/grade?semid=4">time</a>

    </body>
</html>
