<%@page import="Dominio.Provincia"%>
<%@page import="Dominio.Localidad"%>
<%@page import="Dominio.Cliente"%>
<%@page import="Dominio.Usuario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Integer tipoUsuarioId = (Integer) session.getAttribute("tipoUsuarioId");
if (tipoUsuarioId == null || tipoUsuarioId == 0) {
    response.sendRedirect("Inicio.jsp");
    return;
}
if (tipoUsuarioId != 1) {
    response.sendRedirect("InicioCliente.jsp");
    return;
}

if(request.getAttribute("listaClientes") == null || request.getAttribute("provincias") == null) {
    response.sendRedirect("ServletClientes");
    return;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestión de Clientes</title>
<link rel="stylesheet" type="text/css" href="css/StyleSheet.css">
<link rel="stylesheet" type="text/css" href="estilos/estilos.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.5.0/css/responsive.dataTables.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
   
</head>
<script type="text/javascript">

function eventoSeleccionarProvincia() {
    var idProvincia = document.getElementById("idProvincia").value;
    if (idProvincia) {
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "ServletClientes?idProvincia=" + idProvincia, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var localidades = JSON.parse(xhr.responseText);
                var localidadSelect = document.getElementsByName("idLocalidad")[0];
                localidadSelect.innerHTML = "<option value=''>Seleccione</option>";
                localidades.forEach(function (localidad) {
                    var option = document.createElement("option");
                    option.value = localidad.id_localidad;
                    option.textContent = localidad.descripcion;
                    localidadSelect.appendChild(option);
                });
            }
        };
        xhr.send();
    }
}
</script>
<body>


<jsp:include page="MenuAdmin.html"></jsp:include>

<div class="contenedorFormularios">
<% String usuario = (String)session.getAttribute("usuario"); %>
<p class="userLoguedText">
  <i class="fas fa-user"></i> <%= usuario %>
</p>
    <%
Cliente clienteModificar = (Cliente) request.getAttribute("clienteModificar");
%>

<form class="formCliente" method="post" action="ServletClientes">
    <div class="formulariosWrapper">

        <div class="parteDer">
            <h2>Formulario de Cliente</h2>

            <fieldset>
                <p>
                    DNI: <input type="text" pattern="^[0-9]+$" name="dni" maxlength="8" required  title="Ingrese solo números" value="<%= (clienteModificar != null) ? clienteModificar.getDni() : "" %>" <%= (clienteModificar != null) ? "readonly" : "" %> >
                    CUIL: <input type="text" name="cuil" required title="Ingrese solo números" value="<%= (clienteModificar != null) ? clienteModificar.getCuil() : "" %>">
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
                    Provincia:
                    <select name="idProvincia" id="idProvincia" onchange="eventoSeleccionarProvincia()" required>
                        <option value="">Seleccione</option>
                        <%
                        List<Provincia> lista = (List<Provincia>) request.getAttribute("provincias");
                        if (lista != null && !lista.isEmpty()) {
                            for (Provincia p : lista) {
                                String seleccionado = "";
                                if (clienteModificar != null) {
                                    seleccionado = (String.valueOf(p.getId_provincia()).equals(String.valueOf(clienteModificar.getProvincia() != null ? clienteModificar.getProvincia().getId_provincia() : ""))) ? "selected" : "";
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
                                if (clienteModificar != null && clienteModificar.getLocalidad() != null && l.getId_localidad() == clienteModificar.getLocalidad().getId_localidad()) {
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
                    Correo electrónico: <input type="email" name="email" required value="<%= (clienteModificar != null) ? clienteModificar.getCorreo_electronico() : "" %>">
                    Teléfono: <input type="number" name="telefono" required value="<%= (clienteModificar != null) ? clienteModificar.getTelefono() : "" %>">
                </p>
            </fieldset>
        </div>

        <div class="parteDer">
            <h2>Formulario Usuario</h2>

            <fieldset>
                <p>
                    Usuario: <input type="text" name="usuario" required value="<%= (clienteModificar != null && request.getAttribute("usuarioModificar") != null) ? ((Usuario)request.getAttribute("usuarioModificar")).getUsuario() : "" %>">
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
                <input type="hidden" name="idUsuario" value="<%= (clienteModificar != null && clienteModificar.getUsuario() != null) ? clienteModificar.getUsuario().getId_usuario() : "" %>" />
                <% } %>
            </fieldset>
        </div>

    </div>
        <% 
        String mensaje = (String) request.getAttribute("mensajeServlet");
        String claseMensaje = "";
        if (mensaje != null) {
            if (mensaje.contains("Error")) {
                claseMensaje = "error";
            } else if (mensaje.contains("exitosamente")) {
                claseMensaje = "success";
            }
        }
        %>
        <% if (mensaje != null) { %>
            <div class="mensajeServlet <%= claseMensaje %>">
                <%= mensaje %>
            </div>
        <% } %>

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
        <table id="tablaClientes" class="display responsive nowrap" style="width:100%">
            <thead>
                <tr>
                    <th width="10%">DNI</th>
                    <th width="10%">CUIL</th>
                    <th width="20%">Nombre Completo</th>
                    <th width="5%">Sexo</th>
                    <th width="10%">Nacionalidad</th>
                    <th width="12%">Fecha Nac.</th>
                    <th width="18%">Contacto</th>
                    <th width="25%">Acciones</th>
                </tr>
            </thead>
            <tbody><%
            List<Cliente> listaClientes = (List<Cliente>) request.getAttribute("listaClientes");
            if (listaClientes != null && !listaClientes.isEmpty()) {
                for (Cliente cliente : listaClientes) {
                    String fechaFormateada = "";
                    if (cliente.getFecha_nacimiento() != null) {
                        fechaFormateada = String.format("%02d/%02d/%d", 
                            cliente.getFecha_nacimiento().getDayOfMonth(),
                            cliente.getFecha_nacimiento().getMonthValue(),
                            cliente.getFecha_nacimiento().getYear());
                    }
                    String correo = cliente.getCorreo_electronico() != null ? cliente.getCorreo_electronico() : "";
                    String telefono = cliente.getTelefono() != null ? cliente.getTelefono() : "";
            %><tr>
                <td><%= cliente.getDni() %></td>
                <td><%= cliente.getCuil() %></td>
                <td><%= cliente.getApellido() + ", " + cliente.getNombre() %></td>
                <td><%= "M".equals(cliente.getSexo()) ? "M" : "F".equals(cliente.getSexo()) ? "F" : "X" %></td>
                <td><%= cliente.getNacionalidad() != null ? cliente.getNacionalidad() : "" %></td>
                <td><%= fechaFormateada %></td>
                <td><% if (!correo.isEmpty()) { %><i class="fas fa-envelope"></i> <%= correo %><br><% } %>
                    <% if (!telefono.isEmpty()) { %><i class="fas fa-phone"></i> <%= telefono %><% } %></td>
                <td><form method="post" class="boton" action="ServletClientes" >
                        <input type="hidden" name="modificarId" value="<%= cliente.getDni() %>">
                        <button class="btnModificar" type="submit">Modificar</button>
                    </form>
                    <form method="post" class="boton" action="ServletClientes" >
                        <input type="hidden" name="eliminarId" value="<%= cliente.getDni() %>">
                        <button class="btnEliminar" type="submit" onclick="return confirm('¿Está seguro que desea eliminar este cliente?');">Eliminar</button>
                    </form></td>
            </tr><%
                }
            }
            %></tbody>
        </table>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.5.0/js/dataTables.responsive.min.js"></script>
		
<script type="text/javascript">
$(document).ready(function() {
    $('#tablaClientes').DataTable({
        responsive: true,
        pageLength: 10,
        language: {
            url: "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json",
            emptyTable: "No se encontraron clientes"
        },
        columnDefs: [
            { targets: -1, orderable: false } // La última columna (Acciones) no es ordenable
        ]
    });
});
</script>
</body>
</html>
