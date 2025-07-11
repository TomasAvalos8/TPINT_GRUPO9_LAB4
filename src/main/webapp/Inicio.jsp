<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inicio de sesion</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        text-align: center;
        margin: 0;
        padding: 0;
    }
    
    form {
        display: inline-block;
        background: white;
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0px 0px 10px #ccc;
        width: 300px;
        margin-top: 10%;
    }
    
    h1 {
        margin-top: 0;
        color: #333;
    }
    
    label {
        display: block;
        text-align: left;
        margin-bottom: 5px;
        font-weight: bold;
    }
    
    input[type="text"], 
    input[type="password"] {
        padding: 10px;
        width: 100%;
        margin: 5px 0 15px 0;
        box-sizing: border-box;
        border: 1px solid #ddd;
        border-radius: 4px;
    }
    
    .btn-ingresar {
        width: 100%;
        padding: 12px;
        background-color: #4CAF50;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 1rem;
        margin-bottom: 10px;
        display: block;
    }
    
    .btn-ingresar:hover {
        background-color: #45a049;
    }
    
    .btn-recuperar {
        width: 100%;
        padding: 12px;
        background-color: #1e6f9f;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 1rem;
        display: block;
    }
    
    .btn-recuperar:hover {
        background-color: #1a5d85;
    }
    
    .error-message {
        color: red;
        margin-bottom: 15px;
    }
</style>
</head>
<body class="login">

<% if (request.getParameter("error") != null) { %>
    <p class="error-message">Usuario o contraseña incorrectos.</p>
<% } %>

<form action="loginServlet" method="post">
    <h1>Login</h1>
    <label for="usuario">Usuario</label>
    <input type="text" id="usuario" name="usuario" required>

    <label for="contraseña">Contraseña</label>
    <input type="password" id="pass" name="pass" required>

    <input type="submit" value="Ingresar" class="btn-ingresar">
    <button type="button" onclick="location.href='RecuperarPassword.jsp'" class="btn-recuperar">Recuperar contraseña</button>
</form>

</body>
</html>