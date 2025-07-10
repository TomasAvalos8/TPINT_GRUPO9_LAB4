<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="Dominio.Cuenta"%>
    <%@page import="Dominio.SolicitudPrestamo" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Solicitud de Préstamo</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<link rel="stylesheet" type="text/css" href="estilos/estilos.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.css">

</head>
<%
String tipo= (String)session.getAttribute("tipoUsuario");
Integer tipoUsuarioId = (Integer) session.getAttribute("tipoUsuarioId");
if (tipoUsuarioId == null || tipoUsuarioId == 0) {
    response.sendRedirect("Inicio.jsp");
    return;
}
if (tipoUsuarioId != 2) {
    response.sendRedirect("InicioAdmin.jsp");
    return;
}
%>
<body>
    <jsp:include page="MenuCliente.html"></jsp:include>
    <% String usuario = (String)session.getAttribute("usuario"); %>
    <p class="userLoguedText">
  <button class="datos-personales-btn" onclick="location='DatosPersonales.jsp'">
    <i class="fas fa-user"></i> <%= usuario != null ? usuario : "Usuario no identificado" %>
  </button>
</p>
    <h1 class="Titulo">Solicitud de préstamo</h1>
    
    <div class="ContenedorPrestamo">
        <form action="SolicitudPrestamoServlet" method="post">
            <h3>Ingrese el monto</h3>
            <input type="number" name="importe_solicitado" placeholder="Ej: 10000" min="1" step="0.01" required value="<%= request.getAttribute("importe_solicitado") != null ? request.getAttribute("importe_solicitado") : "" %>">
            
            <h3>¿En cuántas cuotas?</h3>
            <div class="cuotas-container">
                <div class="cuota-select">
                    <select name="cuotas" required>
                        <option value="" disabled <%= request.getAttribute("cuotas") == null ? "selected" : "" %>>Seleccione cuotas</option>
                        <option value="6" <%= "6".equals(request.getAttribute("cuotas")) ? "selected" : "" %>>6 cuotas</option>
                        <option value="12" <%= "12".equals(request.getAttribute("cuotas")) ? "selected" : "" %>>12 cuotas</option>
                        <option value="18" <%= "18".equals(request.getAttribute("cuotas")) ? "selected" : "" %>>18 cuotas</option>
                        <option value="24" <%= "24".equals(request.getAttribute("cuotas")) ? "selected" : "" %>>24 cuotas</option>
                        <option value="30" <%= "30".equals(request.getAttribute("cuotas")) ? "selected" : "" %>>30 cuotas</option>
                        <option value="36" <%= "36".equals(request.getAttribute("cuotas")) ? "selected" : "" %>>36 cuotas</option>
                        <option value="42" <%= "42".equals(request.getAttribute("cuotas")) ? "selected" : "" %>>42 cuotas</option>
                    </select>
                </div>
                <div class="info-box" id="interes-info">
                    <%= (request.getAttribute("interes") != null ? request.getAttribute("interes") : "0% interés") %>
                </div>
            </div>
            
         
            
            <button type="submit" class="btn-solicitar" name="accion" value="calcular">Calcular Total</button>
       

            <h2>Total en cuotas</h2>
            <div class="total-cuotas-container">
                <div class="total-cuotas-amount">
                    <%= request.getAttribute("cuotaMensual") != null ? request.getAttribute("cuotaMensual") : "" %>
                </div>
                <div class="info-box">
                    <%= (request.getParameter("cuotas") != null ? request.getParameter("cuotas") + " cuotas" : "") %>
                </div>
            </div>
            
            <h2>Total a pagar</h2>
            <div class="total-container">
                <span class="total-label">Total:</span>
                <span class="total-amount">
                    <%= request.getAttribute("totalPagar") != null ? request.getAttribute("totalPagar") : "" %>
                </span>
            </div>

            <h3>Seleccione la cuenta de depósito</h3>
            <div class="cuota-select">
                <select name="numero_cuenta_deposito" >
                    <option value="" disabled <%=  request.getParameter("numero_cuenta_deposito") == null ? "selected" : "" %>>Seleccione cuenta</option>
                    <% java.util.List<Cuenta> cuentas = (java.util.List<Cuenta>) request.getAttribute("cuentasCliente");
                       if (cuentas != null) {
                           for (Cuenta c : cuentas) { %>
                        <option value="<%= c.getId() %>" <%= (""+c.getId()).equals(request.getParameter("numero_cuenta_deposito")) ? "selected" : "" %>>Cuenta N° <%= c.getId() %> - CBU: <%= c.getCBU() %></option>
                    <%   }
                       }
                    %>
                </select>
            </div>
            <button type="submit" class="btn-solicitar">Solicitar Préstamo</button>
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
        </form>
    </div>

    <h2>Historial de Préstamos</h2>
    <div class="loan-history-container">
        <table id="loanHistoryTable" class="display">
            <thead>
                <tr>
                    <th class="align-center">ID</th>
                    <th class="align-center">Monto</th>
                    <th class="align-center">Importe a Pagar</th>
                    <th class="align-center">Estado</th>
                </tr>
            </thead>
            <tbody>
                <% java.util.List<SolicitudPrestamo> solicitudes = (java.util.List<SolicitudPrestamo>) request.getAttribute("solicitudesUsuario");
                   if (solicitudes != null && !solicitudes.isEmpty()) {
                       for (SolicitudPrestamo solicitud : solicitudes) { %>
                    <tr>
                        <td><%= solicitud.getId_solicitud() %></td>
                        <td><%= solicitud.getImporte_solicitado() %></td>
                        <td><%= solicitud.getImporte_pagar_intereses() %></td>
                        <td><%= solicitud.getEstadoString() != null ? solicitud.getEstadoString() : "Pendiente" %></td>
                    </tr>
                <%   }
                   } else { %>
                    <tr>
                        <td colspan="4">No hay solicitudes de préstamo disponibles.</td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>

    <footer>
        <jsp:include page="Footer.html"></jsp:include>
    </footer>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#loanHistoryTable').DataTable();
        });
    </script>
    <script>
        const cuotasSelect = document.querySelector('select[name="cuotas"]');
        const interesInfo = document.getElementById('interes-info');
        function getInteresText(cuotas) {
            switch (cuotas) {
                case '6': return '5% interés';
                case '12': return '10% interés';
                case '18': return '15% interés';
                case '24': return '20% interés';
                case '30': return '25% interés';
                case '36': return '30% interés';
                case '42': return '35% interés';
                default: return '';
            }
        }
        cuotasSelect.addEventListener('change', function() {
            interesInfo.textContent = getInteresText(this.value);
        });
    </script>
</body>
</html>