package NegocioImpl;

import Dominio.Cuota;
import Negocio.CuotaNeg;
import Datos.CuotaDao;
import DatosImpl.CuotaDaoImpl;

public class CuotaNegImpl implements CuotaNeg {

    private CuotaDao cuotaDao = new CuotaDaoImpl();

    @Override
    public boolean insertar(Cuota cuota) {
        return cuotaDao.insertar(cuota);
    }
}
