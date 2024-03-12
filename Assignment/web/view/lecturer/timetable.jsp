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
        <title>JSP Page</title>
        <style>
            body {
                font-family: 'Arial', sans-serif;
                background-color: #f8f9fa;
                margin: 20px;
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
                margin-top: 20px;
            }
            td {
                border: 1px solid #dee2e6;
                text-align: center;
            }
            td p {
                margin: 2px 0;
                font-size: 14px;
                line-height: 1.4;
                text-transform: uppercase;
            }
            td a {
                display: block;
                padding: 5px 10px;
                background-color: #007bff;
                color: white;
                text-decoration: none;
                border-radius: 5px;
            }
            td a:hover {
                background-color: #0056b3;
            }
            td:hover {
                background-color: #e0e0e0; 
                transition: background-color 0.3s; 
            }
        </style>

    </head>
    <body>
        <h1>Activities for ${requestScope.lecturer.lid} (${requestScope.lecturer.lname})</h1>
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
            Lecturer ID:<input type="text" name="lid" value="${requestScope.lecturer.lid}">
            From: <input type="date" name="from" value="${requestScope.from}">
            To: <input type="date" name="to" value="${requestScope.to}"><br/>
            <input type="submit" value="Save">
        </form>

        <div>
            <table border="1px" style="width:100%" align="center">
                <thead> 
                    <tr>
                        <td></td>
                        <c:forEach items="${requestScope.slots}" var="sl">
                            <th>${sl.tname}</th>
                            </c:forEach>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.dates}" var="d">
                        <tr>                                                          
                            <td rowspan="1">
                                <p><fmt:formatDate pattern="E" value="${d}"/><br><!-- comment --></p>
                            </td>

                            <c:forEach items="${requestScope.slots}" var="slt">
                                <td rowspan="2">
                                    <c:forEach items="${requestScope.lession}" var="les">
                                        <c:if test="${(les.slot.tid eq slt.tid) and (les.date eq d)}">
                                            <p>${les.group.subject.subname} -</p> 
                                            <p>at ${les.group.gname}</p>
                                            <p>${les.attended}</p> 
                                            <a href="att?id=${les.id}">
                                                <c:if test="${les.attended}">Edit</c:if>
                                                <c:if test="${!les.attended}">Take</c:if>
                                                </a>
                                        </c:if>
                                    </c:forEach>
                                </td>
                            </c:forEach>

                        </tr>
                        <tr>
                            <td rowspan="1">
                                <p>${d}</p>
                            </td>
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
    </body>
</html>
