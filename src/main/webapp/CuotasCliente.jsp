<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Dominio.Cuota" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mis Prestamos</title>
<link rel="stylesheet" type="text/css" href="estilos/estilos.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
<script>
$(document).ready(function() {
    $('.dataTable').DataTable({
        language: {
            url: '//cdn.datatables.net/plug-ins/1.11.5/i18n/es-ES.json'
        },
        paging: true,
        searching: true,
        ordering: true
    });
});
</script>
</head>
<body>
<jsp:include page="MenuCliente.html"></jsp:include>

<% String usuario = (String) session.getAttribute("usuario"); %>
<p class="userLoguedText">
  <i class="fas fa-user"></i> <%= usuario != null ? usuario : "Usuario no identificado" %>
</p>

<% 
List<Cuota> listaCuotas = null;
Object cuotasAttribute = request.getAttribute("listaCuotas");
if (cuotasAttribute instanceof List<?>) {
    listaCuotas = (List<Cuota>) cuotasAttribute;
}
%>

<div class="contenedorFormularios">

<div class="formulariosWrapper listadoContainer">
<h2>Mis Préstamos</h2>

<table class="dataTable">
<thead>
<tr>
<th>ID Préstamo</th>
<th>Número de cuota</th>
<th>Importe</th>
<th>Vencimiento</th>
<th>Pagado</th>
<th>Acción</th>
</tr>
</thead>
<tbody>
<% if (listaCuotas != null && !listaCuotas.isEmpty()) { %>
    <% java.time.LocalDate hoy = java.time.LocalDate.now(); %>
    <% for (Cuota cuota : listaCuotas) { %>
        <% java.time.LocalDate fechaVencimiento = null;
           try { fechaVencimiento = java.time.LocalDate.parse(cuota.getFechaPago().toString()); } catch(Exception ex) {} %>
        <tr<%= cuota.isPagado() ? " style='background-color: #d4edda;'" : (fechaVencimiento != null && fechaVencimiento.isBefore(hoy) ? " style='background-color: #f8d7da;'" : "") %>>
        <td><%= cuota.getPrestamo().getId_prestamo() %></td>
        <td><%= cuota.getNumeroCuota() %></td>
        <td><%= cuota.getMonto() %></td>
        <td><%= cuota.getFechaPago() %></td>
        <td><%= cuota.isPagado() ? "Sí" : "No" %></td>
        <td>
            <% if (!cuota.isPagado()) { %>
                <form action="CuotaServlet" method="post" class="p-0">
                    <input type="hidden" name="idCuota" value="<%= cuota.getId() %>" />
                    <button type="submit" name="accion" value="pagar" class="btn-pagar">Pagar</button>
                </form>
            <% } else { %>
                <span>Pagado</span>
            <% } %>
        </td>
        </tr>
    <% } %>
<% } else { %>
    <tr>
    <td colspan="6">No hay cuotas disponibles.</td>
    </tr>
<% } %>
</tbody>
</table>

</div>

</div>

<footer>
<jsp:include page="Footer.html"></jsp:include>
</footer>
</body>
</html>