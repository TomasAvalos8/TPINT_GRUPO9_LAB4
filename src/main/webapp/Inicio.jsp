<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inicio de sesion</title>
<link rel="stylesheet" type="text/css" href="estilos/estilos.css">
</head>
<body>


<h1>Login</h1>
<form action="loginServlet" method="post">
<label for="usuario">Usuario</label><br>
<input type="text" id="usuario" name="usuario" required><br><br>

<label for="contraseña">Contraseña</label><br>
<input type="text" id="contraseña" name="contraseña"required><br><br>

<input type="submit" value="ingresar">
</form>




</body>
</html>