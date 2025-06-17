<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Creacion de cuentas</title>
<link rel="stylesheet" type="text/css" href="css/StyleSheet.css">
<link rel="stylesheet" type="text/css" href="estilos/estilos.css">
</head>
<body>
<jsp:include page="MenuAdmin.html"></jsp:include>


<p class="userLoguedText">usuario logueado </p>
<div class="contenedorFormularios">

<div class="formulariosWrapper">

<div class="parteDer">
    <h2>Formulario de Cuentas</h2>

    <form class="formCliente" method="post" action="ServletCuenta">
        <fieldset>
         <p>
             Tipo de cuenta:
             <select name="tipoCuenta" required>
                 <option value="">Seleccione</option>
                 <option value="cajaAhorro">Caja de ahorro</option>
                 <option value="cuentaCorriente">Cuenta corriente</option>
             </select>
              ID Cliente: <input type="number" name="dni" required>
            
         </p>
         

        </fieldset>
    </form>
</div>


</div>
<div class="botonContainer">
  <button class="btnRegistrar">Registrar</button>
</div>

<div class="formulariosWrapper listadoContainer">
<h2>Listado de Cuentas</h2>
<div class="tablaCuentasContainer">
<div class="filtrosContainer">
 <p> <b>Busqueda:</b> <input type="text" name="search">  
 <input type="submit" value="Buscar" class="btnBuscar">
 </p>
 <p> <b>Filtrar:</b>
 
 	Tipo de cuenta:
     <select name="tipoCuentaFiltro">
         <option value="">Seleccione</option>
         <option value="cajaAhorro">Caja de ahorro</option>
         <option value="cuentaCorriente">Cuenta corriente</option>
     </select>
     
     <p> ID Cliente: <input type="text" name="idCliente">  </p>
     <p><input type="submit" value="Filtrar" class="btnFiltrar"></p>
     </p>
</div>

	<table>
        <thead>
            <tr>
                <th>ID Cuenta</th>
                <th>Tipo de Cuenta</th>
                <th>ID Cliente</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody >
             <tr>
                 <td>123</td>
                 <td>Cuenta corriente</td>
                 <td>343243</td>
                 <td><button class="btnEliminar">Eliminar</button></td>
             </tr>
             <tr>
                 <td>222</td>
                 <td>Caja de ahorro</td>
                 <td>2222</td>
                 <td><button class="btnEliminar">Eliminar</button></td>
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