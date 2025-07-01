<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    }
</style>

<body>

<div class = "main-contanier">
<jsp:include page="MenuCliente.html"></jsp:include>

<header>
<div class="contenedor-bienvenida">
<h1>¡Bienvenido, <%= nombreUsuario%>!</h1>
<p>Estas logueado como: <strong><%= tipo %></strong></p>
</div>
</header>
<section class = "cuenta-section">
 <div class="cuenta-container">
        <div class="cuenta-body">
            <div class="info-item">
                <div class="info-label">Número de Cuenta</div>
                <div class="info-value">
                    0123-456789-01
                    <button class="copy-btn" >Copiar</button>
                </div>
            </div>
            
            <div class="info-item">
                <div class="info-label">CBU</div>
                <div class="info-value">
                    0170123420000045678901
                    <button class="copy-btn" >Copiar</button>
                </div>
            </div>          
            <div class="info-item">
                <div class="info-label">Saldo Disponible</div>
                <div class="saldo-value"> 
                    <span class="currency">$</span>125,750.50
                </div>
            </div>
        </div>
    </div>
</section>
</div>

<footer>
<jsp:include page="Footer.html"></jsp:include>
</footer>
</body>
</html>