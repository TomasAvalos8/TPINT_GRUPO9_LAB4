<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inicio de sesion</title>
<link rel="stylesheet" type="text/css" href="estilos/estilos.css">
</head>
<body class="login">

<% if (request.getParameter("error") != null) { %>
    <p style="color:red;">Usuario o contraseña incorrectos.</p>
<% } %>

<form action="loginServlet" method="post" >
<h1>Login</h1>
<label for="usuario">Usuario</label><br>
<input type="text" id="usuario" name="usuario" required><br><br>

<label for="contraseña">Contraseña</label><br>
<input type="password" id="pass" name="pass"required><br><br>

<input type="submit" value="Ingresar">
</form>




</body>
</html>