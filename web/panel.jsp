<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.post"%>
<%
    if(session.getAttribute("logueado") != "OK"){
        response.sendRedirect("login.jsp");
    }
%>
<%
    List<post> lista = (List<post>) request.getAttribute("lista");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>PANEL DE ADMINISTARCION</h1>
        <p>Usuario: ${sessionScope.usuario}</p>
        <p>BIENVENIDO AL PANEL DE ADMINISTARCION</p>
        <br>
        <a href="loginControlador?action=logout">SALIR [x]</a>
        <br><br><br>
        
        <h1>LISTADO de POSTS</h1>
        <p>
            <a href="MainController?op=nuevo">Nuevo POST</a>
        </p>
        <table border="1">
        <tr>
            <th>ID</th>
            <th>TITULO</th>
            <th>DESCRIPCION</th>
            <th>FECHA</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach var="item" items="${lista}">
        <tr>
            <td>${item.id_usuario}</td>
            <td>${item.titulo}</td>
            <td>${item.descripcion}</td>
            <td>${item.fecha}</td>
            <td><a href="MainController?op=editar&id=${item.id_usuario}">Editar</a></td>
            <td><a href="MainController?op=eliminar&id=${item.id_usuario}" onclick="return(confirm('Esta seguro de eliminar ?'))">Eliminar</a></td>
        </tr>
        </c:forEach>
    </table>
        
        
    </body>
</html>
