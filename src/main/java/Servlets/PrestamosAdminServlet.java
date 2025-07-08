package Servlets;

import Dominio.Cuota;
import Dominio.Prestamo;
import Dominio.SolicitudPrestamo;
import Dominio.Movimiento;
import Dominio.TipoMovimiento;
import Negocio.CuotaNeg;
import Negocio.PrestamoNeg;
import Negocio.SolicitudPrestamoNeg;
import Negocio.MovimientoNeg;
import NegocioImpl.SolicitudPrestamoNegImpl;
import NegocioImpl.MovimientoNegImpl;
import Datos.TipoMovimientoDao;
import DatosImpl.TipoMovimientoDaoImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.time.LocalDate;

@WebServlet("/PrestamosAdminServlet")
public class PrestamosAdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SolicitudPrestamoNeg solPrestamoNeg = new SolicitudPrestamoNegImpl();
        String accion = request.getParameter("accion");
        String idParam = request.getParameter("id");
        if (accion != null && idParam != null) {
            int id = Integer.parseInt(idParam);
            int nuevaAutorizacion = 0;
            if ("aprobar".equals(accion)) {
                nuevaAutorizacion = 2;
            } else if ("rechazar".equals(accion)) {
                nuevaAutorizacion = 1;
            }

            Prestamo prestamo = new Prestamo();
            SolicitudPrestamo solicitud = solPrestamoNeg.obtenerSolicitudPorId(id);
            if (nuevaAutorizacion == 2) {
                if (solicitud != null) {
                    prestamo.setSolicitud(solicitud);
                    prestamo.setCliente(solicitud.getCliente());
                    prestamo.setCuenta(solicitud.getCuentaDeposito());
                    prestamo.setFecha_alta(new java.util.Date());
                    prestamo.setCuotas(solicitud.getCuotas());
                    prestamo.setImporte_pagar_por_mes(solicitud.getImporte_pagar_intereses() / solicitud.getCuotas());
                    prestamo.setPlazo_pago_meses(solicitud.getCuotas());
                    prestamo.setImporte_total_intereses(solicitud.getImporte_pagar_intereses());
                    prestamo.setImporte_solicitado(solicitud.getImporte_solicitado());
                    prestamo.setActivo(true);
                    PrestamoNeg prestamoNeg = new NegocioImpl.PrestamoNegImpl();
                    int idPrestamo = prestamoNeg.insertar(prestamo);
                    prestamo.setId_prestamo(idPrestamo);

                    int idCuentaDestino = solicitud.getCuentaDeposito().getId();
                    double monto = solicitud.getImporte_solicitado();
                    Negocio.CuentaNeg cuentaNeg = new NegocioImpl.CuentaNegImpl();
                    cuentaNeg.depositarEnCuenta(idCuentaDestino, monto);

                
                        Movimiento movimiento = new Movimiento();
                        movimiento.setNumeroCuenta(idCuentaDestino);
                        TipoMovimiento tipoMovimiento = new TipoMovimiento();
                        tipoMovimiento.setIdTipoMovimiento(2);
                        movimiento.setTipoMovimiento(tipoMovimiento);
                        movimiento.setDetalle("Alta de préstamo");
                        movimiento.setMonto((float)monto);
                        movimiento.setFecha(LocalDate.now());
                        MovimientoNeg movimientoNeg = new MovimientoNegImpl();
                        movimientoNeg.insertarMovimiento(movimiento);

                    for (int i = 1; i <= solicitud.getCuotas(); i++) {
                        Cuota cuota = new Cuota();
                        cuota.setPrestamo(prestamo);
                        cuota.setNumeroCuota(i);
                        cuota.setMonto(prestamo.getImporte_pagar_por_mes());
                        java.util.Calendar calendar = java.util.Calendar.getInstance();
                        calendar.setTime(prestamo.getFecha_alta());
                        calendar.add(java.util.Calendar.MONTH, i - 1); 
                        cuota.setFechaPago(calendar.getTime());
                        cuota.setPagado(false); 
                        CuotaNeg cuotaNeg = new NegocioImpl.CuotaNegImpl();
                        cuotaNeg.insertar(cuota);
                    }
                }
            }

            if (nuevaAutorizacion == 1 && solicitud != null && solicitud.getAutorizacion() == 2) {
                PrestamoNeg prestamoNeg = new NegocioImpl.PrestamoNegImpl();
                Prestamo prestamoExistente = prestamoNeg.obtenerPorSolicitud(id); 

                if (prestamoExistente != null && prestamoExistente.getId_prestamo() > 0) {
                    int idPrestamo = prestamoExistente.getId_prestamo();

                    CuotaNeg cuotaNeg = new NegocioImpl.CuotaNegImpl();
                    cuotaNeg.eliminarCuotasPorPrestamo(idPrestamo);

                    prestamoNeg.eliminarPrestamoPorSolicitud(id);

                    int idCuentaDestino = solicitud.getCuentaDeposito().getId();
                    double monto = solicitud.getImporte_pagar_intereses();
                    Negocio.CuentaNeg cuentaNeg = new NegocioImpl.CuentaNegImpl();
                    cuentaNeg.depositarEnCuenta(idCuentaDestino, -monto);
                }

          
            }

            solPrestamoNeg.actualizarAutorizacion(id, nuevaAutorizacion);

        }
        request.setAttribute("listaPrestamos", solPrestamoNeg.obtenerTodos());
        request.getRequestDispatcher("PrestamosAdmin.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
