<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Creacion de cuentas</title>
<link rel="stylesheet" type="text/css" href="estilos/estilos.css">
</head>
<body>
<jsp:include page="MenuAdmin.html"></jsp:include>

<%
    Dominio.Cuenta cuentaModificar = (Dominio.Cuenta) request.getAttribute("cuentaModificar");
%>

<p class="userLoguedText">usuario logueado </p>
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
                <input type="number" name="cbu" id="cbu" required value="<%= cuentaModificar != null ? cuentaModificar.getCBU() : "" %>" />
            </p>

            <p>

                <label for="fecha">Fecha de Creación:</label>
                <input type="date" name="fecha" id="fecha" required value="<%= cuentaModificar != null && cuentaModificar.getCreacion() != null ? new java.text.SimpleDateFormat("yyyy-MM-dd").format(cuentaModificar.getCreacion()) : "" %>" />

                <label for="tipoCuenta">Tipo de cuenta:</label>
                <select name="tipoCuenta" id="tipoCuenta" required>
                    <option value="">Seleccione</option>
                    <% java.util.List<Dominio.TipoCuenta> tiposCuenta = (java.util.List<Dominio.TipoCuenta>) request.getAttribute("tiposCuenta");
                       if (tiposCuenta != null) {
                           for (Dominio.TipoCuenta tipo : tiposCuenta) { %>
                               <option value="<%= tipo.getIdTipoCuenta() %>" <%= cuentaModificar != null && cuentaModificar.getTipo() != null && cuentaModificar.getTipo().getIdTipoCuenta() == tipo.getIdTipoCuenta() ? "selected" : "" %>><%= tipo.getDescripcion() %></option>
                    <%     }
                       }
                    %>
                </select>

                <label for="saldo">Saldo inicial:</label>
                <input type="number" name="saldo" id="saldo" step="0.01" required value="<%= cuentaModificar != null ? cuentaModificar.getSaldo() : "10000" %>" />
            </p>



        </fieldset>

        <% if (request.getAttribute("mensajeServlet") != null) { %>
            <div class="mensajeServlet" style="color: black; font-weight:bold; margin: 10px 0;">
                <%= request.getAttribute("mensajeServlet") %>
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
        <% 
            java.util.List<Dominio.Cuenta> listaCuentas = (java.util.List<Dominio.Cuenta>) request.getAttribute("listaCuentas");
            if (listaCuentas != null) {
                for (Dominio.Cuenta cuenta : listaCuentas) { 
        %>
            <tr>
                <td><%= cuenta.getId() %></td>
                <td><%= cuenta.getTipo() != null ? cuenta.getTipo().getDescripcion() : "" %></td>
                <td><%= cuenta.getDni() %></td>
                <td>
                    <form   class="boton"  method="post" action="CuentasAdminServlet" style="display:inline;">
                        <input type="hidden" name="eliminarId" value="<%= cuenta.getId() %>" />
                        <button class="btnEliminar" type="submit" onclick="return confirm('¿Estas seguro que queres eliminar esta cuenta?');">Eliminar</button>
                    </form>
                    <form class="boton"  method="post" action="CuentasAdminServlet" style="display:inline;">
                        <input type="hidden" name="modificarId" value="<%= cuenta.getId() %>" />
                        <button class="btnModificar" type="submit">Modificar</button>
                    </form>
                </td>
            </tr>
        <%      }
            }
        %>
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