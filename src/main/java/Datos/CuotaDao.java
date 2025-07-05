package Datos;

import Dominio.Cuota;

public interface CuotaDao {
    boolean insertar(Cuota cuota);
    boolean eliminarCuotasPorPrestamo(int id_prestamo);
}
