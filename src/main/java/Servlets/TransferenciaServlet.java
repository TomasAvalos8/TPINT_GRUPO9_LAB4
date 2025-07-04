package Servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dominio.Cuenta;
import Negocio.TransferenciaNeg;
import NegocioImpl.TransferenciaNegImpl;

/**
 * Servlet implementation class TransferenciaServlet
 */
@WebServlet("/TransferenciaServlet")
public class TransferenciaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransferenciaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			
	        // Obtener parámetros según nombres del JSP
	        int idCuentaSaliente = Integer.parseInt(request.getParameter("cuentaorigen"));
	        int idCuentaDestino = Integer.parseInt(request.getParameter("cuentadestino"));
	        float monto = Float.parseFloat(request.getParameter("cantidad"));

	        // Fecha actual del sistema
	        java.util.Date fechaUtil = new java.util.Date();
	        java.sql.Date fecha = new java.sql.Date(fechaUtil.getTime());

	        
	        // Crear objetos Cuenta
	        Cuenta cuentaSaliente = new Cuenta();
	        cuentaSaliente.setId(idCuentaSaliente);
	        Cuenta cuentaDestino = new Cuenta();
	        cuentaDestino.setId(idCuentaDestino);

	        // Llamar al método Transferir
	        TransferenciaNeg negocio = new TransferenciaNegImpl();
	        boolean exito = negocio.transferirCuenta(cuentaSaliente, cuentaDestino, monto, fecha);

	        // Resultado y redirección
	        if (exito) {
	            request.setAttribute("mensaje", "Transferencia realizada con éxito.");
	        } else {
	            request.setAttribute("mensaje", "Error: saldo insuficiente o error en la transferencia.");
	        }
	        request.getRequestDispatcher("TransferenciaCliente.jsp").forward(request, response);

	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("mensaje", "Error en la operación: " + e.getMessage());
	        request.getRequestDispatcher("TransferenciaCliente.jsp").forward(request, response);
	    }
	}

}
