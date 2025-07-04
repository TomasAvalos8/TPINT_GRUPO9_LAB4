package NegocioImpl;

import Dominio.Prestamo;
import Negocio.PrestamoNeg;
import Datos.PrestamoDao;
import DatosImpl.PrestamoDaoImpl;
import java.util.List;

public class PrestamoNegImpl implements PrestamoNeg {
    private PrestamoDao prestamoDao = new PrestamoDaoImpl();

    @Override
    public int insertar(Prestamo prestamo) {
        return prestamoDao.insertar(prestamo);
    }

    @Override
    public boolean eliminarPrestamoPorSolicitud(int id) {
        return prestamoDao.eliminarPrestamoPorSolicitud(id);
    }

}
