<%-- 
    Document   : timetable
    Created on : Mar 6, 2024, 12:42:02 AM
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
            th {
                border-right: 1px solid #fff;
                text-transform: uppercase;
                height: 23px;
                background-color: #6b90da;
                font-weight: normal;
            }
            p {
                margin: 0 0 10px;
                text-align: left;
            }
        </style>

    </head>
    <body>
        <h1>Activities for ${requestScope.student.sid} (${requestScope.student.sname})</h1>
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
            From: <input type="date" name="from" value="${requestScope.from}">
            To: <input type="date" name="to" value="${requestScope.to}"><br/>
            <input type="submit" value="Save">
        </form>
        <div>
            <table border="1px" style="width:100%" align="center">
                <thead> 
                    <tr>
                        <td rowspan="2"></td>
                        <c:forEach items="${requestScope.dates}" var="d">
                            <th><fmt:formatDate pattern="E" value="${d}"/></th>
                            </c:forEach>
                    </tr>
                    <tr><c:forEach items="${requestScope.dates}" var="date">
                            <td>${date}</td>
                        </c:forEach>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.slots}" var="sl">
                        <tr>
                            <td>${sl.tname}</td>
                            <c:forEach items="${requestScope.dates}" var="d">
                                <td>
                                    <c:forEach items="${requestScope.lession}" var="les">
                                        <c:if test="${(les.slot.tid eq sl.tid) and (les.date eq d)}">
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
                    </c:forEach>
                </tbody>
            </table>
            <p>
                <b>More note / Chú thích thêm</b>:
            </p>
            <div id="ctl00_mainContent_divfoot">
                <ul>
                    <li>(<font color="green">attended</font>): ${requestScope.student.sid} had attended this activity / ${requestScope.student.sname} đã tham gia hoạt động này</li>
                    <li>(<font color="red">absent</font>): ${requestScope.student.sid} had NOT attended this activity / ${requestScope.student.sname}đã vắng mặt buổi này</li> 
                    <li>(-): no data was given / chưa có dữ liệu</li> 
                </ul>
            </div>
        </div>
    </body>
</html>
