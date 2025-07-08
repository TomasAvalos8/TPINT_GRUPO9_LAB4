package Negocio;

import Dominio.Cuota;
import java.util.List;

public interface CuotaNeg {
    boolean insertar(Cuota cuota);
    boolean eliminarCuotasPorPrestamo(int id_prestamo);
    List<Cuota> obtenerCuotasPorCliente(int id_cliente);
    boolean actualizarCuota(Cuota cuota);
    Cuota obtenerCuotaPorId(int id);
}
