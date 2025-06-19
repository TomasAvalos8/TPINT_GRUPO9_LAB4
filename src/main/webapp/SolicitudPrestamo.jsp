<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Solicitud de Préstamo</title>
<style>
.Titulo {
    padding-top: 20px;
    text-align: center;
    margin: 20px 0;
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
    <h1 class="Titulo">Solicitud de préstamo</h1>
    
    <div class="ContenedorPrestamo">
        <form action="procesarPrestamo.jsp" method="post">
            <h3>Ingrese el monto</h3>
            <input type="number" name="monto" placeholder="Ej: 10000" min="1" step="0.01" required>
            
            <h3>¿En cuántas cuotas?</h3>
            <div class="cuotas-container">
                <div class="cuota-select">
                    <select name="cuotas" required>
                        <option value="" disabled selected>Seleccione cuotas</option>
                        <option value="3">3 cuotas</option>
                        <option value="6">6 cuotas</option>
                        <option value="12">12 cuotas</option>
                        <option value="24">24 cuotas</option>
                    </select>
                </div>
                <div class="info-box">
                    15% interés
                </div>
            </div>
            
            <h2>Total en cuotas</h2>
            <div class="total-cuotas-container">
                <div class="total-cuotas-amount">
                    $479.17/mes
                </div>
                <div class="info-box">
                    24 cuotas
                </div>
            </div>
            
            <h2>Total a pagar</h2>
            <div class="total-container">
                <span class="total-label">Total:</span>
                <span class="total-amount">$11,500.00</span>
            </div>
            
            <div class="DiasPagar">
                <h3>Días a pagar</h3>
                <div class="Dias-select">
                    <select name="diaPago" required>
                        <option value="" disabled selected>Seleccione día a pagar</option>
                        <option value="UltimoDiaMes">Último día del mes</option>
                        <option value="PrimerDiaDelMes">Primer día del mes</option>
                    </select>
                </div>
            </div>
            
            <button type="submit" class="btn-solicitar">Solicitar Préstamo</button>
        </form>
    </div>
    
    <footer>
        <jsp:include page="Footer.html"></jsp:include>
    </footer>
</body>
</html>