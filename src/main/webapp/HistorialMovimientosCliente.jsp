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

</style>
</head>
<body>
<jsp:include page="MenuCliente.html"></jsp:include>
<% String usuario = (String)session.getAttribute("usuario"); %>
<p class="userLoguedText">
  <i class="fas fa-user"></i> <%= usuario %>
</p>
<div class="formulariosWrapper listadoContainer ">

    <h2>Historial de movimientos</h2>
     <form class="formMovimiento" method="post" action=" ">
    	<fieldset>
        	<div>
            	 Fecha desde: 
            	<input type="date" name="startDate" required>
           	 	 Fecha hasta:
            	<input type="date" name="endDate" required>
            	
            	<button class="btnGenerar" > <i class="bi bi-arrow-counterclockwise"></i>   Limpiar</button>
        	</div>

    	</fieldset>
	</form>

    <div class="tablaMovimientoContainer ">

        <table id="table_id" class="display">
            <thead>
                <tr>
                    <th>Fecha</th>
                    <th>Concepto</th>
                    <th>Tipo</th>
                    <th>Importe</th>
                    <th>Comprobante</th>
                </tr>
            	</thead>
           	 	<tbody>
                	<tr>
                    	<td data-order="2024-12-15">15/12/2024</td>
                    	<td>Transferencia recibida de Juan Perez</td>
                    	<td><span class="badge ingreso">Ingreso</span></td>
                    	<td data-order="25000.00">+$25,000.00</td>
                    	<td><input type="submit" name="btnComprobante" value = "Comprobante"  /></td>
                	</tr>
                	<tr>
                    	<td data-order="2024-12-13">13/12/2024</td>
                    	<td>Deposito en efectivo</td>
                    	<td><span class="badge ingreso">Ingreso</span></td>
                    	<td data-order="15000.00">+$15,000.00</td>
                    	<td></td>
                	</tr>
                	<tr>
                    	<td data-order="2024-12-12">12/12/2024</td>
                    	<td>Compra en comercio - SUPERMERCADO PLAZA VEA</td>
                    	<td><span class="badge egreso">Egreso</span></td>
                    	<td data-order="12350.75">-$12,350.75</td>
                    	<td></td>
                	</tr>
               		<tr>
                    	<td data-order= "10-01-2025">10/01/2025</td>
                    	<td>Trasferencia a Martin Galmarini</td>
                    	<td><span class="badge egreso">Egreso</span></td>
                    	<td data-order="1000.00">-$1,000.00</td>
                    	<td></td>
                	</tr>
                	
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
	    $('#table_id').DataTable({
	        "language": {
	            "url": "//cdn.datatables.net/plug-ins/1.10.25/i18n/Spanish.json"
	        },
	        "columnDefs": [
	            { "type": "date-eu", "targets": 0 },
	            { "type": "currency", "targets": 3 },
	        ],
	        "order": [[0, "desc"]],
	        "responsive": true
	    });
	});
</script>

</body>
</html>