package NegocioImpl;

import Dominio.SolicitudPrestamo;
import Negocio.SolicitudPrestamoNeg;
import Datos.SolicitudPrestamoDao;
import DatosImpl.SolicitudPrestamoDaoImpl;
import java.util.List;

public class SolicitudPrestamoNegImpl implements SolicitudPrestamoNeg {
    private SolicitudPrestamoDao dao = new SolicitudPrestamoDaoImpl();

    @Override
    public boolean insertar(SolicitudPrestamo prestamo) {
        return dao.insertar(prestamo);
    }

    @Override
    public java.util.List<SolicitudPrestamo> obtenerTodos() {
        return dao.obtenerTodos();
    }

    @Override
    public boolean actualizarAutorizacion(int idSolicitud, int nuevaAutorizacion) {
        return dao.actualizarAutorizacion(idSolicitud, nuevaAutorizacion);
    }

    @Override
    public SolicitudPrestamo obtenerSolicitudPorId(int idSolicitud) {
        return dao.obtenerSolicitudPorId(idSolicitud);
    }

    @Override
    public List<SolicitudPrestamo> obtenerSolicitudesPorUsuario(Integer idUsuario) {
        return dao.obtenerSolicitudesPorUsuario(idUsuario);
    }
}
