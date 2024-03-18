<%-- 
    Document   : atts
    Created on : Mar 3, 2024, 11:56:30 PM
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
            .att{
                width: 100%;
            }
            form{
                width: 100%;
            }
            body {
                background-color: #f8f9fa;
                margin: 10px;
            }
            th {
                border: 1px solid #dee2e6;
                text-transform: uppercase;
                height: 40px;
                background-color: #6b90da;
                font-weight: bold;
                color: white;
            }
            td {
                margin: 2px;
                text-align: left;
            }
            .input{
                display: flex;
                flex-wrap: wrap;
                justify-content: space-between
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="row col-md-12">
                <%@include file="head.jsp" %>
                <h1>Hello ${sessionScope.account.displayname}</h1>
                <div class="row col-md-12">
                    <form action="att" method="POST">
                        <input type="hidden" name="id" value="${param.id}" />
                        <table border="0" class="att">
                            <thead>
                            <th>INDEX</th>
                            <th>GROUP</th>
                            <th>CODE</th>
                            <th>NAME</th>
                            <th>IMAGE</th>
                            <th>PRESENT</th>
                            <th>COMMENT</th>
                            <th>TAKER</th>
                            <th>RECORD TIME</th>

                            </thead>
                            <tbody>
                                <c:set var="i" value="0"/>
                                <c:forEach items="${requestScope.atts}" var="a">
                                    <c:set var="i" value="${i+1}"/>
                                    <tr>
                                        <td>${i}</td>
                                        <td>${a.lession.group.gname}</td>
                                        <td>${a.student.sid}</td>
                                        <td>${a.student.sname}</td>
                                        <td><img src="${pageContext.request.contextPath}/../image/1.jpeg" alt="alt"/>
                                        </td>
                                        <td class="input">
                                            <input type="radio" 
                                                   ${!a.isPresent?"checked=\"checked\"":""}
                                                   name="present${a.student.sid}" value="false" /> 
                                            <p style="color: red">Absent</p><!-- comment -->
                                            <input type="radio" 
                                                   ${a.isPresent?"checked=\"checked\"":""}
                                                   name="present${a.student.sid}" value="true"/>
                                            <p style="color: green">Present</p>
                                        </td>
                                        <td>
                                            <input type="text" name="description${a.student.sid}" value="${a.description}"/>
                                        </td>
                                        <td>${a.lession.lecturer.lname}</td>
                                        <td>${a.capturedtime}</td>
                                    </tr>    
                                </c:forEach>
                            </tbody>
                        </table>
                        <input type="submit" value="Save"/>
                    </form>
                                ${pageContext.request.contextPath}
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    </body>
</html>
