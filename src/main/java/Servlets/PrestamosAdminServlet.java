package Servlets;

import Dominio.SolicitudPrestamo;
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
        SolicitudPrestamoNeg prestamoNeg = new SolicitudPrestamoNegImpl();
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
            prestamoNeg.actualizarAutorizacion(id, nuevaAutorizacion);
        }
        request.setAttribute("listaPrestamos", prestamoNeg.obtenerTodos());
        request.getRequestDispatcher("PrestamosAdmin.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
