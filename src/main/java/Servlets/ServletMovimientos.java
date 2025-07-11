package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import Dominio.Cliente;
import Dominio.Cuenta;
import Dominio.Movimiento;
import Negocio.MovimientoNeg;
import NegocioImpl.MovimientoNegImpl;

/**
 * Servlet implementation class ServletMovimientos
 */
@WebServlet("/ServletMovimientos")
public class ServletMovimientos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MovimientoNeg movimiento = new MovimientoNegImpl();
	
       
    
    public ServletMovimientos() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	Cliente cliente = (Cliente)request.getSession().getAttribute("clienteLogueado");
	    if (cliente == null) {
	        response.sendRedirect("Inicio.jsp");
	        return;
	    }
	    List<Cuenta>cuentas=(List<Cuenta>)request.getSession().getAttribute("cuentas");
	    if(cuentas!=null)request.setAttribute("cuentas", cuentas);
	    
	    
	    request.getRequestDispatcher("HistorialMovimientosCliente.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  String nroCuentaParam = request.getParameter("numeroCuenta");
		  String fechaDesde = request.getParameter("fechaDesde");
		    String fechaHasta = request.getParameter("fechaHasta");
		    
		    if (nroCuentaParam != null && !nroCuentaParam.isEmpty()) {
		    	int nroCuenta = Integer.parseInt(nroCuentaParam);
		    	
		    	List<Movimiento> ListaMovimientos;	
		    	
		    	if (request.getParameter("btnLimpiar") != null) {
		            ListaMovimientos = movimiento.listarMovimientosPorCuenta(nroCuenta);
		            fechaDesde = "";
		            fechaHasta = "";
		        }
		    	
		    	if (request.getParameter("btnFiltrar") != null && fechaDesde != null && fechaHasta != null &&
		                !fechaDesde.isEmpty() && !fechaHasta.isEmpty()) {
		                ListaMovimientos = movimiento.listarMovimientosPorCuentaYFechas(nroCuenta, fechaDesde, fechaHasta);
		            } else {
		                
		                ListaMovimientos = movimiento.listarMovimientosPorCuenta(nroCuenta);
		            }
		    	
		        request.setAttribute("ListaMovimientos", ListaMovimientos);
		        request.setAttribute("cuentaSeleccionada", nroCuentaParam);
		        request.setAttribute("fechaDesde", fechaDesde);
		        request.setAttribute("fechaHasta", fechaHasta);

		    }
		    List<Cuenta>cuentas=(List<Cuenta>)request.getSession().getAttribute("cuentas");
		    if(cuentas!=null)request.setAttribute("cuentas", cuentas);
		request.getRequestDispatcher("HistorialMovimientosCliente.jsp").forward(request, response);
	}

}
