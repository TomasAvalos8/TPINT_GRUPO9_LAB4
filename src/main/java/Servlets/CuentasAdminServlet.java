package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio.CuentaNeg;
import NegocioImpl.CuentaNegImpl;
import Dominio.Cuenta;
import Excepciones.ClienteNoExisteException;
import java.util.List;


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
        CuentaNeg cuentaNeg = new CuentaNegImpl();
        int siguienteId = cuentaNeg.obtenerSiguienteIdCuenta();
        request.setAttribute("siguienteIdCuenta", siguienteId);
       
        List<Cuenta> listaCuentas = cuentaNeg.obtenerTodasLasCuentas();
        request.setAttribute("listaCuentas", listaCuentas);
        request.getRequestDispatcher("CuentasAdmin.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CuentaNeg cuentaNeg = new CuentaNegImpl();
        // Eliminar cuenta
        String eliminarId = request.getParameter("eliminarId");
        if (eliminarId != null) {
            int idEliminar = Integer.parseInt(eliminarId);
            boolean eliminado = cuentaNeg.eliminarCuenta(idEliminar);
            if (eliminado) {
                request.setAttribute("mensajeServlet", "Cuenta eliminada exitosamente.");
            } else {
                request.setAttribute("mensajeServlet", "No se pudo eliminar la cuenta.");
            }
            int siguienteId = cuentaNeg.obtenerSiguienteIdCuenta();
            request.setAttribute("siguienteIdCuenta", siguienteId);
            List<Cuenta> listaCuentas = cuentaNeg.obtenerTodasLasCuentas();
            request.setAttribute("listaCuentas", listaCuentas);
            request.getRequestDispatcher("CuentasAdmin.jsp").forward(request, response);
            return;
        }
        // Modificar (cargar datos en formulario)
        String modificarId = request.getParameter("modificarId");
        if (modificarId != null) {
            int idModificar = Integer.parseInt(modificarId);
            List<Cuenta> listaCuentas = cuentaNeg.obtenerTodasLasCuentas();
            Cuenta cuentaModificar = null;
            for (Cuenta c : listaCuentas) {
                if (c.getId() == idModificar) {
                    cuentaModificar = c;
                    break;
                }
            }
            request.setAttribute("cuentaModificar", cuentaModificar);
            int siguienteId = cuentaNeg.obtenerSiguienteIdCuenta();
            request.setAttribute("siguienteIdCuenta", siguienteId);
            request.setAttribute("listaCuentas", listaCuentas);
            request.getRequestDispatcher("CuentasAdmin.jsp").forward(request, response);
            return;
        }
        // Actualizar cuenta
        String actualizar = request.getParameter("actualizar");
        if (actualizar != null) {
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
                boolean actualizado = cuentaNeg.actualizarCuenta(cuenta);
                if (actualizado) {
                    request.setAttribute("mensajeServlet", "Cuenta actualizada exitosamente.");
                } else {
                    request.setAttribute("mensajeServlet", "No se pudo actualizar la cuenta.");
                }
            } catch (Exception e) {
                request.setAttribute("mensajeServlet", "Error al actualizar: " + e.getMessage());
            }
            int siguienteId = cuentaNeg.obtenerSiguienteIdCuenta();
            request.setAttribute("siguienteIdCuenta", siguienteId);
            List<Cuenta> listaCuentas = cuentaNeg.obtenerTodasLasCuentas();
            request.setAttribute("listaCuentas", listaCuentas);
            request.getRequestDispatcher("CuentasAdmin.jsp").forward(request, response);
            return;
        }
        // Registrar cuenta (alta)
        String registrar = request.getParameter("registrar");
        if (registrar != null) {
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
                try {
                    boolean exito = cuentaNeg.crearCuenta(cuenta);
                    if (exito) {
                        request.setAttribute("mensajeServlet", "Cuenta registrada exitosamente.");
                    } else {
                        request.setAttribute("mensajeServlet", "Error al registrar la cuenta.");
                    }
                } catch (ClienteNoExisteException e) {
                    request.setAttribute("mensajeServlet", "Cliente inexistente");
                }
                int siguienteId = cuentaNeg.obtenerSiguienteIdCuenta();
                request.setAttribute("siguienteIdCuenta", siguienteId);
                List<Cuenta> listaCuentas = cuentaNeg.obtenerTodasLasCuentas();
                request.setAttribute("listaCuentas", listaCuentas);
                request.getRequestDispatcher("CuentasAdmin.jsp").forward(request, response);
                return;
            } catch (Exception e) {
                request.setAttribute("mensajeServlet", "Error en los datos ingresados: " + e.getMessage());
                int siguienteId = cuentaNeg.obtenerSiguienteIdCuenta();
                request.setAttribute("siguienteIdCuenta", siguienteId);
                List<Cuenta> listaCuentas = cuentaNeg.obtenerTodasLasCuentas();
                request.setAttribute("listaCuentas", listaCuentas);
                request.getRequestDispatcher("CuentasAdmin.jsp").forward(request, response);
                return;
            }
        }
        // Si no hay ninguna acción, recarga la página
        int siguienteId = cuentaNeg.obtenerSiguienteIdCuenta();
        request.setAttribute("siguienteIdCuenta", siguienteId);
        List<Cuenta> listaCuentas = cuentaNeg.obtenerTodasLasCuentas();
        request.setAttribute("listaCuentas", listaCuentas);
        request.getRequestDispatcher("CuentasAdmin.jsp").forward(request, response);
}
}
