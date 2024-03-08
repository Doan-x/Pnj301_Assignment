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
        <h1>Hello World!</h1>
        <c:forEach items="${requestScope.subjects}" var="sub">
            <a href="?semid=${sub.semester.id}&subid=${sub.subid}">${sub.subname}</a><br/>
        </c:forEach>
        <div>
            <c:set var="grades" value="${requestScope.grades}"/>
            <c:if test="${(grades != null && grades.size()>0)}">
                <table border = "1px">
                    <tr>
                        <th>GRADE CATEGORY</th>
                        <th>GRADE ITEM</th>
                        <th>WEIGHT</th>
                        <th>VALUE</th>
                        <th>COMMENT</th>
                    </tr>    
                </table>
            </c:if>
        </div>
    </body>
</html>
