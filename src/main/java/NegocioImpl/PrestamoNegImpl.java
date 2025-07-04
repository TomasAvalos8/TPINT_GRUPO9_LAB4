package NegocioImpl;

import Dominio.Prestamo;
import Negocio.PrestamoNeg;
import Datos.PrestamoDao;
import DatosImpl.PrestamoDaoImpl;
import java.util.List;

public class PrestamoNegImpl implements PrestamoNeg {
    private PrestamoDao prestamoDao = new PrestamoDaoImpl();

    @Override
    public boolean insertar(Prestamo prestamo) {
        return prestamoDao.insertar(prestamo);
    }


}
