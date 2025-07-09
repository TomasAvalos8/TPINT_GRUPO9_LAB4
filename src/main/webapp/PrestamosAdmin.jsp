<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="Dominio.SolicitudPrestamo"%>
<%
Integer tipoUsuarioId = (Integer) session.getAttribute("tipoUsuarioId");
if (tipoUsuarioId == null || tipoUsuarioId == 0) {
    response.sendRedirect("Inicio.jsp");
    return;
}
if (tipoUsuarioId != 1) {
    response.sendRedirect("ServletInicioCliente");
    return;
}

if(request.getAttribute("listaPrestamos") == null) {
    response.sendRedirect("PrestamosAdminServlet");
    return;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Aprobacion de prestamos</title>
<link rel="stylesheet" type="text/css" href="css/StyleSheet.css">
<link rel="stylesheet" type="text/css" href="estilos/estilos.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.5.0/css/responsive.dataTables.min.css">
</head>
<body>
<jsp:include page="MenuAdmin.html"></jsp:include>
<% String usuario = (String)session.getAttribute("usuario"); %>
<p class="userLoguedText">
  <i class="fas fa-user"></i> <%= usuario %>
</p>
<div class="contenedorFormularios">


<div class="formulariosWrapper listadoContainer">
<h2>Listado de Prestamos</h2>
<div class="tablaCuentasContainer">
    <table id="tablaPrestamos" class="display responsive nowrap" style="width:100%">
        <thead>
            <tr>
                <th>ID</th>
                <th>DNI Cliente</th>
                <th>Importe Solicitado</th>
                <th>Cuenta Depósito</th>
                <th>Cuotas</th>
                <th>Fecha Solicitud</th>
                <th>Autorización</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
        <% 
            java.util.List<SolicitudPrestamo> listaPrestamos = (java.util.List<SolicitudPrestamo>) request.getAttribute("listaPrestamos");
            if (listaPrestamos != null) {
                for (SolicitudPrestamo sp : listaPrestamos) { 
                    String rowClass = "";
                    String autorizacionText = "";
                    int autorizacion = sp.getAutorizacion();
                    if (autorizacion == 2) {
                        rowClass = "autorizado";
                        autorizacionText = "Aprobada";
                    } else if (autorizacion == 1) {
                        rowClass = "rechazado";
                        autorizacionText = "Rechazada";
                    } else {
                        autorizacionText = "Pendiente";
                    }
        %>
            <tr class="<%= rowClass %>">
                <td><%= sp.getId_solicitud() %></td>
                <td><%= sp.getCliente().getDni() %></td>
                <td><%= sp.getImporte_solicitado() %></td>
                <td><%= sp.getCuentaDeposito().getId() %></td>
                <td><%= sp.getCuotas() %></td>
                <td><%= sp.getFecha_solicitud() %></td>
                <td><%= autorizacionText %></td>
                <td>
        <form method="post" class="btnAprobar" action="PrestamosAdminServlet" style="display:inline">
            <input type="hidden" name="accion" value="aprobar">
            <input type="hidden" name="id" value="<%= sp.getId_solicitud() %>">
            <button  type="submit">Aprobar</button>
        </form>
        <form method="post" class="btnRechazar" action="PrestamosAdminServlet" style="display:inline">
            <input type="hidden" name="accion" value="rechazar">
            <input type="hidden" name="id" value="<%= sp.getId_solicitud() %>">
            <button  type="submit">Rechazar</button>
        </form>
                </td>
            </tr>
        <%      }
            } else { %>
            <tr>
                <td colspan="8">No hay préstamos disponibles</td>
            </tr>
        <% } %>
        </tbody>
    </table>
    <style>
    .autorizado { background-color: #d4edda !important; }
    .rechazado { background-color: #f8d7da !important; }
    </style>
        </div>
</div>
</div>

<%

if (tipoUsuarioId == null || tipoUsuarioId == 0) {
    response.sendRedirect("login.jsp");
    return;
}
if (tipoUsuarioId != 1) {
    response.sendRedirect("InicioCliente.jsp");
    return;
}
%>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.5.0/js/dataTables.responsive.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    $('#tablaPrestamos').DataTable({
        "language": {
            "url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
        }
    });
});
</script>

</body>
</html>