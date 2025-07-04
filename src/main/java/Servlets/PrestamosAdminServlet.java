package Servlets;

import Dominio.Prestamo;
import Dominio.SolicitudPrestamo;
import Negocio.PrestamoNeg;
import Negocio.SolicitudPrestamoNeg;
import NegocioImpl.SolicitudPrestamoNegImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
            solPrestamoNeg.actualizarAutorizacion(id, nuevaAutorizacion);

            if (nuevaAutorizacion == 2) {
                SolicitudPrestamo solicitud = solPrestamoNeg.obtenerSolicitudPorId(id);
                
                if (solicitud != null) {
                    
                    Prestamo prestamo = new Prestamo();
                    prestamo.setSolicitud(solicitud);
                    prestamo.setCliente(solicitud.getCliente());
                    prestamo.setCuenta(solicitud.getCuentaDeposito());
                    prestamo.setFecha_alta(new java.util.Date());
                    prestamo.setCuotas(solicitud.getCuotas());
                    prestamo.setImporte_pagar_por_mes(solicitud.getImporte_solicitado() / solicitud.getCuotas()); 
                    prestamo.setPlazo_pago_meses(solicitud.getCuotas());
                    prestamo.setImporte_solicitado(solicitud.getImporte_solicitado());
                    prestamo.setActivo(true);
                    PrestamoNeg prestamoNeg = new NegocioImpl.PrestamoNegImpl();
                    prestamoNeg.insertar(prestamo);

                }
            }

            if (nuevaAutorizacion == 1){
                PrestamoNeg prestamoNeg = new NegocioImpl.PrestamoNegImpl();
                prestamoNeg.eliminarPrestamoPorSolicitud(id);
            }
        }
        request.setAttribute("listaPrestamos", solPrestamoNeg.obtenerTodos());
        request.getRequestDispatcher("PrestamosAdmin.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
