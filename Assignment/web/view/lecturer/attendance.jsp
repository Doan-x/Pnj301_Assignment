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
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="att" method="POST">
            <input type="hidden" name="id" value="${param.id}" />
            <table border="1px">
                <tr>
                    <td>Id</td>
                    <td>Name</td>
                    <td>Presented</td>
                    <td>Note</td>
                    <td>Time</td>
                </tr>
                <c:forEach items="${requestScope.atts}" var="a">
                    <tr>
                        <td>${a.student.sid}</td>
                        <td>${a.student.sname}</td>
                        <td>
                            <input type="radio" 
                                   ${!a.isPresent?"checked=\"checked\"":""}
                                   name="present${a.student.sid}" value="no"/> No
                            <input type="radio" 
                                   ${a.isPresent?"checked=\"checked\"":""}
                                   name="present${a.student.sid}" value="yes"/> Yes
                        </td>
                        <td>
                            <input type="text" name="description${a.student.sid}" value="${a.description}"/>
                        </td>
                        <td>${a.capturedtime}</td>
                    </tr>    
                </c:forEach>
            </table>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
