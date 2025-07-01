<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, Dominio.Usuario" %>
<%
Integer tipoUsuarioId = (Integer) session.getAttribute("tipoUsuarioId");
if (tipoUsuarioId == null || tipoUsuarioId == 0) {
    response.sendRedirect("login.jsp");
    return;
}
if (tipoUsuarioId != 1) {
    response.sendRedirect("InicioCliente.jsp");
    return;
}

if(request.getAttribute("usuarios") == null) {
    response.sendRedirect("ServletUsuario");
    return;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manejo de usuarios</title>
<link rel="stylesheet" type="text/css" href="estilos/estilos.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.5.0/css/responsive.dataTables.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
<jsp:include page="MenuAdmin.html"></jsp:include>

<% String usuario = (String)session.getAttribute("usuario"); %>
<p class="userLoguedText">
  <i class="fas fa-user"></i> <%= usuario %>
</p>
<div class="contenedorFormularios">
<div class="formulariosWrapper">
<div class="parteDer">
    <h2>Crear nuevo usuario Administrador</h2>
    <form class="formCliente" method="post" action="ServletUsuario">
        <fieldset>
         <p>    
             Usuario: <input type="text" name="usuario" required>
             Contraseña: <input type="password" name="password" required>
             Repetir contraseña: <input type="password" name="passwordConfirm" required>
         </p>
        </fieldset>
        <% 
        String mensaje = (String) request.getAttribute("mensaje");
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
          <button name="btnRegistrarUsuario" class="btnRegistrar">Registrar</button>
        </div>
    </form>
</div>
</div>
</div>
<div class="formulariosWrapper listadoContainer">
<h2>Listado de Usuarios</h2>
<div class="tablaCuentasContainer">
    <%
        List<Usuario> listaUsuarios = (List<Usuario>) request.getAttribute("usuarios");
    %>
    <table id="tablaUsuarios" class="display responsive nowrap" style="width:100%">
        <thead>
            <tr>
                <th>ID Usuario</th>
                <th>Nombre de usuario</th>
                <th>Fecha Alta</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
<% if (listaUsuarios != null && !listaUsuarios.isEmpty()) {
    for (Usuario u : listaUsuarios) {
        if (u != null) {
            String fechaAlta = u.getFechaAlta() != null ? u.getFechaAlta().toString() : "N/A";
%><tr>
    <td><%= u.getId_usuario() %></td>
    <td><%= u.getUsuario() %></td>
    <td><%= fechaAlta %></td>
    <td>
        <form method="post" action="ServletUsuario">
            <input type="hidden" name="usuarioEliminar" value="<%= u.getId_usuario() %>"/>
            <button type="submit" name="btnEliminarUsuario" class="btnEliminar" 
                    onclick="return confirm('¿Está seguro de eliminar este usuario?');">
                Eliminar
            </button>
        </form>
    </td>
</tr><%
        }
    }
} else { %><tr>
    <td colspan="4" style="text-align:center;">No se encontraron usuarios.</td>
</tr><% } %>
        </tbody>
    </table>
</div>
</div>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.5.0/js/dataTables.responsive.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    var table = $('#tablaUsuarios').DataTable({
        "responsive": true,
        "autoWidth": false,
        "language": {
            "url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
        },
        "columns": [
            { "width": "10%" },
            { "width": "25%" },
            { "width": "20%" },
            { "width": "45%" }
        ],
        "pageLength": 10,
        "searching": true,
        "ordering": true,
        "lengthChange": false,
        "info": true,
        "dom": 'rtip'
    });
    
    // Recalcular columnas al cambiar tamaño
    $(window).resize(function() {
        table.columns.adjust().responsive.recalc();
    });
});
</script>
</body>
</html>