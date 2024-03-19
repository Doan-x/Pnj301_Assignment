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
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

        <style>
            body {
                background-color: #f8f9fa;
                margin: 10px;
            }
            a{
                text-decoration: none;
            }
            .student:hover {
                background-color: #e0e0e0;
                transition: background-color 0.3s;
            }
        </style>
    </head>
    <body>
        <%@include file="student/head.jsp" %>
        <div class="container">
            <div class="row col-md-12">
                <table border="0">
                    <tbody>
                        <tr>
                            <td><h1>Hello ${sessionScope.account.displayname}</h1></td>
                        </tr>
                        <c:forEach items="${sessionScope.roles}" var="r">
                            <c:if test="${r.id == 1}">
                                <tr>
                                    <td><a href="lecturer/timetable">Time Table</a></td>
                                </tr>
                                <tr>
                                    <td></td>
                                </tr>
                            </c:if>
                            <c:if test="${r.id == 2}">
                                <tr>
                                    <td><a href="student/timetable">Time Table</a></td>
                                </tr>
                                <tr>
                                    <td><a href="student/grade?semid=4">Mark report</a></td>
                                </tr>
                            </c:if>
                        </c:forEach>
                        <tr>
                            <td><a href="group?semid=4">Group Student</a></td>
                        </tr>
                        <tr>
                            <td>
                                <form action="home" method="post">
                                    <table border="0">
                                        <tbody>
                                            <tr>
                                                <td>
                                                    <input type="text" name="search" placeholder="Search Student">

                                                </td>
                                                <td><input type="submit" value="seach"></td>

                                            </tr>
                                            <c:forEach items="${requestScope.students}" var="s">
                                                <tr>
                                                    <td class="student">
                                                        <a href="student/infor?sid=${s.sid}">
                                                            <div>${s.sid} - ${s.sname}</div></a>
                                                    </td>

                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </form>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    </body>
</html>
