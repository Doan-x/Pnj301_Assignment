<%-- 
    Document   : infor
    Created on : Mar 17, 2024, 11:03:07 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

        <title>JSP Page</title>
        <style>
            a{
                text-decoration: none;
            }
            img{
                width: 200px;
                    height: 250px;
            }
            
        </style>
    </head>
    <body>
        <%@include file="head.jsp" %>
        <div class="container">
            <div class="row col-md-12">
                <table border="0">
                    <thead>
                        <tr>
                            <th><h1>${requestScope.student.sname}</h1></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr><td><img src="../image/${requestScope.student.url}" alt="alt"/></td></tr>
                        <tr>
                            <td><a href="timetable?sid=${requestScope.student.sid}">TimeTable</a> </td>
                        </tr>
                        <tr>
                            <td>
                                <form action="grade">
                                    <input type="hidden" value="${requestScope.student.sid}" name="sid">
                                    <input type="hidden" value="4" name="semid"><!-- comment -->
                                    <input type="submit" value="Grade">
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
