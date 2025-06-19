<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mis Prestamos</title>
<link rel="stylesheet" type="text/css" href="estilos/estilos.css">
</head>
<body>
<jsp:include page="MenuCliente.html"></jsp:include>

<p class="userLoguedText">Usuario Logueado </p>
<div class="contenedorFormularios">

<div class="formulariosWrapper listadoContainer">
<h2>Mis Prestamos</h2>

<div>
	<table>
	<thead>
	<tr>
	<th>Número de cuota</th>
	<th>Importe</th>
	<th>Vencimiento</th>
	<th>	</th>
	</tr>
	</thead>
	<tbody>
	
	<tr>
	<td>10/12</td>
	<td>$200.000</td>
	<td>05/07/2025</td>
	<td><input type="submit" class="btnPagar" value="Pagar"></td>
	</tr>
	
	<tr>
	<td>11/12</td>
	<td>$200.000</td>
	<td>05/08/2025</td>
	<td><input type="submit" class="btnPagar" value="Pagar"></td>
	</tr>
	
	<tr>
	<td>12/12</td>
	<td>$200.000</td>
	<td>05/09/2025</td>
	<td><input type="submit" class="btnPagar" value="Pagar"></td>
	</tr>
	
	</tbody>
	</table>
</div>
</div>




</div>

<jsp:include page="Footer.html"></jsp:include>
</body>
</html>