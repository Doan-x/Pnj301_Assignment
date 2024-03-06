<%-- 
    Document   : login
    Created on : Mar 2, 2024, 5:20:16 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    </head>
    <body>
        <h1>Login Form</h1>
        <h2 style="color: red">${requestScope.error}</h2>
        <form action="login" method="post">
            Username: <input type="text" name="user"><br/>
            Password: <input type="password" name="pass"><br/>
            <input type="submit" value="Login"><br/>
        </form>
    </body>
</html>
