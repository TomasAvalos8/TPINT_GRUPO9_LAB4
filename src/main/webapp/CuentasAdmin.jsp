<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, Dominio.Cuenta, Dominio.TipoCuenta" %>
<%
if(request.getAttribute("listaCuentas") == null || request.getAttribute("tiposCuenta") == null || request.getAttribute("siguienteIdCuenta") == null) {
    response.sendRedirect("CuentasAdminServlet");
    return;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Creacion de cuentas</title>
<link rel="stylesheet" type="text/css" href="estilos/estilos.css">

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.5.0/css/responsive.dataTables.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
<jsp:include page="MenuAdmin.html"></jsp:include>

<%
    Dominio.Cuenta cuentaModificar = (Dominio.Cuenta) request.getAttribute("cuentaModificar");
    String tipoCuentaFiltroSeleccionado = request.getParameter("tipoCuentaFiltro");
    if (tipoCuentaFiltroSeleccionado == null) {
        tipoCuentaFiltroSeleccionado = ""; // Por defecto, sin filtro
    }
%>

<% String usuario = (String)session.getAttribute("usuario"); %>
<p class="userLoguedText">
  <i class="fas fa-user"></i> <%= usuario %>
</p>
<div class="contenedorFormularios">

<div class="formulariosWrapper">

<div class="parteDer">
    <h2>Formulario de Cuentas</h2>

    <form class="formCliente" method="post" action="CuentasAdminServlet">
        <fieldset>
            <p>
                <label for="id">ID Cuenta:</label>
                <input type="number" id="id" name="id" value="<%= cuentaModificar != null ? cuentaModificar.getId() : (request.getAttribute("siguienteIdCuenta") != null ? request.getAttribute("siguienteIdCuenta") : "") %>" readonly>
                          
                <label for="dni">DNI del Cliente:</label>
                <input type="number" name="dni" id="dni" required value="<%= cuentaModificar != null ? cuentaModificar.getDni() : "" %>" />

                <label for="cbu">CBU:</label>
                <input type="text" name="cbu" id="cbu" readonly value="<%= cuentaModificar != null ? cuentaModificar.getCBU() : (request.getAttribute("siguienteIdCuenta") != null ? String.format("%022d", Integer.parseInt(request.getAttribute("siguienteIdCuenta").toString())) : "") %>" />
            </p>

            <p>

                <label for="tipoCuenta">Tipo de cuenta:</label>
                <select name="tipoCuenta" id="tipoCuenta" required>
                    <option value="">Seleccione</option>
                    <% java.util.List<Dominio.TipoCuenta> tiposCuenta = (List<TipoCuenta>) request.getAttribute("tiposCuenta");
                       if (tiposCuenta != null) {
                           for (Dominio.TipoCuenta tipo : tiposCuenta) { %>
                               <option value="<%= tipo.getIdTipoCuenta() %>" <%= cuentaModificar != null && cuentaModificar.getTipo() != null && cuentaModificar.getTipo().getIdTipoCuenta() == tipo.getIdTipoCuenta() ? "selected" : "" %>><%= tipo.getDescripcion() %></option>
                    <%     }
                       }
                    %>
                </select>

                <label for="saldo">Saldo inicial:</label>
                <input type="number" name="saldo" id="saldo" step="0.01" required readonly value="<%= cuentaModificar != null ? cuentaModificar.getSaldo() : "10000" %>" />
            </p>
        </fieldset>

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
        <% if (cuentaModificar != null) { %>
            <button class="btnActualizar" type="submit" name="actualizar">Actualizar</button>
        <% } else { %>
            <button class="btnRegistrar" type="submit" name="registrar">Registrar</button>
        <% } %>
        </div>
    </form>
</div>

</div>

<div class="formulariosWrapper listadoContainer">
<h2>Listado de Cuentas</h2>
<div class="tablaCuentasContainer">

    <div class="filtrosContainer">
        <form method="get" action="CuentasAdminServlet">
            <p><b>Filtrar:</b>
                Tipo de cuenta:
                <select name="tipoCuentaFiltro">
                    <option value="">Seleccione</option>
                    <% 
                       if (tiposCuenta != null) {
                           for (Dominio.TipoCuenta tipo : tiposCuenta) { 
                               String selected = "";
                               if (tipoCuentaFiltroSeleccionado.equals(String.valueOf(tipo.getIdTipoCuenta()))) {
                                   selected = "selected";
                               }
                    %>
                               <option value="<%= tipo.getIdTipoCuenta() %>" <%= selected %>><%= tipo.getDescripcion() %></option>
                    <%     }
                       }
                    %>
                </select>
            </p>
            <p><input type="submit" value="Filtrar" class="btnFiltrar"></p>
        </form>
    </div>

    <table id="tablaCuentas" class="display responsive nowrap" style="width:100%">
        <thead>
            <tr>
                <th>ID Cuenta</th>
                <th>Tipo de Cuenta</th>
                <th>DNI Cliente</th>
                <th>CBU</th>
                <th>Fecha Creacion</th>
                <th>Estado</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
        <% 
            List<Cuenta> listaCuentas = (List<Cuenta>) request.getAttribute("listaCuentas");
            if (listaCuentas != null) {
                for (Dominio.Cuenta cuenta : listaCuentas) { 
        %>
            <tr>
                <td><%= cuenta.getId() %></td>
                <td><%= cuenta.getTipo() != null ? cuenta.getTipo().getDescripcion() : "N/A" %></td>
                <td><%= cuenta.getDni() %></td>
                <td><%= cuenta.getCBU() %></td>
                <td><%= cuenta.getCreacion() %></td>
                <td><%= cuenta.isEstado() ? "Activa" : "Inactiva" %></td>
                <td>
                    <form class="boton" method="post" action="CuentasAdminServlet" >
                        <input type="hidden" name="eliminarId" value="<%= cuenta.getId() %>" />
                        <button class="btnEliminar" type="submit" onclick="return confirm('¿Estás seguro que quieres eliminar esta cuenta?');">Eliminar</button>
                    </form>
                    <form class="boton" method="post" action="CuentasAdminServlet" >
                        <input type="hidden" name="modificarId" value="<%= cuenta.getId() %>" />
                        <button class="btnModificar" type="submit">Modificar</button>
                    </form>
                </td>
            </tr>
        <%      }
            } else { %>
            <tr>
                <td colspan="7">No hay cuentas disponibles</td>
            </tr>
        <% } %>
        </tbody>
    </table>
        
</div>
</div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.5.0/js/dataTables.responsive.min.js"></script>
		
<script type="text/javascript">
$(document).ready(function() {
    $('#tablaCuentas').DataTable({
        "language": {
            "url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
        }
    });
});
</script>

</body>
</html>