package Servlets;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Datos.CuentaDao;
import DatosImpl.Conexion;
import DatosImpl.CuentaDaoImpl;
import Dominio.Cuenta;
import Dominio.Movimiento;
import Dominio.TipoMovimiento;
import Negocio.TransferenciaNeg;
import NegocioImpl.TransferenciaNegImpl;
import Negocio.CuentaNeg;
import Negocio.MovimientoNeg;
import NegocioImpl.CuentaNegImpl;
import NegocioImpl.MovimientoNegImpl;

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
        HttpSession session = request.getSession(false);
        
        if (session == null || session.getAttribute("id_usuario") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }
        
        Integer idUsuario = (Integer) session.getAttribute("id_usuario");

        try {
            CuentaNeg cuentaneg = new CuentaNegImpl();
            List<Cuenta> cuentas = cuentaneg.obtenerCuentasPorIdUsuario(idUsuario);

            if (cuentas == null || cuentas.isEmpty()) {
                request.setAttribute("mensaje", "No se encontraron cuentas para el usuario.");
            }

            request.setAttribute("cuentas", cuentas);
            request.getRequestDispatcher("TransferenciaCliente.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error al cargar las cuentas: " + e.getMessage());
            request.getRequestDispatcher("TransferenciaCliente.jsp").forward(request, response);
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String cuentaOrigen = request.getParameter("cuentaorigen");
            String cuentaDestino = request.getParameter("cuentadestino");
            String cantidadStr = request.getParameter("cantidad");
            String concepto = request.getParameter("concepto");

            cantidadStr = cantidadStr.replace(',', '.');
            float monto = Float.parseFloat(cantidadStr);

            java.util.Date fechaUtil = new java.util.Date();
            java.sql.Date fecha = new java.sql.Date(fechaUtil.getTime());

            CuentaNeg cuentanegocio = new CuentaNegImpl();

            Cuenta cuentaSaliente = cuentanegocio.obtenerCuentaPorCBU(cuentaOrigen);
            Cuenta cuentaDestinoObj = cuentanegocio.obtenerCuentaPorCBU(cuentaDestino);

            if (cuentaSaliente == null) throw new Exception("Cuenta origen no encontrada.");
            if (cuentaDestinoObj == null) throw new Exception("Cuenta destino no encontrada.");

            // Transferencia
            TransferenciaNeg negocio = new TransferenciaNegImpl();
            int resultado = negocio.transferirCuenta(cuentaSaliente, cuentaDestinoObj, monto, concepto, fecha);

            HttpSession session = request.getSession(false);
            Integer idUsuario = (Integer) session.getAttribute("id_usuario");

            // cuenta de usuario
            List<Cuenta> cuentas = cuentanegocio.obtenerCuentasPorIdUsuario(idUsuario);
            request.setAttribute("cuentas", cuentas);

            switch (resultado) {
                case 0:
                    request.setAttribute("mensaje", "Transferencia realizada con éxito.");
                    break;
                case 1:
                    request.setAttribute("mensaje", "Error: saldo insuficiente.");
                    break;
                case 2:
                    request.setAttribute("mensaje", "Error: las cuentas no pueden ser iguales.");
                    break;
                case 3:
                    request.setAttribute("mensaje", "Error: una de las cuentas está inactiva.");
                    break;
                default:
                    request.setAttribute("mensaje", "Error al realizar la transferencia.");
                    break;
            }

            request.getRequestDispatcher("TransferenciaCliente.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();

            HttpSession session = request.getSession(false);
            Integer idUsuario = (Integer) session.getAttribute("id_usuario");

            CuentaNeg cuentanegocio = new CuentaNegImpl();
            List<Cuenta> cuentas = cuentanegocio.obtenerCuentasPorIdUsuario(idUsuario);
            request.setAttribute("cuentas", cuentas);

            request.setAttribute("mensaje", "Error en la operación: " + e.getMessage());
            request.getRequestDispatcher("TransferenciaCliente.jsp").forward(request, response);
        }
    }





}
