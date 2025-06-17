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
<body>
<div class = "main-contanier">
<jsp:include page="MenuCliente.html"></jsp:include>
<header>
    <h1>Bienvenido al Banco UTN</h1>
    <div class="user-info">
       <h4>¡Hola!, Nombre Usuario</h4>
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

<jsp:include page="Footer.html"></jsp:include>

</div>
</body>
</html>