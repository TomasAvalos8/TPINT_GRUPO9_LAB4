<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Aprobacion de prestamos</title>
<link rel="stylesheet" type="text/css" href="css/StyleSheet.css">
<link rel="stylesheet" type="text/css" href="estilos/estilos.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
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
<div class="filtrosContainer">
 <p> <b>Busqueda:</b> <input type="text" name="search">  </p>
 <p><input type="submit" value="Buscar" class="btnBuscar"></p>
 <p> <b>Filtrar:</b> 
     
     <p> ID Cliente: <input type="text" name="idCliente">  </p>
     <p> ID Cuenta a depositar: <input type="text" name="idCuenta">  </p>
     <p><input type="submit" value="Filtrar" class="btnFiltrar"></p>
     
</div>

	<table>
        <thead>
            <tr>
                <th>ID Cliente</th>
                <th>Dinero solicitado</th>
                <th>Cuotas solicitadas</th>
                <th>ID Cuenta a depositar</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody >
             <tr>
                 <td>123</td>
                 <td>$ 1.000.000</td>
                 <td>12</td>
                 <td>00435</td>
                 <td><button class="btnAprobar">Aprobar</button> <button class="btnRechazar">Rechazar</button></td>
                 
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
</div>








</body>
</html>