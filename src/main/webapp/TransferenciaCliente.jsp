<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Banco UTN</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="estilos/estilos.css">
<link rel="stylesheet" type="text/css" href="estilos/estilosTransferencia.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    
</head>
<style>
body{
    font-family: Arial, sans-serif;
        font-size: 16px;
    line-height: 1.5;
    }
 .datos-personales-btn {
    
    background-color: #e0e0e0;
    border: none;
    padding: 8px 15px;
    border-radius: 20px;
    cursor: pointer;
    font-size: 14px;
    color: #333;
    transition: all 0.3s ease;
    box-shadow: 0 2px 5px rgba(0,0,0,0.1);
    display: inline-flex;
    align-items: center;
    gap: 8px;
    
   
    font-family: 'Segoe UI', Arial, sans-serif;
    font-weight: 500;
    letter-spacing: 0.5px;
    
   
    border: 1px solid transparent;
  }

  .datos-personales-btn:hover {
    background-color: #d0d0d0;
    transform: translateY(-1px);
    box-shadow: 0 3px 8px rgba(0,0,0,0.15);
    border-color: #b0b0b0;
  }

  .datos-personales-btn:active {
    transform: translateY(0);
    background-color: #c0c0c0;
  }

  .datos-personales-btn i {
    font-size: 16px;
    color: #555;
  }
</style>
<%@ page import="Dominio.Cuenta" %>
<%@ page import="java.util.List" %>
<%
String tipo= (String)session.getAttribute("tipoUsuario");
Integer tipoUsuarioId = (Integer) session.getAttribute("tipoUsuarioId");
if (tipoUsuarioId == null || tipoUsuarioId == 0) {
    response.sendRedirect("Inicio.jsp");
    return;
}
if (tipoUsuarioId != 2) {
    response.sendRedirect("InicioAdmin.jsp");
    return;
}
%>
<body>

<jsp:include page="MenuCliente.html"></jsp:include>

	<% String usuario = (String)session.getAttribute("usuario"); %>
	 <p class="userLoguedText">
  <button class="datos-personales-btn" onclick="location='DatosPersonales.jsp'">
    <i class="fas fa-user"></i> <%= usuario != null ? usuario : "Usuario no identificado" %>
  </button>
</p>
	<section class = "container">
		<div class = "trans-card card" >
			<form action="TransferenciaServlet" method="post">
				<div class = "header">
					<h4 class = "card-title mb-0">
						<i class="bi bi-arrow-left-right me-2"></i>
                    	Transferir Dinero
					</h4>
					<p class="card-description">Transfiere dinero entre tus cuentas o a cuentas de terceros</p>
				</div>	
				<div class="mb-3">
				
                       <label for="sourceAccount" class="form-label">Cuenta de Origen</label>
                       <select class="form-select" id="selectCuenta" name="cuentaorigen" required>
					    <option value="">Selecciona tu cuenta de origen</option>
					    <% 
					    	List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentas");
					        if (cuentas != null) {
					            for (Cuenta c : cuentas) {
					    %>
					        <option value="<%= c.getCBU() %>">
					            <%= c.getTipo().getDescripcion() %> - CBU <%= c.getCBU() %> ($<%= String.format("%.2f", c.getSaldo()) %>)
					        </option>
					    <%
					            }
					        }
					    %>
						</select>
                        
                        <div id="saldoInfo" class="saldo-info d-none">
                        <i class="bi bi-info-circle me-2"></i>
                        <span id="balanceText">Saldo disponible: <strong>$0</strong></span>
                    </div>
                    <hr>
                    <!--
                     <div class="mb-3">
                        <label class="form-label">Tipo de Transferencia</label>
                        <div class=" trans-tipo">
                            <div class="col-md-6">
                                <label class="transfer-option active" for="transferOwn">
                                    <input type="radio" id="transferOwn" name="transferType" value="own" checked>
                                    <div>
                                        <i class="bi bi-credit-card fs-3 mb-2 d-block"></i>
                                        <div class="fw-bold">Entre mis cuentas</div>
                                        <p class="text-muted">Transferencia propia</p>
                                    </div>
                                </label>
                            </div>
                            <div class="col-md-6">
                                <label class="transfer-option" for="transferExternal">
                                    <input type="radio" id="transferExternal" name="transferType" value="external">
                                    <div>
                                        <i class="bi bi-person fs-3 mb-2 d-block"></i>
                                        <div class="fw-bold">A terceros</div>
                                        <p class="text-muted">Usando CBU</p>
                                    </div>
                                </label>
                            </div>
                        </div>
                    </div>
                    -->
                    <div class="mb-3">
                        <label for="cuentadestino" class="form-label">Cuenta de Destino</label>
                        <input type="text" inputmode="numeric" pattern="\d+" maxlength="30" class="form-control" id="cuentadestino" name="cuentadestino"
       					placeholder="Ingrese el CBU de destino" required title="Solo se permiten números (sin espacios ni símbolos)">
                    </div>
                    <div class="mb-3">
                        <label for="cantidad" class="form-label">Importe</label>
                        <div class="input-group">
                            <span class="input-group-text">$</span>
                            <input type="number" class="form-control " id="cantidad" name="cantidad" 
                                   placeholder="0.00" min="1" step="0.01" required>                         
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="concepto" class="form-label">Concepto</label>
                        <input type="text" class="form-control" id="concepto" name="concepto" 
                               placeholder="Motivo de la transferencia" maxlength="50" required>
                    </div>
                </div>
                <div class="d-grid gap-2 d-md-flex justify-content-md-end mt-4">
                        <button type="button" class="btn btn-outline-secondary me-md-2" id="cancelarBtn">
                            <i class="bi bi-x-lg me-1"></i>
                            Cancelar
                        </button>
                        <button type="submit" class="btn btn-success " id="submitBtn">
                            <span class="btn-text">
                                <i class="bi bi-check-lg me-1"></i>
                                Confirmar Transferencia
                            </span>
                        </button>
                    </div>
			</form>
		</div>
	</section>


<jsp:include page="Footer.html"></jsp:include>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<% if (request.getAttribute("mensaje") != null) { %>
    <script>
        alert("<%= request.getAttribute("mensaje").toString().replace("\"", "\\\"") %>");
    </script>
<% } %>



</body>
</html>