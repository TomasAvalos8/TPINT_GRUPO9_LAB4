<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/StyleSheet.css">
<link rel="stylesheet" type="text/css" href="estilos/estilos.css">
</head>
<body>
<jsp:include page="MenuAdmin.html"></jsp:include>

<div class="contenedorFormularios">

<div class="formulariosWrapper">

<div class="parteDer">
    <h2>Formulario de Cliente</h2>

    <form class="formCliente" method="post" action="ServletCliente">
        <fieldset>
            <p>
                DNI: <input type="number" name="dni" required>
                CUIL: <input type="number" name="cuil" required>
            </p>
            <p>
                Nombre: <input type="text" name="nombre" required>
                Apellido: <input type="text" name="apellido" required>
            </p>
            <p>
                Sexo:
                <select name="sexo" required>
                    <option value="">Seleccione</option>
                    <option value="masculino">Masculino</option>
                    <option value="femenino">Femenino</option>
                </select>
                Nacionalidad: <input type="text" name="nacionalidad" required>
            </p>
            <p>
                Fecha de nacimiento: <input type="date" name="fechaNacimiento" required>
                Dirección: <input type="text" name="direccion" required>
            </p>
            <p>
                ID Localidad:
                <select name="idLocalidad" required>
                    <option value="">Seleccione</option>
                </select>
                ID Provincia:
                <select name="idProvincia" required>
                    <option value="">Seleccione</option>
                </select>
            </p>
            <p>
                Correo electrónico: <input type="email" name="email" required>
                Teléfono: <input type="number" name="telefono" required>
            </p>           
        </fieldset>
    </form>
</div>

<div class="parteDer">
    <h2>Formulario Usuario</h2>

    <form class="formUsuario" method="post" action="ServletUsuario">
        <fieldset>
            <p>
  				ID de usuario:
  				<input type="number" name="numUsuario" readonly value="1">
			</p>
            <p>
                Tipo de usuario:
                <select name="tipoUsuario" required>
                    <option value="">Seleccione</option>
                </select>
            </p>
            <p>
                Usuario: <input type="text" name="usuario" required>
            </p>
            <p>
                Contraseña: <input type="password" name="password" required>
            </p>
            <p>
                Repetir contraseña: <input type="password" name="passwordConfirm" required>
            </p>

            <p>
                Fecha de alta: <input type="date" name="fechaAlta" required>
            </p>
         
        </fieldset>
    </form>
</div>

</div>

<div class="botonContainer">
  <button class="btnRegistrar">Registrar</button>
</div>

<div class="formulariosWrapper listadoContainer">
<h2>Listado de Clientes</h2>
<div class="tablaCuentasContainer">
<div class="filtrosContainer">
 <p> <b>Busqueda:</b> <input type="text" name="search">  
 <input type="submit" value="Buscar" class="btnBuscar">
 </p>
 <p>
   
     <p> DNI: <input type="text" name="idCliente">  </p>
     <p><input type="submit" value="Filtrar" class="btnFiltrar"></p>
     </p>
</div>

	<table>
        <thead>
            <tr>
                <th>DNI</th>
                <th>Nombre y Apellido</th>
                <th>Sexo y Nacionalidad</th>
                <th>Fecha de nacimiento</th>
            </tr>
        </thead>
        <tbody >
             <tr>
                 <td>123421555</td>
                 <td>Pedro gonzales</td>
                 <td>Masculino/ Argentino</td>
                 <td>17/20/1990</td>
             </tr>
             <tr>
                 <td>42652311</td>
                 <td>Pechito Martinez perez</td>
                 <td>No binario/ Uruguayo</td>
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


</div>



</body>
</html>