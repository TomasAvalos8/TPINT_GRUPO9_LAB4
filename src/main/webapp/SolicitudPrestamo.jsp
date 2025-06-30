<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Solicitud de Préstamo</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<link rel="stylesheet" type="text/css" href="estilos/estilos.css">
<style>
.Titulo {
    padding-top: 20px;
    text-align: center;
    margin: 20px 0;
}
body{
margin:0;
padding:0;
font-family: Arial, sans-serif;
position: relative;
}
.ContenedorPrestamo {
    width: 500px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    background-color: #f9f9f9;
}

.ContenedorPrestamo h3 {
    text-align: center;
    margin-bottom: 15px;
}

.ContenedorPrestamo h2 {
    text-align: center;
    margin-bottom: 15px;
}

.ContenedorPrestamo input[type="number"] {
    width: 100%;
    padding: 8px;
    margin-bottom: 10px;
    border: 1px solid #ddd;
    border-radius: 4px;
    box-sizing: border-box;
}

.cuotas-container, .total-cuotas-container {
    display: flex;
    align-items: center;
    gap: 10px;
    margin-bottom: 15px;
}

.cuota-select, .Dias-select {
    flex: 1;
}

.cuota-select select, .Dias-select select {
    width: 100%;
    padding: 8px;
    border: 1px solid #ddd;
    border-radius: 4px;
    appearance: none;
    -webkit-appearance: none;
    -moz-appearance: none;
    background-image: url("data:image/svg+xml;charset=UTF-8,%3csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' fill='none' stroke='currentColor' stroke-width='2' stroke-linecap='round' stroke-linejoin='round'%3e%3cpolyline points='6 9 12 15 18 9'%3e%3c/polyline%3e%3c/svg%3e");
    background-repeat: no-repeat;
    background-position: right 10px center;
    background-size: 15px;
}

.info-box {
    padding: 8px 12px;
    background-color: #e9f7ef;
    border: 1px solid #d4edda;
    border-radius: 4px;
    color: #155724;
    font-weight: bold;
    min-width: 100px;
    text-align: center;
}

.total-container {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-top: 20px;
    padding: 15px;
    background-color: #f0f8ff;
    border: 1px solid #cce5ff;
    border-radius: 4px;
}

.total-cuotas-container {
    margin-top: 10px;
}

.total-cuotas-amount {
    flex: 1;
    padding: 8px 12px;
    background-color: #fff3cd;
    border: 1px solid #ffeeba;
    border-radius: 4px;
    color: #856404;
    font-weight: bold;
}

.total-label {
    font-weight: bold;
}

.total-amount {
    font-size: 1.3em;
    font-weight: bold;
    color: #004085;
}

.btn-solicitar {
    width: 100%;
    padding: 12px;
    margin-top: 20px;
    background-color: #28a745;
    color: white;
    border: none;
    border-radius: 4px;
    font-size: 1.1em;
    font-weight: bold;
    cursor: pointer;
    transition: background-color 0.3s;
}

.btn-solicitar:hover {
    background-color: #218838;
}

.DiasPagar {
    margin-top: 20px;
}


</style>
</head>

<body>
    <jsp:include page="MenuCliente.html"></jsp:include>
    <% String usuario = (String)session.getAttribute("usuario"); %>
    <p class="userLoguedText">
      <i class="fas fa-user"></i> <%= usuario %>
    </p>
    <h1 class="Titulo">Solicitud de préstamo</h1>
    
    <div class="ContenedorPrestamo">
        <form action="SolicitudPrestamoServlet" method="post">
            <h3>Ingrese el monto</h3>
            <input type="number" name="importe_solicitado" placeholder="Ej: 10000" min="1" step="0.01" required value="<%= request.getParameter("importe_solicitado") != null ? request.getParameter("importe_solicitado") : "" %>">
            
            <h3>¿En cuántas cuotas?</h3>
            <div class="cuotas-container">
                <div class="cuota-select">
                    <select name="cuotas" required>
                        <option value="" disabled <%= request.getParameter("cuotas") == null ? "selected" : "" %>>Seleccione cuotas</option>
                        <option value="6" <%= "6".equals(request.getParameter("cuotas")) ? "selected" : "" %>>6 cuotas</option>
                        <option value="12" <%= "12".equals(request.getParameter("cuotas")) ? "selected" : "" %>>12 cuotas</option>
                        <option value="18" <%= "18".equals(request.getParameter("cuotas")) ? "selected" : "" %>>18 cuotas</option>
                        <option value="24" <%= "24".equals(request.getParameter("cuotas")) ? "selected" : "" %>>24 cuotas</option>
                        <option value="30" <%= "30".equals(request.getParameter("cuotas")) ? "selected" : "" %>>30 cuotas</option>
                        <option value="36" <%= "36".equals(request.getParameter("cuotas")) ? "selected" : "" %>>36 cuotas</option>
                        <option value="42" <%= "42".equals(request.getParameter("cuotas")) ? "selected" : "" %>>42 cuotas</option>
                    </select>
                </div>
                <div class="info-box" id="interes-info">
                    <%= request.getAttribute("interes") != null ? request.getAttribute("interes") : "0% interés" %>
                </div>
            </div>
            
            <button type="submit" class="btn-solicitar" name="accion" value="calcular">Calcular Total</button>
            
            <h2>Total en cuotas</h2>
            <div class="total-cuotas-container">
                <div class="total-cuotas-amount">
                    <%= request.getAttribute("cuotaMensual") != null ? request.getAttribute("cuotaMensual") : "" %>
                </div>
                <div class="info-box">
                    <%= request.getParameter("cuotas") != null ? request.getParameter("cuotas") + " cuotas" : "" %>
                </div>
            </div>
            
            <h2>Total a pagar</h2>
            <div class="total-container">
                <span class="total-label">Total:</span>
                <span class="total-amount">
                    <%= request.getAttribute("totalPagar") != null ? request.getAttribute("totalPagar") : "" %>
                </span>
            </div>
            
            <button type="submit" class="btn-solicitar">Solicitar Préstamo</button>
        </form>
    </div>
    
    <footer>
        <jsp:include page="Footer.html"></jsp:include>
    </footer>

    <script>
        const cuotasSelect = document.querySelector('select[name="cuotas"]');
        const interesInfo = document.getElementById('interes-info');
        function getInteresText(cuotas) {
            switch (cuotas) {
                case '6': return '5% interés';
                case '12': return '10% interés';
                case '18': return '15% interés';
                case '24': return '20% interés';
                case '30': return '25% interés';
                case '36': return '30% interés';
                case '42': return '35% interés';
                default: return '';
            }
        }
        cuotasSelect.addEventListener('change', function() {
            interesInfo.textContent = getInteresText(this.value);
        });
    </script>
</body>
</html>