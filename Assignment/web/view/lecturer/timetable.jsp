<%-- 
    Document   : timetable
    Created on : Mar 3, 2024, 7:45:26 AM
    Author     : Admin
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            table {
                width: 100%;
                margin-top: 20px;
            }
            td {
                border: 1px solid #dee2e6;
                text-align: left;
            }
            td p {
                margin: 2px 0 2px 10px;
                font-size: 14px;
                line-height: 1.4;
            }
            td:hover {
                background-color: #e0e0e0;
                transition: background-color 0.3s;
            }
            th:hover, td:hover {
                background-color: #f5f5f5;
            }

        </style>

    </head>
    <body> 
        <h1>${requestScope.today}</h1>
        <div class="container">
            <div class="head">
                <%@include file="head.jsp" %>
            </div>
            <div class="row">
                <div class="col-md-12" >

                    <c:if test="${requestScope.lecturer.lid != null}">
                        <h1>Activities for ${requestScope.lecturer.lid} (${requestScope.lecturer.lname})</h1> 
                    </c:if>
                    <c:if test="${requestScope.student.sid != null}">
                        <h1>Activities for ${requestScope.student.sid} (${requestScope.student.sname})</h1> 
                    </c:if>

                    <div>
                        <strong>Note:</strong> These activities do not include extra-curriculum activities, such as club activities ...
                        <strong>Chú thích:</strong> Các hoạt động trong bảng dưới không bao gồm hoạt động ngoại khóa, ví dụ như hoạt động câu lạc bộ ...</br> 
                        Các phòng bắt đầu bằng AL thuộc tòa nhà Alpha. VD: AL...</br>
                        Các phòng bắt đầu bằng BE thuộc tòa nhà Beta. VD: BE,..</br>
                        Các phòng bắt đầu bằng G thuộc tòa nhà Gamma. VD: G201,...</br>
                        Các phòng tập bằng đầu bằng R thuộc khu vực sân tập Vovinam.</br>
                        Các phòng bắt đầu bằng DE thuộc tòa nhà Delta. VD: DE,..</br>
                        Little UK (LUK) thuộc tầng 5 tòa nhà Delta
                    </div></br>

                    <form action="timetable" method="get">
                        <c:forEach items="${sessionScope.roles}" var="role">
                            <c:if test="${role.id != 2 && requestScope.student.sid == null}">
                                Lecturer ID:<input type="text" name="lid" value="${requestScope.lecturer.lid}">    
                            </c:if>
                        </c:forEach>
                        <c:if test="${requestScope.student.sid != null}">
                            <input type="hidden" name="sid" value="${param.sid}"/>
                        </c:if>
                        From: <input type="date" name="from" value="${requestScope.from}">
                        To: <input type="date" name="to" value="${requestScope.to}"> 
                        <input type="submit" value="View" style="width: 55px">
                    </form>

                    <div >
                        <table border="1px" class="table-container">
                            <thead> 
                            <td rowspan="3"></td>
                            <tr>                                    
                                <c:forEach items="${requestScope.dates}" var="d">
                                    <th><fmt:formatDate pattern="E" value="${d}"/></th>
                                    </c:forEach>
                            </tr>
                            <tr>
                                <c:forEach items="${requestScope.dates}" var="date">
                                    <th>${date}</th>
                                    </c:forEach>
                            </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.slots}" var="sl">
                                    <tr>
                                        <td>${sl.tname}</td>
                                        <c:forEach items="${requestScope.dates}" var="date">
                                            <td>
                                                <c:forEach items="${requestScope.lession}" var="les">
                                                    <c:if test="${(les.slot.tid eq sl.tid) and (les.date eq date)}">
                                                        <p>
                                                            <a href="#">${les.group.subject.subname}-</a>
                                                            <a href="#" target="_blank" style="background-color: #f0ad4e"><span>View Materials</span></a>
                                                            <br> at ${les.group.gname}
                                                            <c:forEach items="${sessionScope.roles}" var="role">
                                                                <c:if test="${!requestScope.current.after(les.date)}">                                                                    
                                                                    <c:if test="${role.id == 1 && les.lecturer.lid == sessionScope.lid}">                                                                  
                                                                        <a href="../lecturer/att?id=${les.id}">
                                                                            <c:if test="${les.attended}">
                                                                                <br>(<font color="Green">Attended</font>) Edit
                                                                            </c:if>
                                                                            <c:if test="${!les.attended}">
                                                                                <br>(<font color="red">Not yet</font>) Take
                                                                            </c:if>
                                                                        </a>
                                                                    </c:if>
                                                                    <c:if test="${role.id == 2 || les.lecturer.lid != sessionScope.lid}">                                                                  
                                                                        <a href="#">
                                                                            <c:if test="${les.attended}">
                                                                                <br>(<font color="Green">attended</font>)
                                                                            </c:if>
                                                                            <c:if test="${!les.attended}">
                                                                                <br>(<font color="red">Not yet</font>)
                                                                            </c:if>
                                                                        </a>
                                                                    </c:if>                                                  
                                                                </c:if>
                                                                <c:if test="${requestScope.current.after(les.date)}">                                                                    
                                                                    <c:if test="${role.id == 1 && les.lecturer.lid == sessionScope.lid}">                                                                  
                                                                        <a href="../lecturer/att?id=${les.id}">
                                                                            <c:if test="${les.attended}">
                                                                                <br>(<font color="Green">attended</font>) Edit
                                                                            </c:if>
                                                                            <c:if test="${!les.attended}">
                                                                                <br>(<font color="red">absent</font>) Take
                                                                            </c:if>
                                                                        </a>
                                                                    </c:if>
                                                                    <c:if test="${role.id == 2 || les.lecturer.lid != sessionScope.lid}">                                                                  
                                                                        <a href="#">
                                                                            <c:if test="${les.attended}">
                                                                                <br>(<font color="Green">attended</font>)
                                                                            </c:if>
                                                                            <c:if test="${!les.attended}">
                                                                                <br>(<font color="red">Not yet</font>)
                                                                            </c:if>
                                                                        </a>
                                                                    </c:if>                                                
                                                                </c:if>                           
                                                            </c:forEach>
                                                            <br>
                                                            <span>${les.slot.time}</span><br><!-- comment -->
                                                        </p>

                                                    </c:if>

                                                </c:forEach>

                                            </td>
                                        </c:forEach>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <p>
                            <b>More note / Chú thích thêm</b>:
                        </p>
                        <div>
                            <ul>
                                <li>(<font color="green">attended</font>): ${requestScope.lecturer.lid}  had attended this activity / ${requestScope.lecturer.lname} đã tham gia hoạt động này</li>
                                <li>(<font color="red">absent</font>): ${requestScope.lecturer.lid}  had NOT attended this activity / ${requestScope.lecturer.lname}  đã vắng mặt buổi này</li> 
                                <li>(-): no data was given / chưa có dữ liệu</li> 
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </body>
</html>
