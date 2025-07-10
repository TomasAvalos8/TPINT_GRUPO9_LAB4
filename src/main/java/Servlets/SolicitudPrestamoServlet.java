package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Dominio.Cliente;
import Dominio.SolicitudPrestamo;
import Dominio.Cuenta;
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
		cargarClienteYCuentas(request);
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
				request.setAttribute("importe_solicitado", importeParam);
				request.setAttribute("cuotas", cuotasParam);
				request.setAttribute("interes", String.format("%.0f%% interés", interes * 100));

				
			} catch (Exception e) {
				request.setAttribute("cuotaMensual", "");
				request.setAttribute("totalPagar", "");
			}
			cargarClienteYCuentas(request);
			request.getRequestDispatcher("SolicitudPrestamo.jsp").forward(request, response);
			return;
		}

		
		try {
			String cuotasParam = request.getParameter("cuotas");
			String importeParam = request.getParameter("importe_solicitado");
			String cuentaParam = request.getParameter("numero_cuenta_deposito");
			int cuotas = Integer.parseInt(cuotasParam);
			long importe = Long.parseLong(importeParam);
			long cuenta = Long.parseLong(cuentaParam);

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
			double totalPagarConInteres = importe + (importe * interes);

			SolicitudPrestamo prestamo = new SolicitudPrestamo();
			prestamo.setCuotas(cuotas);
			prestamo.setImporte_solicitado(importe);
			prestamo.setImporte_pagar_intereses(totalPagarConInteres); 

			Datos.CuentaDao cuentaDao = new DatosImpl.CuentaDaoImpl();
			Cuenta  cuentaDeposito = cuentaDao.obtenerCuentaPorId((int) cuenta); 
			prestamo.setCuentaDeposito(cuentaDeposito);

			prestamo.setFecha_solicitud(new java.sql.Date(System.currentTimeMillis()));
			prestamo.setAutorizacion(0);
			prestamo.setEstado(true); 

			Integer idUsuario = (Integer) request.getSession().getAttribute("id_usuario");
			Cliente cliente = null;
			if (idUsuario != null) {
				Datos.ClienteDao clienteDao = new DatosImpl.ClienteDaoImpl();
				cliente = clienteDao.obtenerClientePorIdUsuario(idUsuario);
			}
			prestamo.setCliente(cliente);

			Negocio.SolicitudPrestamoNeg neg = new NegocioImpl.SolicitudPrestamoNegImpl();
			boolean exito = neg.insertar(prestamo);
			if (exito) {
				request.setAttribute("mensaje", "Préstamo solicitado exitosamente");
				request.setAttribute("importe_solicitado", null);
				request.setAttribute("cuotas", null);
				request.setAttribute("numero_cuenta_deposito", null);
				request.setAttribute("cuotaMensual", null);
				request.setAttribute("totalPagar", null);
			} else {
				request.setAttribute("mensaje", "Ocurrió un error al solicitar el préstamo");
			}
			cargarClienteYCuentas(request);
		} catch (Exception e) {
			cargarClienteYCuentas(request);
			request.setAttribute("mensaje", "Ocurrió un error al solicitar el préstamo");
		}
		request.getRequestDispatcher("SolicitudPrestamo.jsp").forward(request, response);
	}

	private void cargarClienteYCuentas(HttpServletRequest request) {
		try {
			Integer idUsuario = (Integer) request.getSession().getAttribute("id_usuario");
			Cliente cliente = null;
			if (idUsuario != null) {
				Datos.ClienteDao clienteDao = new DatosImpl.ClienteDaoImpl();
				cliente = clienteDao.obtenerClientePorIdUsuario(idUsuario);
			}
			if (cliente != null) {
				Datos.CuentaDao cuentaDao = new DatosImpl.CuentaDaoImpl();
				java.util.List<Cuenta> cuentas = cuentaDao.obtenerCuentasPorDni(cliente.getDni());
				request.setAttribute("cuentasCliente", cuentas);
				if (cuentas == null || cuentas.isEmpty()) {
					request.setAttribute("mensajeSinCuentas", "No tiene cuentas disponibles para seleccionar.");
				}
			} else {
				request.setAttribute("cuentasCliente", new java.util.ArrayList<Cuenta>());
				request.setAttribute("mensajeSinCuentas", "No se pudo obtener el cliente o no tiene cuentas.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("cuentasCliente", new java.util.ArrayList<Cuenta>());
			request.setAttribute("mensajeSinCuentas", "Error al obtener cuentas del cliente.");
		}
	}
}
