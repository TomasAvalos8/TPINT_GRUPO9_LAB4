<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/StyleSheet.css">
<link rel="stylesheet" type="text/css" href="estilos/estilos.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
<jsp:include page="MenuAdmin.html"></jsp:include>

<div class="estadisticas"></div>
<% String usuario = (String)session.getAttribute("usuario"); %>
<p class="userLoguedText">
  <i class="fas fa-user"></i> <%= usuario %>
</p>
<form class="formInformes" method="post" action="ServletInformes">
    <fieldset>
        <p>
            Fecha de inicio:
            <input type="date" name="startDate" required>
            Fecha de fin:
            <input type="date" name="endDate" required>
        </p>
        <p>
            <div class="botonContainer">
  			<button class="btnGenerar">Generar Informe</button>
			</div>
			
    </fieldset>
</form>

<div class="formulariosWrapper listadoContainer">

<h2>Listado de Informes</h2>
<div class="tablaCuentasContainer">
	<table>
        <thead>
            <tr>
                <th>Monto</th>
                <th>Cuentas</th>
                <th>Movimientos</th>
                <th>Prestamos</th>
            </tr>
        </thead>
        <tbody >
             <tr>
                 <td>$1.000.000</td>
                 <td>10</td>
                 <td>300</td>
                 <td>20</td>
             </tr>
             <tr>
                 <td>$20.000.000</td>
                 <td>50</td>
                 <td>100</td>
                 <td>60</td>
             </tr>         
        </tbody>
          <tfoot>
            <tr>
                <td colspan="5">
                    <div class="paginado">
                    <button class="btnAnterior"><</button>
                        <span> Pagina 1 de 10 </span>
                        <button class="btnSiguiente">></button>
                    </div>
                </td>
            </tr>
          </tfoot>
        </table>
        </div>
</div>

</body>
</html>