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
}
