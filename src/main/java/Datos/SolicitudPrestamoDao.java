package Datos;

import Dominio.SolicitudPrestamo;
import java.util.List;

public interface SolicitudPrestamoDao {
    boolean insertar(SolicitudPrestamo prestamo);
    List<SolicitudPrestamo> obtenerTodos();
    boolean actualizarAutorizacion(int idSolicitud, int nuevaAutorizacion);
    SolicitudPrestamo obtenerSolicitudPorId(int idSolicitud);
}
