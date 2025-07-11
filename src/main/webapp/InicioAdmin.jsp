<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inicio Administrador</title>
<link rel="stylesheet" type="text/css" href="estilos/estilos.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f6f8;
        margin: 0;
        padding: 0;
    }

    .contenedor-bienvenida {
        max-width: 600px;
        margin: 80px auto;
        padding: 30px;
        text-align: center;
        background-color: #fff;
        border-radius: 12px;
        box-shadow: 0 4px 8px rgba(0,0,0,0.1);
    }

    .contenedor-bienvenida h2 {
        color: #2c3e50;
        margin-bottom: 10px;
    }

    .contenedor-bienvenida p {
        font-size: 18px;
        color: #555;
    }

    .contenedor-bienvenida strong {
        color: #2980b9;
    }

    .userLoguedText {
        font-size: 16px;
        color: #333;
        margin-top: 10px;
    }
</style>

<body>
<jsp:include page="MenuAdmin.html"></jsp:include>
<%
String nombreUsuario= (String)session.getAttribute("usuario");
String tipo= (String)session.getAttribute("tipoUsuario");
Integer tipoUsuarioId = (Integer) session.getAttribute("tipoUsuarioId");
if (tipoUsuarioId == null || tipoUsuarioId == 0) {
    response.sendRedirect("Inicio.jsp");
    return;
}
if (tipoUsuarioId != 1) {
    response.sendRedirect("ServletInicioCliente");
    return;
}
%>

<div class="contenedor-bienvenida">
<h1>¡Bienvenido, <%= nombreUsuario%> !</h1>
<p>Estas logueado como: <strong><%= tipo %></strong></p>

</div>


<jsp:include page="Footer.html"></jsp:include>
</body>
</html>