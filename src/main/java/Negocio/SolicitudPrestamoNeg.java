package Negocio;

import Dominio.SolicitudPrestamo;
import java.util.List;

public interface SolicitudPrestamoNeg {
    boolean insertar(SolicitudPrestamo prestamo);
    List<SolicitudPrestamo> obtenerTodos();
    boolean actualizarAutorizacion(int idSolicitud, int nuevaAutorizacion);
    SolicitudPrestamo obtenerSolicitudPorId(int idSolicitud);
    List<SolicitudPrestamo> obtenerSolicitudesPorUsuario(Integer idUsuario);
}
