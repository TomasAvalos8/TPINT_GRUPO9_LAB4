<%@page import="Dominio.Provincia"%>
<%@page import="Dominio.Localidad"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/StyleSheet.css">
<link rel="stylesheet" type="text/css" href="estilos/estilos.css">
</head>
<script type="text/javascript">

function eventoSeleccionarProvincia() {
		var x = document.getElementById("idProvincia").value;
		 window.location.replace("ServletClientes?idProvincia="+x);
	  }
	  
</script>
<body>
<jsp:include page="MenuAdmin.html"></jsp:include>

<div class="contenedorFormularios">

    <form class="formCliente" method="post" action="ServletClientes">
<div class="formulariosWrapper">

<div class="parteDer">
    <h2>Formulario de Cliente</h2>

        <fieldset>
            <p>
            <%
            String idProvinciaSeleccionada = (String) request.getAttribute("idProvinciaSeleccionada");
            %>
                Provincia:
               <select name="idProvincia" id="idProvincia" onchange="eventoSeleccionarProvincia()" required>
               <option value="">Seleccione</option>
               <%
               List<Provincia> lista = (List<Provincia>) request.getAttribute("provincias");
               for (Provincia p : lista) {
            	   String seleccionado = (idProvinciaSeleccionada != null && idProvinciaSeleccionada.equals(String.valueOf(p.getId_provincia()))) ? "selected" : "";
            	   %>
            	   <option value="<%= p.getId_provincia() %>" <%= seleccionado %>><%= p.getDescripcion() %></option>
            	   <%
            	   }
            	   %>
            	   </select>
                Localidad:
                <select name="idLocalidad" required>
                    <option value="">Seleccione</option>
                    <% List<Localidad> listaLocalidades = (List<Localidad>) request.getAttribute("localidades");
                    if (listaLocalidades != null && !listaLocalidades.isEmpty()) {
                    	for (Localidad l : listaLocalidades) {
                    	%>
                    	<option value="<%= l.getId_localidad() %>"><%= l.getDescripcion() %></option>
                    	<%
                    	}
                    	}
                    	%>
                </select>
            </p>
            <p>
                DNI: <input type="number" name="dni" required>
                CUIL: <input type="number" name="cuil" required>
            </p>
            <p>
                Nombre: <input type="text" name="nombre" required>
                Apellido: <input type="text" name="apellido" required>
            </p>
            <p>
                Sexo:
                <select name="sexo" required>
                    <option value="">Seleccione</option>
                    <option value="M">Masculino</option>
                    <option value="F">Femenino</option>
                </select>
                Nacionalidad: <input type="text" name="nacionalidad" required>
            </p>
            <p>
                Fecha de nacimiento: <input type="date" name="fechaNacimiento" required>
                Dirección: <input type="text" name="direccion" required>
            </p>
            <p>
                Correo electrónico: <input type="email" name="email" required>
                Teléfono: <input type="number" name="telefono" required>
            </p>           
        </fieldset>
</div>

<div class="parteDer">
    <h2>Formulario Usuario</h2>

    
        <fieldset>
           
            <p>
                Usuario: <input type="text" name="usuario" required>
            </p>
            <p>
                Contraseña: <input type="password" name="password" required>
            </p>
            <p>
                Repetir contraseña: <input type="password" name="passwordConfirm" required>
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

  <button name="btnRegistrar" class="btnRegistrar">Registrar</button>
</div>
    </form>

<div class="formulariosWrapper listadoContainer">
<h2>Listado de Clientes</h2>
<div class="tablaCuentasContainer">
<div class="filtrosContainer">
 <p> <b>Busqueda:</b> <input type="text" name="search">  
 <input type="submit" value="Buscar" class="btnBuscar">
 </p>
 
   
     <p> DNI: <input type="text" name="idCliente">  </p>
     <p><input type="submit" value="Filtrar" class="btnFiltrar"></p>
  
</div>

	<table>
        <thead>
            <tr>
                <th>DNI</th>
                <th>Nombre y Apellido</th>
                <th>Sexo y Nacionalidad</th>
                <th>Fecha de nacimiento</th>
            </tr>
        </thead>
        <tbody >
             <tr>
                 <td>123421555</td>
                 <td>Pedro gonzales</td>
                 <td>Masculino/ Argentino</td>
                 <td>17/20/1990</td>
             </tr>
             <tr>
                 <td>42652311</td>
                 <td>Pechito Martinez perez</td>
                 <td>No binario/ Uruguayo</td>
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


</div>



</body>
</html>