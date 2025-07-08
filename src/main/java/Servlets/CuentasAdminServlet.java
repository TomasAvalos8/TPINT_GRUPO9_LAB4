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
import Dominio.TipoCuenta;
import Excepciones.ClienteNoExisteException;
import java.util.List;
import Datos.TipoCuentaDao;
import DatosImpl.TipoCuentaDaoImpl;
import Dominio.Movimiento;
import Dominio.TipoMovimiento;
import Negocio.MovimientoNeg;
import NegocioImpl.MovimientoNegImpl;
import java.time.LocalDate;


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
        
        String tipoCuentaFiltro = request.getParameter("tipoCuentaFiltro");
        List<Cuenta> listaCuentas;
        
        if (tipoCuentaFiltro != null && !tipoCuentaFiltro.isEmpty()) {
            try {
                int idTipoCuenta = Integer.parseInt(tipoCuentaFiltro);
                listaCuentas = cuentaNeg.obtenerCuentasPorTipo(idTipoCuenta);
            } 
            catch (NumberFormatException e) {
                listaCuentas = cuentaNeg.obtenerTodasLasCuentas();
            }
        } else {
            listaCuentas = cuentaNeg.obtenerTodasLasCuentas();
        }
        
        request.setAttribute("listaCuentas", listaCuentas);

        TipoCuentaDao tipoCuentaDao = new TipoCuentaDaoImpl();
        List<TipoCuenta> tiposCuenta = tipoCuentaDao.obtenerTodos();
        request.setAttribute("tiposCuenta", tiposCuenta);

        request.getRequestDispatcher("CuentasAdmin.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CuentaNeg cuentaNeg = new CuentaNegImpl();
        TipoCuentaDao tipoCuentaDao = new TipoCuentaDaoImpl();
        List<TipoCuenta> tiposCuenta = tipoCuentaDao.obtenerTodos();
        request.setAttribute("tiposCuenta", tiposCuenta);
        
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
        
        String actualizar = request.getParameter("actualizar");
        if (actualizar != null) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                int dni = Integer.parseInt(request.getParameter("dni"));
                String fechaStr = request.getParameter("fecha");
                int tipoId = Integer.parseInt(request.getParameter("tipoCuenta"));
                float saldo = Float.parseFloat(request.getParameter("saldo"));
                java.sql.Date fecha = java.sql.Date.valueOf(fechaStr);
                
                Cuenta cuenta = new Cuenta();
                cuenta.setId(id);
                cuenta.setDni(dni);
                String cbu = String.format("%022d", id); 
                cuenta.setCBU(cbu);
                cuenta.setCreacion(fecha);
                TipoCuenta tipoCuenta = new TipoCuenta();
                tipoCuenta.setIdTipoCuenta(tipoId);
                cuenta.setTipo(tipoCuenta);
                cuenta.setSaldo(saldo);
                cuenta.setEstado(true);
                
                boolean actualizado = cuentaNeg.actualizarCuenta(cuenta);
                if (actualizado) {
                    request.setAttribute("mensajeServlet", "Cuenta actualizada exitosamente.");
                } else {
                    request.setAttribute("mensajeServlet", "No se pudo actualizar la cuenta.");
                }
            }
            catch (Exception e) {
                request.setAttribute("mensajeServlet", "Error al actualizar: " + e.getMessage());
            }
            int siguienteId = cuentaNeg.obtenerSiguienteIdCuenta();
            request.setAttribute("siguienteIdCuenta", siguienteId);
            List<Cuenta> listaCuentas = cuentaNeg.obtenerTodasLasCuentas();
            request.setAttribute("listaCuentas", listaCuentas);
            request.getRequestDispatcher("CuentasAdmin.jsp").forward(request, response);
            return;
        }
        
        String registrar = request.getParameter("registrar");
        if (registrar != null) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                int dni = Integer.parseInt(request.getParameter("dni"));
                List<Cuenta> cuentasUsuario = cuentaNeg.obtenerTodasLasCuentas();
                int cuentasActivas = 0;
                for (Cuenta c : cuentasUsuario) {
                    if (c.getDni() == dni && c.isEstado()) {
                        cuentasActivas++;
                    }
                }
                if (cuentasActivas >= 3) {
                    request.setAttribute("mensajeServlet", "Error: El usuario ya tiene 3 cuentas activas. No puede crear otra cuenta.");
                    int siguienteId = cuentaNeg.obtenerSiguienteIdCuenta();
                    request.setAttribute("siguienteIdCuenta", siguienteId);
                    List<Cuenta> listaCuentas = cuentaNeg.obtenerTodasLasCuentas();
                    request.setAttribute("listaCuentas", listaCuentas);
                    request.getRequestDispatcher("CuentasAdmin.jsp").forward(request, response);
                    return;
                }
                int tipoId = Integer.parseInt(request.getParameter("tipoCuenta"));
                float saldo = Float.parseFloat(request.getParameter("saldo"));
                java.sql.Date fecha = new java.sql.Date(System.currentTimeMillis());
                Cuenta cuenta = new Cuenta();
                cuenta.setId(id);
                cuenta.setDni(dni);
                String cbu = String.format("%022d", id);
                cuenta.setCBU(cbu);
                cuenta.setCreacion(fecha);
                TipoCuenta tipoCuenta = new TipoCuenta();
                tipoCuenta.setIdTipoCuenta(tipoId);
                cuenta.setTipo(tipoCuenta);
                cuenta.setSaldo(saldo);
                cuenta.setEstado(true);
                boolean exito = false;
                try {
                    exito = cuentaNeg.crearCuenta(cuenta);
                } catch (ClienteNoExisteException e) {
                    request.setAttribute("mensajeServlet", "Error: DNI inexistente.");
                    int siguienteId = cuentaNeg.obtenerSiguienteIdCuenta();
                    request.setAttribute("siguienteIdCuenta", siguienteId);
                    List<Cuenta> listaCuentas = cuentaNeg.obtenerTodasLasCuentas();
                    request.setAttribute("listaCuentas", listaCuentas);
                    request.getRequestDispatcher("CuentasAdmin.jsp").forward(request, response);
                    return;
                }
                if (exito) {
                    request.setAttribute("mensajeServlet", "Cuenta registrada exitosamente.");
                    
                    Movimiento movimiento = new Movimiento();
                    movimiento.setNumeroCuenta(id);
                    TipoMovimiento tipoMovimiento = new TipoMovimiento();
                    tipoMovimiento.setIdTipoMovimiento(1); 
                    movimiento.setTipoMovimiento(tipoMovimiento);
                    movimiento.setDetalle("Alta de cuenta");
                    movimiento.setMonto(saldo);
                    movimiento.setFecha(LocalDate.now());
                    MovimientoNeg movimientoNeg = new MovimientoNegImpl();
                    movimientoNeg.insertarMovimiento(movimiento);
                } else {
                    request.setAttribute("mensajeServlet", "Error al registrar la cuenta.");
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
        
        int siguienteId = cuentaNeg.obtenerSiguienteIdCuenta();
        request.setAttribute("siguienteIdCuenta", siguienteId);
        List<Cuenta> listaCuentas = cuentaNeg.obtenerTodasLasCuentas();
        request.setAttribute("listaCuentas", listaCuentas);
        request.getRequestDispatcher("CuentasAdmin.jsp").forward(request, response);
    }
}