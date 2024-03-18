<%-- 
    Document   : grade
    Created on : Mar 8, 2024, 10:56:19 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <title>JSP Page</title>
        <style>
            body {
                font-family: 'Arial', sans-serif;
                background-color: #f5f5f5;
                padding: 20px;
                width: 100%;
                margin: auto;
            }

            table {
                width: 100%;
                margin-top: 20px;
                border-collapse: collapse;
            }

            .main th {
                padding: 5px;
                text-align: left;
                border-top: 1px solid #dee2e6;
            }
            .main td {
                padding: 5px;
                text-align: left;
                border-top: 1px solid #dee2e6;
            }

            th {
                background-color: #6b90da;
                color: #fff;
            }
            th,td{
                padding: 5px;
                text-align: left;
            }
            h3 {
                color: #6b90da;
            }
            tfoot {
                padding: 2px;
                text-transform: uppercase;
                font-size: 1.2em;
                font-weight: bold;
                margin-top: 6px;
                border-top: 6px solid #ddd;
            }

        </style>
    </head>
    <body>
        <div class="container">
            <div class="head">
                <%@include file="head.jsp" %>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <h2>Grade report for <span>${requestScope.student.sname}(${requestScope.student.sid})</span></h2>
                    <table >
                        <tbody>
                            <tr>                
                                <td valign="top">
                                    <h3>Select a term, course ...</h3>
                                    <table class="main">
                                        <thead>
                                            <tr>
                                                <th>Term</th>
                                                <th>Course</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td valign="top">
                                                    <div >
                                                        <table >
                                                            <tbody>
                                                                <c:forEach items="${requestScope.semesters}" var="sem">
                                                                    <tr>
                                                                        <td>
                                                                            <c:if test="${sem.id eq requestScope.semid}">
                                                                                <a href="?semid=${sem.id}&sid=${requestScope.student.sid}}">
                                                                                    <p style="font-weight: bold;color: black;">${sem.name}</p>
                                                                                </a>
                                                                            </c:if>
                                                                            <c:if test="${sem.id ne requestScope.semid}">
                                                                                <a href="?semid=${sem.id}&sid=${requestScope.student.sid}">${sem.name}</a>
                                                                            </c:if>
                                                                        </td>
                                                                    </tr>
                                                                </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </td>
                                                <td valign="top">
                                                    <div >
                                                        <table >
                                                            <tbody>
                                                                <c:forEach items="${requestScope.subjects}" var="sub">
                                                                    <tr>
                                                                        <td>
                                                                            <c:if test="${sub.subid eq requestScope.subid}">
                                                                                <a href="?semid=${sub.semester.id}&subid=${sub.subid}&sid=${requestScope.student.sid}">                                               
                                                                                    <p style="font-weight: bold;color: black;">${sub.subname}</p>
                                                                                </a>
                                                                            </c:if>
                                                                            <c:if test="${sub.subid ne requestScope.subid}">
                                                                                <a href="?semid=${sub.semester.id}&subid=${sub.subid}&sid=${requestScope.student.sid}">${sub.subname}</a>  
                                                                            </c:if>
                                                                        </td>
                                                                    </tr>
                                                                </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </td>

                                <c:set var="grades" value="${requestScope.ass}"/>
                                <c:if test="${(ass != null && ass.size()>0)}">
                                    <td valign="top">
                                        <table border = "1px">                                    
                                            <thead>
                                                <tr>
                                                    <th>Grade category</th>
                                                    <th>Grade item</th>
                                                    <th>Weight</th>
                                                    <th>Value</th>
                                                    <th>Comment</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${requestScope.ass}" var="a">
                                                    <tr>
                                                        <td rowspan="${a.grades.size()+2}">${a.atname}</td>
                                                    </tr>                                 
                                                    <c:forEach items="${a.grades}" var="g">
                                                        <tr>                                                    
                                                            <td>${g.exam.assessment.item}</td>
                                                            <td>${g.exam.assessment.weight}%</td>
                                                            <c:if test="${!(g.score eq -1)}">
                                                                <td>${g.score}</td>
                                                            </c:if>
                                                            <td></td>
                                                        </tr>
                                                    </c:forEach>

                                                    <tr><td>Total</td>

                                                        <td>${a.getWeightTotal()}%</td>
                                                        <c:if test="${a.getMarkTotal()  >= 0}">
                                                            <td>${a.getMarkTotal()}</td>
                                                        </c:if>
                                                        <td></td>
                                                    </tr>

                                                </c:forEach>
                                            </tbody>
                                            <tfoot>
                                                <tr>
                                                    <td rowspan="2">Course total</td>
                                                    <td>Average</td>
                                                    <c:if test="${requestScope.courseratotal.average >=0}">
                                                        <td colspan="3">${requestScope.courseratotal.average}</td>
                                                    </c:if>
                                                </tr>
                                                <tr>
                                                    <td>Status</td>
                                                    <td colspan="3">

                                                        <c:if test="${requestScope.courseratotal.status == 1}">
                                                            <font color="Green">Passed</font>
                                                        </c:if>
                                                        <c:if test="${requestScope.courseratotal.status == 0}">
                                                            <font  color ="Green">Studying</font>
                                                        </c:if>
                                                        <c:if test="${requestScope.courseratotal.status == -1}">
                                                            <font color="red">NotPassed</font>
                                                        </c:if>

                                                    </td>
                                                </tr>
                                            </tfoot>

                                        </table>
                                    </td>
                                </c:if>    
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </body>
</html>
