<%@page import="com.emergentes.modelo.post"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
       post post = (post)request.getAttribute("lista");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>
            <c:if test="${item.id == 0}">Nuevo POST</c:if>
            <c:if test="${item.id != 0}">Realizar una edicion</c:if>
        </h1>
        <form action="MainController" method="post">
        <table>
            <input type="hidden" name="fecha" value="${item.id_usuario}"></td>
            <tr>
                <td>Fecha:</td>
                <td><input type="date" name="fecha" value="${item.fecha}"></td>
            </tr>
            <tr>
                <td>Titulo:</td>
                <td><input type="text" name="titulo" value="${item.titulo}"></td>
            </tr>
            <tr>
                <td>Descripcion:</td>
                <td><input type="text" name="descripcion" value="${item.descripcion}"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Editar"></td>
            </tr>
        </table>
    </form>
    </body>
</html>
