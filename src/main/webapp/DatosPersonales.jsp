<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Datos Personales</title>
<link rel="stylesheet" type="text/css" href="estilos/estilos.css">
<link rel="stylesheet" type="text/css" href="estilos/estilosCliente.css">
<style>
ul {
  height: 60px;
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: #333;
  text-align: center;
  
}

li {
  float: left;
}

li a {
  display: block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

li a:hover:not(.active) {
  background-color: #333;
}

.active {
  background-color: #04AA6D;
  
}

.ContenedorPersonal {
  display: flex;
  flex-wrap: wrap;
  width: 100%;
  margin: 20px 0;
}

/* Estilos para todas las celdas */
.Nombre, .Apellido, .Gmail, .Telefono , .Calle, .localidad, .Usuario{
  width: 50%;
  padding: 15px 50px 15px 200px;
  box-sizing: border-box;
}
.Apellido, .Telefono , .localidad, .Contraseña{
  width: 50%;
  padding: 15px 200px 15px 50px;
  box-sizing: border-box;
}

.Nombre, .Apellido{
	padding-top: 50px;
}

/* Estilos para los títulos */
.Nombre h3:first-child, .Apellido h3:first-child, 
.Gmail h3:first-child, .Telefono h3:first-child,  
.Calle h3:first-child, .localidad h3:first-child,
.Usuario h3:first-child, .Contraseña h3:first-child{
  background-color: #1a237e;
  color: white;
  padding: 10px;
  border-radius: 5px 5px 0 0;
  margin: 0;
  text-align: center;
}

/* Estilos para los valores */
.Nombre h3:last-child, .Apellido h3:last-child, 
.Gmail h3:last-child, .Telefono h3:last-child,  
.Calle h3:last-child, .localidad h3:last-child,
.Usuario h3:last-child, .Contraseña h3:last-child {
  background-color: white;
  color: black;
  padding: 10px;
  border: 1px solid #ddd;
  border-top: none;
  border-radius: 0 0 5px 5px;
  margin: 0;
  text-align: left;
  font-weight: bold;
}


.boton-restablecer {
  display: inline-block;
  padding: 12px 25px;
  background-color: #1a237e;
  color: white;
  text-align: center;
  text-decoration: none;
  border-radius: 8px;
  font-weight: bold;
  font-size: 16px;
  border: none;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin: 90px 100px 30px 0; 
  float: right; /* Alineación a la derecha */
}

.boton-restablecer:hover {
  background-color: #666;
}


.clearfix::after {
  content: "";
  clear: both;
  display: table;
}

.Titulo {
  font-size: 2.5rem;  /* Tamaño razonable */
  text-align: center;
  color: #1a237e;    
  margin: 30px 0;
  font-weight: bold;
  padding-top: 30px;
}

</style>

</head>




<body>
<nav>
    <ul>
        <li><a href="#">Historial de Movimientos</a></li>
        <li><a href="#">Transferencias</a></li>
        <li><a href="#">Solicitud de Préstamo</a></li>
        <li><a href="#">Mis Préstamos</a></li>
        <li><a href="DatosPersonales.jsp">Información Personal</a></li>
         <li style="float:right" >
  			<ul>
  				<li ><a style="background-color:#ec0000 " class="active" href="Inicio.jsp">Cerrar Sesion</a></li>
  			</ul>
  		</li>
    </ul>
</nav>

<h2 class="Titulo">Datos personal</h2>

<div class="ContenedorPersonal">
    <div class="Nombre">
        <h3>Nombre</h3>
        <h3>Federico</h3>
    </div>
    <div class="Apellido">
        <h3>Apellido</h3>
        <h3>Escobar</h3>
    </div>
    <div class="Gmail">
        <h3>Gmail</h3>
        <h3>Federico@gmail.com</h3>
    </div>
    <div class="Telefono">
        <h3>Telefono</h3>
        <h3>113908930</h3>
    </div>
    <div class="Calle">
        <h3>Calle</h3>
        <h3>Calle falsa 123</h3>
    </div>
    <div class="localidad">
        <h3>localidad</h3>
        <h3>tigre</h3>
    </div>
    <div class="Usuario">
        <h3>Usuario</h3>
        <h3>Frederick</h3>
    </div>
    <div class="Contraseña">
        <h3>Contraseña</h3>
        <h3>123456</h3>
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