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
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <h1><span>FPT University Academic Portal</span></h1>                    
                </div>
                <div class="col-md-4">
                    <table>
                        <tbody><tr>
                                <td colspan="2" class="auto-style1">
                                    <strong>FAP mobile app (myFAP) is ready at</strong></td>
                            </tr>
                            <tr>
                                <td><a href="https://apps.apple.com/app/id1527723314">
                                        <img src="https://fap.fpt.edu.vn/images/app-store.png" 
                                             style="width: 120px; height: 40px" alt="apple store"></a></td>
                                <td><a href="https://play.google.com/store/apps/details?id=com.fuct">
                                        <img src="https://fap.fpt.edu.vn/images/play-store.png" 
                                             style="width: 120px; height: 40px" alt="google store"></a></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <h2>Grade report for <span>Nguyễn Doãn Trọng (HE172412)</span></h2>
            <table>
                <tbody>
                    <tr>
                        <td valign="top">
                            <h3>Select a term, course ...</h3>
                            <table>
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
                                                <table>
                                                    <tbody>
                                                        <c:forEach items="${requestScope.subjects}" var="sub">
                                                            <tr>
                                                                <td>
                                                                    <a href="?semid=${sub.semester.id}&subid=${sub.subid}">${sub.subname}</a>  
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </td>
                                        <td valign="top">
                                            <div >
                                                <table>
                                                    <tbody>
                                                        <c:forEach items="${requestScope.subjects}" var="sub">
                                                            <tr>
                                                                <td>
                                                                    <a href="?semid=${sub.semester.id}&subid=${sub.subid}">${sub.subname}</a>  
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
                                    <tr><td>Grade category</td></tr>
                                    <c:forEach items="${requestScope.ass}" var="a">
                                        <tr>
                                            <td>${a.atname}</td>
                                        </tr>
                                        <tr><td></td></tr>
                                    </c:forEach>
                                </table>
                            </td>
                            <td valign="top">
                                <table border = "1px">
                                    <thead>
                                        <tr>
                                            <th>Grade item</th>
                                            <th>Weight</th>
                                            <th>Value</th>
                                            <th>Comment</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.ass}" var="a">
                                                                                    
                                                <c:forEach items="${a.grades}" var="g">
                                                    <tr>
                                                    <td>${g.exam.assessment.item}</td>
                                                    <td>${g.exam.assessment.weight}%</td>
                                                    <td>${g.score}</td>
                                                    <td></td>
                                                    </tr>
                                                </c:forEach>
                                            
                                            <tr><td>Total</td>
                                                <td>${(g.exam.assessment.weight*a.grades.size())} %</td>
                                                <td></td>
                                                <td></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </td>
                        </c:if>    

                    </tr>
                </tbody>
            </table>

        </div>
    </body>
</html>
