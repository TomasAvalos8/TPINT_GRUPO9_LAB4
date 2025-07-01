package NegocioImpl;

import Dominio.SolicitudPrestamo;
import Negocio.SolicitudPrestamoNeg;
import Datos.SolicitudPrestamoDao;
import DatosImpl.SolicitudPrestamoDaoImpl;

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
}
