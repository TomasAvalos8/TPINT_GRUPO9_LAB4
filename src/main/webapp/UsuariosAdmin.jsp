<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manejo de usuarios</title>
<link rel="stylesheet" type="text/css" href="css/StyleSheet.css">
<link rel="stylesheet" type="text/css" href="estilos/estilos.css">
</head>
<body>
<jsp:include page="MenuAdmin.html"></jsp:include>

<% String usuario = (String)session.getAttribute("usuario"); %>
<p class="userLoguedText">
  <i class="fas fa-user"></i> <%= usuario != null ? usuario : "Usuario logueado" %>
</p>
    <form class="formCliente" method="post" action="ServletUsuario">
<div class="contenedorFormularios">

<div class="formulariosWrapper">

<div class="parteDer">
    <h2>Crear nuevo usuario Administrador</h2>

        <fieldset>
         <p>    
             Usuario: <input type="text" name="usuario" required>
             Contraseña: <input type="password" name="password" required>
         </p>
         

        </fieldset>
</div>


</div>
<%
String mensaje = (String) request.getAttribute("mensaje");
if (mensaje != null && !mensaje.isEmpty()) {
%>
<div style="text-align: center; margin: 10px 0;">
    <p style="color: <%= mensaje.startsWith("Error") ? "red" : "green" %>;">
        <%= mensaje %>
    </p>
</div>
<%
}
%>
<div class="botonContainer">
  <button name="btnRegistrarUsuario" class="btnRegistrar">Registrar</button>
</div>
</div>
    </form>
<div class="formulariosWrapper listadoContainer">
<h2>Listado de Usuarios</h2>
<div class="tablaCuentasContainer">
<div class="filtrosContainer">
 <p> <b>Busqueda:</b>  Nombre de usuario: <input type="text" name="search">  
 <input type="submit" value="Buscar" class="btnBuscar">
 </p>

</div>

	<table>
        <thead>
            <tr>
                <th>Nombre de usuario</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody >
             <tr>
                 <td>usuario123</td>
                 <td><button class="btnEliminar">Eliminar</button></td>
             </tr>
             <tr>
                 <td>usuario3</td>
                 <td><button class="btnEliminar">Eliminar</button></td>
             </tr>
         
        </tbody>
          <tfoot>
            <tr>
                <td colspan="5">
                    <div class="paginado">
                    <button class="btnAnterior"><</button>
                        <span> Pagina 1 de 10 </span>
                        <button class="btnSiguiente">></button>
                    </div>
                </td>
            </tr>
          </tfoot>
        </table>
        </div>
</div>










</body>
</html>