<%-- 
   Document   : login
   Created on : Mar 2, 2024, 5:20:16 AM
   Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <style>
            .container {
                width: 100%;
                margin: 0 auto;
                padding: 20px;
                display: flex; 
                justify-content: center;
                align-items: center;
                height: 100vh; 
            }
            table{
                width: 100%;
            }

            .row {
                width: 40%;
                text-align: center;
            }

            h1 {
                margin-bottom: 20px;
                color: #333;
            }

            h2 {
                color: red;
            }

            td {
                padding: 10px 0;
            }

            .user,
            .pass {
                width: 100%;
                padding: 10px;
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-sizing: border-box; /* Ensures padding is included in width */
            }

            button {
                width: 100%;
                padding: 10px;
                background-color: #007bff;
                color: #fff;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            button:hover {
                background-color: #0056b3;
            }

        </style>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <table border="0">
                    <tbody>
                        <tr>
                            <td><h1>Login Form</h1></td>
                        </tr>
                        <tr>
                            <td><h2 style="color: red">${requestScope.error}</h2></td>
                        </tr>
                    <form action="login" method="post" class="form">
                        <tr>
                            <td><input type="text" name="user" placeholder="Username:" class="user"/></td>
                        </tr>
                        <tr>
                            <td><input type="password" name="pass" placeholder="Password: " class="pass"/></td>
                        </tr>
                        <tr>
                            <td><button type="submit">Login</button></td>
                        </tr>
                    </form>
                    </tbody>
                </table>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </body>
</html>
