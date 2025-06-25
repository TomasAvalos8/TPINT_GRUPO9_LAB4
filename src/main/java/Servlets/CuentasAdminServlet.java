package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Datos.CuentaDao;
import DatosImpl.CuentaDaoImpl;
import Dominio.Cuenta;

/**
 * Servlet implementation class CuentasAdminServlet
 */
@WebServlet("/CuentasAdminServlet")
public class CuentasAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CuentasAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CuentaDao cuentaDao = new CuentaDaoImpl();
        int siguienteId = cuentaDao.obtenerSiguienteIdCuenta();
        request.setAttribute("siguienteIdCuenta", siguienteId);
        request.getRequestDispatcher("CuentasAdmin.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    
	    
	}

}
