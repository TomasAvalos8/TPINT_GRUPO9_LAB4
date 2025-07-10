package NegocioImpl;

import Dominio.TipoCuenta;
import Negocio.TipoCuentaNeg;
import Datos.TipoCuentaDao;
import DatosImpl.TipoCuentaDaoImpl;
import java.util.List;

public class TipoCuentaNegImpl implements TipoCuentaNeg {

    private TipoCuentaDao tipoCuentaDao = new TipoCuentaDaoImpl();

    @Override
    public List<TipoCuenta> obtenerTodos() {
        return tipoCuentaDao.obtenerTodos();
    }
}
