<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Dominio.Reporte" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Informes y Reportes - Sistema Bancario</title>
    <link rel="stylesheet" type="text/css" href="css/StyleSheet.css">
    <link rel="stylesheet" type="text/css" href="estilos/estilos.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        .informes-container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }
        
        .reporte-section {
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            margin-bottom: 30px;
            padding: 20px;
        }
        
        .reporte-header {
            display: flex;
            align-items: center;
            margin-bottom: 20px;
            padding-bottom: 15px;
            border-bottom: 2px solid #4CAF50;
        }
        
        .reporte-header i {
            font-size: 24px;
            color: #007bff;
            margin-right: 10px;
        }
        
        .reporte-title {
            font-size: 20px;
            font-weight: bold;
            color: #333;
            margin: 0;
        }
        
        .stats-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 20px;
            margin-top: 20px;
        }
        
        .stat-card {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 20px;
            border-radius: 10px;
            text-align: center;
            box-shadow: 0 4px 15px rgba(102, 126, 234, 0.3);
        }
        
        .stat-card.green {
            background: linear-gradient(135deg, #11998e 0%, #38ef7d 100%);
        }
        
        .stat-card.red {
            background: linear-gradient(135deg, #ff6b6b 0%, #ee5a6f 100%);
        }
        
        .stat-card.orange {
            background: linear-gradient(135deg, #ff9a9e 0%, #fecfef 100%);
        }
        
        .stat-value {
            font-size: 28px;
            font-weight: bold;
            margin-bottom: 5px;
        }
        
        .stat-label {
            font-size: 14px;
            opacity: 0.9;
        }
        
        .reporte-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        
        .reporte-table th,
        .reporte-table td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        
        .reporte-table th {
            background-color: #f8f9fa;
            font-weight: bold;
            color: #333;
        }
        
        .reporte-table tr:hover {
            background-color: #f5f5f5;
        }
        
        .filtros-container {
            background: #f8f9fa;
            border-radius: 8px;
            padding: 25px;
            margin-bottom: 30px;
        }
        
        .filtros-grid {
            display: grid;
            grid-template-columns: 1fr 1fr auto;
            gap: 20px;
            align-items: end;
        }
        
        .form-group {
            display: flex;
            flex-direction: column;
        }
        
        .form-group label {
            margin-bottom: 5px;
            font-weight: bold;
            color: #555;
        }
        
        .form-group input,
        .form-group select {
            padding: 10px;
            border: 2px solid #ddd;
            border-radius: 5px;
            font-size: 14px;
        }
        
        .form-group input:focus,
        .form-group select:focus {
            outline: none;
            border-color: #007bff;
        }
        
        .btn-generar {
            background: #4CAF50;
            color: white;
            border: none;
            padding: 12px 25px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            font-weight: bold;
            transition: all 0.3s ease;
        }
        
        .btn-generar:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(0, 123, 255, 0.3);
        }
        
        .error-message {
            background: #f8d7da;
            color: #721c24;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 20px;
            border: 1px solid #f5c6cb;
        }
        
        .success-message {
            background: #d4edda;
            color: #155724;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 20px;
            border: 1px solid #c3e6cb;
        }
        
        .no-data {
            text-align: center;
            color: #666;
            font-style: italic;
            padding: 40px;
        }
        
        .fecha-periodo {
            text-align: center;
            background: #e9ecef;
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 20px;
            font-weight: bold;
            color: #495057;
        }
        .reporte-header i{
        color: #4CAF50;
        }
    </style>
</head>
<body>
    <jsp:include page="MenuAdmin.html"></jsp:include>
    
    <div class="informes-container">
        <% String usuario = (String)session.getAttribute("usuario"); %>
        <p class="userLoguedText">
            <i class="fas fa-user"></i> <%= usuario %>
        </p>
        
        <h1><i class="fas fa-chart-bar"></i> Informes y Reportes</h1>
        <div class="filtros-container">
            <h3> Filtros de Reporte</h3>
            <form method="post" action="ServletInformes">
                <div class="filtros-grid">
                    <div class="form-group">
                        <label for="startDate">Fecha de Inicio:</label>
                        <input type="date" id="startDate" name="startDate" 
                               value="<%= request.getAttribute("fechaInicio") != null ? request.getAttribute("fechaInicio") : "" %>" 
                               required>
                    </div>
                    
                    <div class="form-group">
                        <label for="endDate">Fecha de Fin:</label>
                        <input type="date" id="endDate" name="endDate" 
                               value="<%= request.getAttribute("fechaFin") != null ? request.getAttribute("fechaFin") : "" %>" 
                               required>
                    </div>
                    
                    <div class="form-group">
                        <label for="tipoReporte">Tipo de Reporte:</label>
                        <select id="tipoReporte" name="tipoReporte">
                            <option value="completo">Seleccionar Tipo de reporte</option>
                            <option value="prestamos">Prestamos</option>
                            <option value="usuarios">Usuarios</option>
                            <option value="cuentas">Cuentas</option>
                        </select>
                    </div>
                    
                    <button type="submit"  class="btn-generar">
                        <i class="fas fa-chart-line"></i> Generar Reporte
                    </button>
                </div>
            </form>
        </div>
        <% if (request.getAttribute("fechaInicio") != null && request.getAttribute("fechaFin") != null) { %>
            <div class="fecha-periodo">
                <i class="fas fa-calendar-alt"></i>
                Período del reporte: <%= request.getAttribute("fechaInicio") %> al <%= request.getAttribute("fechaFin") %>
            </div>
        <% } %>  

        <% List<Reporte> reportePrestamos = (List<Reporte>)request.getAttribute("reportePrestamos"); %>

        <%--<% if (reportePrestamos != null && !reportePrestamos.isEmpty()) { %>--%>
        <% if ("prestamos".equals(request.getAttribute("tipoReporte")) && reportePrestamos != null && !reportePrestamos.isEmpty()) { %>
            <div class="reporte-section">
                <div class="reporte-header">
                    <i class="fas fa-hand-holding-usd"></i>
                    <h2 class="reporte-title">Reporte de Prestamos</h2>
                </div>
                
                <table class="reporte-table">
  					 <thead>
                    <tr>
                        <th>Cantidad de Prestamos</th>
                        <th>Porcentaje prestamos Aprobados</th>
                        <th>Porcentaje prestamos Rechazados</th>
                        <th>Porcentaje prestamos Pendientes</th>    
                    </tr>
                </thead>
                <tbody>
                    <% 

                    
                    for (Reporte reporte : reportePrestamos) { 
                    %>
                    <tr>
                       	<td><%= reporte.getTotalPrestamos() %></td>
						<td><%= String.format("%.2f", reporte.getPorcAprobados()) %> %</td>
						<td><%= String.format("%.2f", reporte.getPorcRechazados()) %> %</td>
						<td><%= String.format("%.2f", reporte.getPorcPendientes()) %> %</td>
                     
                    </tr>
                    <% } %>
                </tbody>
                </table>
            </div>
        <% } %>
        <% List<Reporte> reporteUsuarios = (List<Reporte>)request.getAttribute("reporteUsuarios"); %>
		<% if ("usuarios".equals(request.getAttribute("tipoReporte")) && reporteUsuarios != null && !reporteUsuarios.isEmpty()) { %>
    	<div class="reporte-section">
        <div class="reporte-header">
            <i class="fas fa-users"></i>
            <h2 class="reporte-title">Reporte de Usuarios</h2>
        </div>
        <table class="reporte-table">
            <thead>
                <tr>
                    <th>Total de Usuarios</th>
                    <th>% Usuarios Activos</th>
                    <th>% Usuarios Inactivos</th>
                </tr>
            </thead>
            <tbody>
                <% for (Reporte reporte : reporteUsuarios) { %>
                <tr>
                    <td><%= reporte.getTotalUsuarios() %></td>
                    <td><%= String.format("%.2f", reporte.getPorcActivos()) %> %</td>
                    <td><%= String.format("%.2f", reporte.getPorcInactivos()) %> %</td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
<% } %>
<% List<Reporte> reporteCuentas = (List<Reporte>)request.getAttribute("reporteCuentas"); %>
<% if ("cuentas".equals(request.getAttribute("tipoReporte")) && reporteCuentas != null && !reporteCuentas.isEmpty()) { %>
    <div class="reporte-section">
        <div class="reporte-header">
            <i class="fas fa-piggy-bank"></i>
            <h2 class="reporte-title">Reporte de Cuentas por Tipo</h2>
        </div>
        <table class="reporte-table">
            <thead>
                <tr>
                    <th>Tipo de Cuenta</th>
                    <th>Cantidad</th>
                </tr>
            </thead>
            <tbody>
                <% for (Reporte reporte : reporteCuentas) { %>
                <tr>
                    <td><%= reporte.getDescripcionTipoCuenta() %></td>
                    <td><%= reporte.getCantidadCuentas() %></td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
<% } %>
</div>
</body>
</html>