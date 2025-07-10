<%@page import="Dominio.Movimiento"%>
<%@page import="Dominio.Cuenta"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Banco UTN</title>
<link rel="stylesheet" type="text/css" href="estilos/estilos.css">
<link rel="stylesheet" type="text/css" href="css/StyleSheet.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<style type="text/css">

.formMovimiento {
  display: flex;
  justify-content: center;
  margin-top: 15px;
  margin-right: 20px;

}
.btnGenerar{
	margin-left: 20px;
}
 .datos-personales-btn {
    
    background-color: #e0e0e0;
    border: none;
    padding: 8px 15px;
    border-radius: 20px;
    cursor: pointer;
    font-size: 14px;
    color: #333;
    transition: all 0.3s ease;
    box-shadow: 0 2px 5px rgba(0,0,0,0.1);
    display: inline-flex;
    align-items: center;
    gap: 8px;
    
   
    font-family: 'Segoe UI', Arial, sans-serif;
    font-weight: 500;
    letter-spacing: 0.5px;
    
   
    border: 1px solid transparent;
  }

  .datos-personales-btn:hover {
    background-color: #d0d0d0;
    transform: translateY(-1px);
    box-shadow: 0 3px 8px rgba(0,0,0,0.15);
    border-color: #b0b0b0;
  }

  .datos-personales-btn:active {
    transform: translateY(0);
    background-color: #c0c0c0;
  }

  .datos-personales-btn i {
    font-size: 16px;
    color: #555;
  }
</style>
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
<% String usuario = (String)session.getAttribute("usuario");
  String cuentaSeleccionada = (String) request.getAttribute("cuentaSeleccionada");
  String fechaDesde = (String)request.getAttribute("fechaDesde");
  String fechaHasta = (String)request.getAttribute("fechaHasta");
%>

 <p class="userLoguedText">
  <button class="datos-personales-btn" onclick="location='DatosPersonales.jsp'">
    <i class="fas fa-user"></i> <%= usuario != null ? usuario : "Usuario no identificado" %>
  </button>
</p>


<div class="formulariosWrapper listadoContainer ">

    <h2>Historial de movimientos</h2>
<div>
   <form method="post" action="ServletMovimientos">
  <label for="cuenta">Cuenta:</label>
  <select name="numeroCuenta" onchange="this.form.submit()">
    <option value="">Seleccione una cuenta</option>
    <%
      List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentas");
      if (cuentas != null) {
        for (Cuenta c : cuentas) {
    String selected = (cuentaSeleccionada != null && cuentaSeleccionada.equals(String.valueOf(c.getId()))) ? "selected" : "";
    %>
      <option value="<%= c.getId() %>" <%= selected %>>Cuenta Nº <%= c.getId() %></option>
    <%
        }
      }
    %>
  </select>
</form>


  </div>
     <form class="formMovimiento" method="post" action="ServletMovimientos ">
    	<fieldset>
        	<div>
            	<label for="fechaDesde">Fecha desde:</label>
            	<input type="date" name="fechaDesde" value="<%= fechaDesde != null ? fechaDesde : "" %>">
            	<label for="fechaHasta">Fecha hasta:</label>
            	<input type="date" name="fechaHasta" value="<%= fechaHasta != null ? fechaHasta : "" %>">
            	<input type="hidden" name="numeroCuenta" value="<%= cuentaSeleccionada != null ? cuentaSeleccionada : "" %>">
            	
            	<button name="btnFiltrar" class="btnGenerar" style="background-color:blue;" > <i class="bi bi-funnel"></i>Filtrar</button>
            	<button name="btnLimpiar" class="btnGenerar" > <i class="bi bi-arrow-counterclockwise"></i>   Limpiar</button>
        	</div>

    	</fieldset>
	</form>

    <div class="tablaMovimientoContainer ">

        <table id="tablaMov" class="display">
            <thead>
                <tr>
                    <th>Fecha</th>
                    <th>Numero cuenta</th>
                    <th>Detalle</th>
                    <th>Tipo</th>
                    <th>Importe</th>
                </tr>
            	</thead>
           	 	<tbody><%
           	 	List<Movimiento> ListaMovimientos = (List<Movimiento>)request.getAttribute("ListaMovimientos");
                	if(ListaMovimientos != null && !ListaMovimientos.isEmpty()){
                	for (Movimiento movimiento : ListaMovimientos){
                	    String fechaFormateada = "";
                        if (movimiento.getFecha() != null) {
                            fechaFormateada = String.format("%02d/%02d/%d", 
                                movimiento.getFecha().getDayOfMonth(),
                                movimiento.getFecha().getMonthValue(),
                                movimiento.getFecha().getYear());
                        }
                	
                	
                	
                	%><tr>
                	
                	<td><%= fechaFormateada%></td>
                	<td><%= movimiento.getNumeroCuenta()%></td>
                	<td><%= movimiento.getDetalle()%></td>
                	<td><%= movimiento.getTipoMovimiento().getDescripcion()%></td>
                	<td><%= movimiento.getMonto()%></td>
                	
                	</tr><%
                	}
                	}
                	%>
            	</tbody>
        	</table>
    </div>
</div>



<jsp:include page="Footer.html"></jsp:include>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
		
<script type="text/javascript">
	$(document).ready(function() {
	    $('#tablaMov').DataTable({
	        language: {
	            url: "//cdn.datatables.net/plug-ins/1.10.25/i18n/Spanish.json",
	            emptyTable: "No se encontraron movimientos"
	            
	        },
	        columnDefs: [
	            { "type": "date-eu", "targets": 0 },
	            { "type": "currency", "targets": 4 }
	        ],
	        order: [[0, "desc"]],
	        responsive: true
	    });
	});
</script>

</body>
</html>