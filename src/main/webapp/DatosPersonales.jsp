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
body {
  background-color: #ffffff;
  margin: 0;
  padding: 0;
  color: #333;
}

.Titulo {
  text-align: center;
  font-size: 2rem;
  margin-top: 40px;
  margin-bottom: 20px;
  color: #000000;
}

.userLoguedText {
  text-align: right;
  padding: 15px 30px;
  font-weight: 600;
  font-size: 1rem;
}

.ContenedorPersonal {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  max-width: 1200px;
  margin: 0 auto;
  gap: 20px;
  padding: 20px;
}

.ContenedorPersonal > div {
  background-color: #ffffff;
  border-radius: 16px;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.07);
  width: 280px;
  transition: transform 0.2s ease;
}

.ContenedorPersonal > div:hover {
  transform: translateY(-5px);
}

.ContenedorPersonal h3:first-child {
  background-color: #3949ab;
  color: white;
  padding: 12px;
  border-radius: 16px 16px 0 0;
  margin: 0;
  text-align: center;
  font-size: 1rem;
}

.ContenedorPersonal h3:last-child {
  padding: 16px;
  margin: 0;
  font-weight: 600;
  font-size: 1.1rem;
  text-align: center;
  color: #2c3e50;
  border-radius: 0 0 16px 16px;
  background-color: #f9f9f9;
}

.BotonRestablecer {
  width: 100%;
  text-align: center;
  margin: 30px 0;
}

.boton-restablecer {
  background-color: #3949ab;
  color: white;
  padding: 14px 40px;
  border: none;
  border-radius: 30px;
  font-size: 1rem;
  font-weight: 600;
  cursor: pointer;
  box-shadow: 0 4px 10px rgba(57, 73, 171, 0.3);
  transition: background-color 0.3s ease;
}

.boton-restablecer:hover {
  background-color: #0f24ad;
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
    <button class="boton-restablecer" onclick="location.href='RecuperarPasswordCliente.jsp'">Restablecer nueva contraseña</button>
</div>




  
    
</body>
<footer>
	<jsp:include page="Footer.html"></jsp:include>
</footer>

</html>