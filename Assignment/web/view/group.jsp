<%-- 
    Document   : group
    Created on : Mar 18, 2024, 10:51:04 AM
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
            img{
                height: 120px;
                width: 100px;
            }
            .head{
                width: 100%;
            }
            .body{
                width: 100%;
            }
            th {
                border: 1px solid #dee2e6;
                text-transform: uppercase;
                height: 40px;
                background-color: #6b90da;
                font-weight: bold;
                color: white;
            }
            .body td {
                padding: 5px;
                text-align: center;
            }

        </style>
    </head>
    <body>
        <div class="container">
            <div class="row col-md-12">
                <%@include file="student/head.jsp" %>
                <h2>Select a course, then a group ...</h2>
                <table border="0" class="head">
                    <thead>
                        <tr>
                            <th>TERM</th>
                            <th>DEPARTMENT</th>
                            <th>COURSE</th>
                            <th>GROUP</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td valign="top">
                                <table class="semester">
                                    <c:forEach items="${requestScope.semesteres}" var="sem">
                                        <tr>
                                            <td>
                                                <c:if test="${sem.id eq requestScope.semid}">
                                                    <a href="?semid=${sem.id}">
                                                        <p style="font-weight: bold;color: black;">${sem.name}</p>
                                                    </a>
                                                </c:if>
                                                <c:if test="${sem.id ne requestScope.semid}">
                                                    <a href="?semid=${sem.id}">${sem.name}</a>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table> 
                            </td>
                            <td valign="top">
                                <table class="departments">
                                    <c:forEach items="${requestScope.departments}" var="d">
                                        <tr>
                                            <td>
                                                <c:if test="${d.id eq requestScope.did}">
                                                    <a href="?semid=${requestScope.semid}&did=${d.id}">
                                                        <p style="font-weight: bold;color: black;">${d.name}</p>
                                                    </a>
                                                </c:if>
                                                <c:if test="${d.id ne requestScope.did}">
                                                    <a href="?semid=${requestScope.semid}&did=${d.id}">
                                                        <p>${d.name}</p>
                                                    </a>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table> 
                            </td>
                            <td valign="top">
                                <table class="subject">
                                    <c:forEach items="${requestScope.subjects}" var="sub">
                                        <tr>
                                            <td>
                                                <c:if test="${sub.subid eq requestScope.subid}">
                                                    <a href="?semid=${requestScope.semid}&did=${requestScope.did}&subid=${sub.subid}">
                                                        <p style="font-weight: bold;color: black;">${sub.subname}</p>
                                                    </a>
                                                </c:if>
                                                <c:if test="${sub.subid ne requestScope.subid}">
                                                    <a href="?semid=${requestScope.semid}&did=${requestScope.did}&subid=${sub.subid}">
                                                        <p>${sub.subname}</p>
                                                    </a>
                                                </c:if>

                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table> 
                            </td>
                            <td valign="top">
                                <table class="subject">
                                    <c:forEach items="${requestScope.group}" var="g">
                                        <tr>
                                            <td>
                                                <c:if test="${g.gid eq requestScope.gid}">
                                                    <a href="?semid=${requestScope.semid}&did=${requestScope.did}&subid=${requestScope.subid}&gid=${g.gid}">
                                                        <p style="font-weight: bold;color: black;">${g.gname}</p>
                                                    </a>
                                                </c:if>
                                                <c:if test="${g.gid ne requestScope.gid}">
                                                    <a href="?semid=${requestScope.semid}&did=${requestScope.did}&subid=${requestScope.subid}&gid=${g.gid}">
                                                        <p>${g.gname}</p>
                                                    </a>
                                                </c:if>

                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table> 
                            </td>
                        </tr>
                    </tbody>
                </table>
                <h2>... then see student list </h2>
                <table summary="Select a group" border="1px" class="head">
                    <thead>
                        <tr>
                            <th>INDEX</th>
                            <th>IMAGE</th>
                            <th>CODE</th>
                            <th>NAME</th>
                            <th>GIVEN NAME</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="i" value="0"/>
                        <c:forEach items="${requestScope.students}" var="s">
                            <c:set var="i" value="${i+1}"/>
                            <tr>
                                <td>${i}</td>
                                <td><img src="image/${s.url}" alt="avatar ${s.sid}"/></td>
                                <td>${s.sid}</td>
                                <td><a href="student/infor?sid=${s.sid}">${s.sname}</td>
                                <td></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>   
            </div>
        </div>
        <div style="height: 250px">

        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </body>
</html>
