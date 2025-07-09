<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Dominio.Cliente" %>
<%@ page import="Dominio.Cuenta" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.DecimalFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cliente Dashboard</title>
<link rel="stylesheet" type="text/css" href="estilos/estilos.css">
<link rel="stylesheet" type="text/css" href="estilos/estilosCliente.css">

</head>
<%
String nombreUsuario= (String)session.getAttribute("usuario");
String tipo= (String)session.getAttribute("tipoUsuario");

Cliente cliente = (Cliente) request.getAttribute("cliente");
List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentas");
int indiceCuentaSeleccionada = (int) request.getAttribute("indiceCuentaSeleccionada");
int totalCuentas = (int) request.getAttribute("totalCuentas");

DecimalFormat df = new DecimalFormat("#,##0.00");
%>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f6f8;
        margin: 0;
        padding: 0;
    }

    .contenedor-bienvenida {
        max-width: 600px;
        margin: 80px auto;
        margin-bottom: 0;
        padding: 30px;
        text-align: center;
        background-color: #fff;
        border-radius: 12px;
        box-shadow: 0 4px 8px rgba(0,0,0,0.1);
    }

    .contenedor-bienvenida h2 {
        color: #2c3e50;
        margin-bottom: 10px;
    }

    .contenedor-bienvenida p {
        font-size: 18px;
        color: #555;
    }

    .contenedor-bienvenida strong {
        color: #2980b9;
        font-size: 20px;
    }


    .selector-cuentas {
        max-width: 600px;
        margin: 20px auto;
        padding: 20px;
        background-color: #fff;
        border-radius: 12px;
        box-shadow: 0 4px 8px rgba(0,0,0,0.1);
    }

    .selector-cuentas h3 {
        color: #2c3e50;
        margin-bottom: 15px;
        text-align: center;
    }

    .cuenta-select {
    	display:flex;
    	justify-content: center; 
    	align-items: center;
        width: 100%;
        padding: 12px;
        font-size: 16px;
        border: 2px solid #bdc3c7;
        border-radius: 8px;
        background-color: #fff;
        cursor: pointer;
        transition: border-color 0.3s ease;
    }


    .cuenta-select option {
        padding: 10px;
    }

    .cuenta-oculta {
        display: none;
    }

    .cuenta-visible {
        display: block;
        animation: fadeIn 0.3s ease-in;
    }


    .info-cuenta {
        margin-top: 10px;
        padding: 10px;
        background-color: #ecf0f1;
        border-radius: 6px;
        font-size: 14px;
        color: #7f8c8d;
    }
    
    .btn-seleccionar{
     	margin-top: 10px;
        background-color: #198754;
    	color: white;
    	border: none;
   		padding: 10px 20px;
    	border-radius: 8px;
    	font-size: 16px;
    	cursor: pointer;
    	transition: background-color 0.3s ease, transform 0.2s ease;
    	box-shadow: 0 4px 6px rgba(0,0,0,0.1);
    }
    
    .btn-seleccionar:hover{
    	background-color: #157347;
    	transition: .2s;
    }
    
    .modal-copiado {
    display: none;
    position: fixed;
    bottom: 30px;
    left: 50%;
    transform: translateX(-50%);
    background-color: #2ecc71;
    color: white;
    padding: 12px 24px;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0,0,0,0.2);
    font-size: 16px;
    z-index: 9999;
    animation: fadeinout 2s ease-in-out;
}

  
</style>

<body>

<div class="main-contanier">
<jsp:include page="MenuCliente.html"></jsp:include>

<header>
<div class="contenedor-bienvenida">
<h1>¡Bienvenido!</h1>
<p><strong><%= cliente.getApellido().toUpperCase() + ", " + cliente.getNombre().toUpperCase()%></strong></p>
</div>
</header>

<%
if (cuentas != null && !cuentas.isEmpty()) {
    if (cuentas.size() >= 1) {
%>
<div class="selector-cuentas">
    <form method="GET" action="ServletInicioCliente" class="form-selector">
        <select name="cuentaSeleccionada" class="cuenta-select">
            <option value="">-- Selecciona una cuenta --</option>
           <%for (int i = 0; i < cuentas.size(); i++) {
                Cuenta cuenta = cuentas.get(i);
                String selected = (indiceCuentaSeleccionada == i) ? "selected" : "";
            %>
            <option value="<%= i %>" <%= selected %>>
                Cuenta <%= cuenta.getId() %> - Saldo: $<%= df.format(cuenta.getSaldo()) %>
            </option>
            <%
            }
            %>
        </select>
        <button type="submit" class="btn-seleccionar">Ver Cuenta</button>
    </form>
    <div class="info-cuenta">
        Tienes <%= cuentas.size() %> cuentas disponibles.
    </div>
    <section class="cuenta-section">
<%
    if (indiceCuentaSeleccionada >= 0 && indiceCuentaSeleccionada < cuentas.size()) {
        Cuenta cuentaAMostrar = cuentas.get(indiceCuentaSeleccionada);
        String claseSeleccionada = (totalCuentas > 1) ? "cuenta-seleccionada" : "";
%>
    <div class="cuenta-container <%= claseSeleccionada %>">
        <div class="cuenta-body">
            <% if (totalCuentas > 1) { %>
            <div class="cuenta-info-destacada">
                <h3 style="margin: 0; font-size: 18px;">
                     <%= cuentaAMostrar.getTipo().getDescripcion() %>: <%= cuentaAMostrar.getId() %>
                </h3>
            </div>
            <% } else { %>
            <h3 style="text-align: center; color: #2c3e50; margin-bottom: 20px;">
                Detalles de tu Cuenta
            </h3>
            <% } %>
            <br>
            <div class="info-item">
                <div class="info-label">Número de Cuenta</div>
                <div class="info-value">
                    CTA - <%= cuentaAMostrar.getId() %>
                    <button class="copy-btn" onclick="copiarTexto('<%= cuentaAMostrar.getId() %>')">Copiar</button>
                </div>
            </div>

            <div class="info-item">
                <div class="info-label">CBU</div>
                <div class="info-value">
                    <%= cuentaAMostrar.getCBU() %>
                    <button class="copy-btn" onclick="copiarTexto('<%= cuentaAMostrar.getCBU() %>')">Copiar</button>
                </div>
            </div>

            <div class="info-item">
                <div class="info-label">Saldo Disponible</div>
                <div class="saldo-value">
                    <span class="currency">$</span><%= df.format(cuentaAMostrar.getSaldo()) %>
                </div>
            </div>
        </div>
    </div>
<%
    }
%>
</section>
</div>
<%
    }
%>
<%
}
%>
</div>
<footer>
<jsp:include page="Footer.html"></jsp:include>
</footer>
<div id="modal-copiado" class="modal-copiado">
    <p>Texto copiado</p>
</div>
<script>
function copiarTexto(texto) {
    const input = document.createElement("input");
    input.setAttribute("value", texto);
    document.body.appendChild(input);
    input.select();
    document.execCommand("copy");
    document.body.removeChild(input);

    mostrarModalCopiado();
}
function mostrarModalCopiado() {
    const modal = document.getElementById("modal-copiado");
    modal.style.display = "block";

    setTimeout(() => {
        modal.style.display = "none";
    }, 2500);
}
</script>

</body>
</html>