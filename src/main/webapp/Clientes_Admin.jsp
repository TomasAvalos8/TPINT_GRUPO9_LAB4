<%@page import="Dominio.Provincia"%>
<%@page import="Dominio.Localidad"%>
<%@page import="Dominio.Cliente"%>
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

    <%
Cliente clienteModificar = (Cliente) request.getAttribute("clienteModificar");
%>

<form class="formCliente" method="post" action="ServletClientes">
    <div class="formulariosWrapper">

        <div class="parteDer">
            <h2>Formulario de Cliente</h2>

            <fieldset>
                <p>
                    Provincia:
                    <select name="idProvincia" id="idProvincia" onchange="eventoSeleccionarProvincia()" required>
                        <option value="">Seleccione</option>
                        <%
                        List<Provincia> lista = (List<Provincia>) request.getAttribute("provincias");
                        if (lista != null && !lista.isEmpty()) {
                            for (Provincia p : lista) {
                                String seleccionado = "";
                                if (clienteModificar != null) {
                                    seleccionado = (String.valueOf(p.getId_provincia()).equals(String.valueOf(clienteModificar.getId_provincia()))) ? "selected" : "";
                                }
                        %>
                        <option value="<%= p.getId_provincia() %>" <%= seleccionado %>>
                            <%= p.getDescripcion() %>
                        </option>
                        <%
                            }
                        } else {
                        %>
                        <option value="">No hay provincias disponibles</option>
                        <%
                        }
                        %>
                    </select>
                    Localidad:
                    <select name="idLocalidad" required>
                        <option value="">Seleccione</option>
                        <%
                        List<Localidad> listaLocalidades = (List<Localidad>) request.getAttribute("localidades");
                        if (listaLocalidades != null && !listaLocalidades.isEmpty()) {
                            for (Localidad l : listaLocalidades) {
                                String selLocalidad = "";
                                if (clienteModificar != null && l.getId_localidad() == clienteModificar.getId_localidad()) {
                                    selLocalidad = "selected";
                                }
                        %>
                        <option value="<%= l.getId_localidad() %>" <%= selLocalidad %>><%= l.getDescripcion() %></option>
                        <%
                            }
                        }
                        %>
                    </select>
                </p>
                <p>
                    DNI: <input type="number" name="dni" required value="<%= (clienteModificar != null) ? clienteModificar.getDni() : "" %>" <%= (clienteModificar != null) ? "readonly" : "" %> >
                    CUIL: <input type="number" name="cuil" required value="<%= (clienteModificar != null) ? clienteModificar.getCuil() : "" %>">
                </p>
                <p>
                    Nombre: <input type="text" name="nombre" required value="<%= (clienteModificar != null) ? clienteModificar.getNombre() : "" %>">
                    Apellido: <input type="text" name="apellido" required value="<%= (clienteModificar != null) ? clienteModificar.getApellido() : "" %>">
                </p>
                <p>
                    Sexo:
                    <select name="sexo" required>
                        <option value="">Seleccione</option>
                        <option value="M" <%= (clienteModificar != null && "M".equals(clienteModificar.getSexo())) ? "selected" : "" %>>Masculino</option>
                        <option value="F" <%= (clienteModificar != null && "F".equals(clienteModificar.getSexo())) ? "selected" : "" %>>Femenino</option>
                    </select>
                    Nacionalidad: <input type="text" name="nacionalidad" required value="<%= (clienteModificar != null) ? clienteModificar.getNacionalidad() : "" %>">
                </p>
                <p>
                    Fecha de nacimiento: <input type="date" name="fechaNacimiento" required value="<%= (clienteModificar != null) ? clienteModificar.getFecha_nacimiento() : "" %>">
                    Dirección: <input type="text" name="direccion" required value="<%= (clienteModificar != null) ? clienteModificar.getDireccion() : "" %>">
                </p>
                <p>
                    Correo electrónico: <input type="email" name="email" required value="<%= (clienteModificar != null) ? clienteModificar.getCorreo_electronico() : "" %>">
                    Teléfono: <input type="number" name="telefono" required value="<%= (clienteModificar != null) ? clienteModificar.getTelefono() : "" %>">
                </p>
            </fieldset>
        </div>

        <div class="parteDer">
            <h2>Formulario Usuario</h2>

            <fieldset>
                <p>
                    Usuario: <input type="text" name="usuario" required value="<%= (clienteModificar != null && request.getAttribute("usuarioModificar") != null) ? ((Dominio.Usuario)request.getAttribute("usuarioModificar")).getUsuario() : "" %>">
                </p>
                <% if (clienteModificar == null) { %>
                <p>
                    Contraseña: <input type="password" name="password" required>
                </p>
                <p>
                    Repetir contraseña: <input type="password" name="passwordConfirm" required>
                </p>
                <% } %>
                <% if (clienteModificar != null && request.getAttribute("usuarioModificar") != null) { %>
                <input type="hidden" name="idUsuario" value="<%= ((Dominio.Usuario)request.getAttribute("usuarioModificar")).getId_usuario() %>" />
                <% } %>
            </fieldset>
        </div>

    </div>

    <div class="botonContainer">
        <% if (clienteModificar != null) { %>
        <button class="btnActualizar" type="submit" name="actualizar">Actualizar</button>
        <% } else { %>
        <button class="btnRegistrar" type="submit" name="btnRegistrar">Registrar</button>
        <% } %>
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
        <th>Accion</th>
    </tr>
</thead>
<tbody>
    <%
    List<Cliente> listaClientes = (List<Cliente>) request.getAttribute("listaClientes");
    if (listaClientes != null && !listaClientes.isEmpty()) {
        for (Cliente cliente : listaClientes) {
    %>
    <tr>
        <td><%= cliente.getDni() %></td>
        <td><%= cliente.getNombre() %> <%= cliente.getApellido() %></td>
        <td>
            <%
            String sexo = "";
            if ("M".equals(cliente.getSexo())) {
                sexo = "Masculino";
            } else if ("F".equals(cliente.getSexo())) {
                sexo = "Femenino";
            } else {
                sexo = "No especificado";
            }
            %>
            <%= sexo %> / <%= cliente.getNacionalidad() %>
        </td>
        <td>
            <%
    if (cliente.getFecha_nacimiento() != null) {
        // Formatear la fecha día/mes/año
        String fechaFormateada = String.format("%02d/%02d/%d", 
            cliente.getFecha_nacimiento().getDayOfMonth(),
            cliente.getFecha_nacimiento().getMonthValue(),
            cliente.getFecha_nacimiento().getYear());
        out.print(fechaFormateada);
    } else {
        out.print("");
    }
    %>
    
        </td>
        <td>
        			<form class="boton" method="post" action="ServletClientes" style="display:inline;">
                        <input type="hidden" name="eliminarId" value="<%= cliente.getDni() %>" />
                        <button class="btnEliminar" type="submit" onclick="return confirm('¿Estás seguro que quieres eliminar esta cliente?');">Eliminar</button>
                    </form>
                    <form class="boton" method="post" action="ServletClientes" style="display:inline;">
                        <input type="hidden" name="modificarId" value="<%= cliente.getDni() %>" />
                        <button class="btnModificar" type="submit">Modificar</button>
                    </form>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="4" style="text-align: center;">No se encontraron clientes</td>
    </tr>
    <%
    }
    %>
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