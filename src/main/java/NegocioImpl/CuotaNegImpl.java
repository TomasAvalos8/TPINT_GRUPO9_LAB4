package NegocioImpl;

import Dominio.Cuota;
import Negocio.CuotaNeg;
import Datos.CuotaDao;
import DatosImpl.CuotaDaoImpl;
import java.util.List;

public class CuotaNegImpl implements CuotaNeg {

    private CuotaDao cuotaDao = new CuotaDaoImpl();

    @Override
    public boolean insertar(Cuota cuota) {
        return cuotaDao.insertar(cuota);
    }

    @Override
    public boolean eliminarCuotasPorPrestamo(int id_prestamo) {
        return cuotaDao.eliminarCuotasPorPrestamo(id_prestamo);
    }

    @Override
    public List<Cuota> obtenerCuotasPorCliente(int id_cliente) {
        return cuotaDao.obtenerCuotasPorCliente(id_cliente);
    }

    @Override
    public boolean actualizarCuota(Cuota cuota) {
        return cuotaDao.actualizarCuota(cuota);
    }

    @Override
    public Cuota obtenerCuotaPorId(int id) {
        return cuotaDao.obtenerCuotaPorId(id);
    }
}