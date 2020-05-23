<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>LOGIN</h1>
        <form action="loginControlador" method="post">
            <table>
                <tr>
                    <td>UserName:</td>
                    <td><input type="text" name="usuario"><br></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password"><br></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="IniciarSession"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
