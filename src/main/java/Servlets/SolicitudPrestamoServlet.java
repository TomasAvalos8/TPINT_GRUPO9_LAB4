package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SolicitudPrestamoServlet
 */
@WebServlet("/SolicitudPrestamoServlet")
public class SolicitudPrestamoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SolicitudPrestamoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("SolicitudPrestamo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		if ("calcular".equals(accion)) {
			try {
				String cuotasParam = request.getParameter("cuotas");
				String importeParam = request.getParameter("importe_solicitado");
				int cuotas = Integer.parseInt(cuotasParam);
				double importe = Double.parseDouble(importeParam);
				double interes = 0;
				switch (cuotas) {
					case 6: interes = 0.05; break;
					case 12: interes = 0.10; break;
					case 18: interes = 0.15; break;
					case 24: interes = 0.20; break;
					case 30: interes = 0.25; break;
					case 36: interes = 0.30; break;
					case 42: interes = 0.35; break;
					default: interes = 0; break;
				}
				double totalPagar = importe + (importe * interes);
				double cuotaMensual = totalPagar / cuotas;
				request.setAttribute("cuotaMensual", String.format("$%.2f/mes", cuotaMensual));
				request.setAttribute("totalPagar", String.format("$%.2f", totalPagar));
			} catch (Exception e) {
				request.setAttribute("cuotaMensual", "");
				request.setAttribute("totalPagar", "");
			}
		}
		request.getRequestDispatcher("SolicitudPrestamo.jsp").forward(request, response);
	}

}
