<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cambiar Contraseña</title>
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
        margin-top: 5%;
    }
    
    h1 {
        margin-top: 0;
        color: #333;
        margin-bottom: 20px;
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
    
    .btn-confirmar {
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
    
    .btn-confirmar:hover {
        background-color: #45a049;
    }
    
    .btn-volver {
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
    
    .btn-volver:hover {
        background-color: #1a5d85;
    }
    
    .error-message {
        color: red;
        margin-bottom: 15px;
        padding: 10px;
        background-color: #ffeeee;
        border-radius: 4px;
    }
    
    .success-message {
        color: green;
        margin-bottom: 15px;
        padding: 10px;
        background-color: #eeffee;
        border-radius: 4px;
    }
    
    .password-requirements {
        font-size: 0.8em;
        color: #666;
        text-align: left;
        margin-top: -10px;
        margin-bottom: 15px;
    }
    
    .usuario-actual {
        padding: 10px;
        background-color: #f0f0f0;
        border-radius: 4px;
        margin-bottom: 15px;
        font-weight: bold;
    }
</style>
</head>
<body class="recuperar">

<% 
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("Inicio.jsp");
        return;
    }
%>

<% if (request.getParameter("error") != null) { %>
    <div class="error-message"><%= request.getParameter("error") %></div>
<% } %>

<% if (request.getParameter("success") != null) { %>
    <div class="success-message"><%= request.getParameter("success") %></div>
<% } %>

<form action="RecuperarPasswordServlet" method="post">
    <input type="hidden" name="contexto" value="perfil">
    <h1>Cambiar Contraseña</h1>
    
    <div class="usuario-actual">
        Usuario: <%= usuario %>
    </div>
    <input type="hidden" name="usuario" value="<%= usuario %>">
    
    <label for="pass">Nueva Contraseña</label>
    <input type="password" id="pass" name="pass" required minlength="6">
    <div class="password-requirements">Mínimo 6 caracteres</div>
    
    <label for="confirmpass">Confirmar Contraseña</label>
    <input type="password" id="confirmpass" name="confirmpass" required>

    <input type="submit" value="Confirmar" class="btn-confirmar">
    <input type="button" value="Volver" onclick="location.href='DatosPersonales.jsp'" class="btn-volver">
</form>

</body>
</html>