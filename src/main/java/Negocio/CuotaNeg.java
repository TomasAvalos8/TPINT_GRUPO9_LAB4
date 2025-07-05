package Negocio;

import Dominio.Cuota;

public interface CuotaNeg {
    boolean insertar(Cuota cuota);
    boolean eliminarCuotasPorPrestamo(int id_prestamo);
}
