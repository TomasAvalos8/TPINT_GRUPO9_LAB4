package Servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dominio.Reporte;
import Negocio.ReportesNegocio;
import NegocioImpl.ReportesNegocioImpl;

@WebServlet("/ServletInformes")
public class ServletReportes extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private ReportesNegocio reportesNegocio;
    
    public ServletReportes() {
        super();
        this.reportesNegocio = new ReportesNegocioImpl();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        request.getRequestDispatcher("InformesAdmin.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            String fechaInicioStr = request.getParameter("startDate");
            String fechasFinStr = request.getParameter("endDate");
            String tipoReporte = request.getParameter("tipoReporte");
            
            if (fechaInicioStr == null || fechasFinStr == null || 
            		fechaInicioStr.trim().isEmpty() || fechasFinStr.trim().isEmpty()) {
                request.setAttribute("error", "Las fechas son obligatorias");
                request.getRequestDispatcher("InformesAdmin.jsp").forward(request, response);
                return;
            }
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaInicio = dateFormat.parse(fechaInicioStr);
            Date fechaFin = dateFormat.parse(fechasFinStr);

            if (fechaInicio.after(fechaFin)) {
                request.setAttribute("error", "La fecha de inicio no puede ser mayor que la fecha de fin");
                request.getRequestDispatcher("InformesAdmin.jsp").forward(request, response);
                return;
            }

            if (tipoReporte == null || tipoReporte.equals("completo") || tipoReporte.equals("prestamos")) {
                List<Reporte> reportePrestamos = reportesNegocio.generarReportePrestamos(fechaInicio, fechaFin);
                request.setAttribute("reportePrestamos", reportePrestamos);
                
            }
            
            request.setAttribute("fechaInicio", fechaInicioStr);
            request.setAttribute("fechaFin", fechasFinStr);
            request.setAttribute("tipoReporte", tipoReporte);
            
        } catch (Exception e) {
            request.setAttribute("error", "Error al generar el reporte: " + e.getMessage());
            e.printStackTrace();
        }
       
        request.getRequestDispatcher("InformesAdmin.jsp").forward(request, response);
    }
}