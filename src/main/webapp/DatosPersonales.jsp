<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Datos Personales</title>
<link rel="stylesheet" type="text/css" href="estilos/estilos.css">
<link rel="stylesheet" type="text/css" href="estilos/estilosCliente.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<style>
.ContenedorPersonal {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  margin: 20px auto;
  max-width: 1200px;
}

.ContenedorPersonal > div {
  width: 45%;
  margin: 15px;
  box-shadow: 0 0 5px rgba(0,0,0,0.1);
}


.ContenedorPersonal h3:first-child {
  background-color: #1a237e;
  color: white;
  padding: 10px;
  margin: 0;
  border-radius: 8px 8px 0 0;
  text-align: center;
}


.ContenedorPersonal h3:last-child {
  background-color: white;
  padding: 10px;
  margin: 0;
  font-weight: bold;
  border: 1px solid #ddd;
  border-top: none;
  border-radius: 0 0 8px 8px;
}

</style>

</head>

<%@ page import="Dominio.Cliente" %>
<%
    Cliente cliente = (Cliente) session.getAttribute("clienteLogueado");
    if (cliente == null) {
        response.sendRedirect("Inicio.jsp");
        return;
    }
%>



<body>
<jsp:include page="MenuCliente.html"></jsp:include>
<% String usuario = (String)session.getAttribute("usuario"); %>
<p class="userLoguedText">
  <i class="fas fa-user"></i> <%= usuario %>
</p>
<h2 class="Titulo">Datos personal</h2>



<div class="ContenedorPersonal">
    <div class="Nombre">
        <h3>Nombre</h3>
        <h3><%= cliente.getNombre() %></h3>
    </div>
    <div class="Apellido">
        <h3>Apellido</h3>
        <h3><%= cliente.getApellido() %></h3>
    </div>
    <div class="Gmail">
        <h3>Correo electrónico</h3>
        <h3><%= cliente.getCorreo_electronico() %></h3>
    </div>
    <div class="Telefono">
        <h3>Teléfono</h3>
        <h3><%= cliente.getTelefono() %></h3>
    </div>
    <div class="Calle">
        <h3>Dirección</h3>
        <h3><%= cliente.getDireccion() %></h3>
    </div>
    <div class="localidad">
        <h3>Localidad</h3>
        <h3><%= cliente.getLocalidad().getDescripcion() %></h3>
    </div>
    <div class="Provincia">
        <h3>Provincia</h3>
        <h3><%= cliente.getProvincia().getDescripcion() %></h3>
    </div>
    <div class="Sexo">
        <h3>Sexo</h3>
        <h3><%= cliente.getSexo() %></h3>
    </div>
    <div class="Usuario">
        <h3>Usuario</h3>
        <h3><%= cliente.getUsuario().getUsuario() %></h3>
    </div>
</div>



<div class="BotonRestablecer" style="width: 100%; text-align: center;">
    <button class="boton-restablecer">Restablecer nueva contraseña</button>
</div>




  
    
</body>
<footer>
	<jsp:include page="Footer.html"></jsp:include>
</footer>

</html>