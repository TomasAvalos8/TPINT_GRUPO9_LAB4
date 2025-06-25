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
import Excepciones.ClienteNoExisteException;


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
        // Obtener listado de cuentas de la base de datos
        java.util.List<Dominio.Cuenta> listaCuentas = cuentaDao.obtenerTodasLasCuentas();
        request.setAttribute("listaCuentas", listaCuentas);
        request.getRequestDispatcher("CuentasAdmin.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String eliminarId = request.getParameter("eliminarId");
        if (eliminarId != null) {
            int idEliminar = Integer.parseInt(eliminarId);
            CuentaDao cuentaDao = new CuentaDaoImpl();
            boolean eliminado = cuentaDao.eliminarCuenta(idEliminar);
            if (eliminado) {
                request.setAttribute("mensajeServlet", "Cuenta eliminada exitosamente.");
            } else {
                request.setAttribute("mensajeServlet", "No se pudo eliminar la cuenta.");
            }
            int siguienteId = cuentaDao.obtenerSiguienteIdCuenta();
            request.setAttribute("siguienteIdCuenta", siguienteId);
            java.util.List<Dominio.Cuenta> listaCuentas = cuentaDao.obtenerTodasLasCuentas();
            request.setAttribute("listaCuentas", listaCuentas);
            request.getRequestDispatcher("CuentasAdmin.jsp").forward(request, response);
            return;
        }
        // Validar que el formulario fue enviado con el botón de registrar
        String registrar = request.getParameter("registrar");
        if (registrar == null) {
            // Si no se presionó el botón registrar, solo redirigir a la página sin intentar crear la cuenta
            CuentaDao cuentaDao = new CuentaDaoImpl();
            int siguienteId = cuentaDao.obtenerSiguienteIdCuenta();
            request.setAttribute("siguienteIdCuenta", siguienteId);
            // Obtener listado de cuentas de la base de datos
            java.util.List<Dominio.Cuenta> listaCuentas = cuentaDao.obtenerTodasLasCuentas();
            request.setAttribute("listaCuentas", listaCuentas);
            request.getRequestDispatcher("CuentasAdmin.jsp").forward(request, response);
            return;
        }
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            int dni = Integer.parseInt(request.getParameter("dni"));
            String cbu = request.getParameter("cbu");
            String fechaStr = request.getParameter("fecha");
            int tipo = Integer.parseInt(request.getParameter("tipoCuenta"));
            float saldo = Float.parseFloat(request.getParameter("saldo"));

            java.sql.Date fecha = java.sql.Date.valueOf(fechaStr);

            Cuenta cuenta = new Cuenta();
            cuenta.setId(id);
            cuenta.setDni(dni);
            cuenta.setCBU(cbu);
            cuenta.setCreacion(fecha);
            cuenta.setTipo(tipo);
            cuenta.setSaldo(saldo);
            cuenta.setEstado(true);

            CuentaDao cuentaDao = new CuentaDaoImpl();
            try {
                boolean exito = cuentaDao.crearCuenta(cuenta);
                if (exito) {
                    request.setAttribute("mensajeServlet", "Cuenta registrada exitosamente.");
                } else {
                    request.setAttribute("mensajeServlet", "Error al registrar la cuenta.");
                }
            } catch (ClienteNoExisteException e) {
                request.setAttribute("mensajeServlet", "Cliente inexistente");
            } 
        } catch (Exception e) {
            request.setAttribute("mensajeServlet", "Error en los datos ingresados: " + e.getMessage());
        }
        CuentaDao cuentaDao = new CuentaDaoImpl();
        int siguienteId = cuentaDao.obtenerSiguienteIdCuenta();
        request.setAttribute("siguienteIdCuenta", siguienteId);
        // Obtener listado de cuentas de la base de datos
        java.util.List<Dominio.Cuenta> listaCuentas = cuentaDao.obtenerTodasLasCuentas();
        request.setAttribute("listaCuentas", listaCuentas);
        request.getRequestDispatcher("CuentasAdmin.jsp").forward(request, response);
}
}
