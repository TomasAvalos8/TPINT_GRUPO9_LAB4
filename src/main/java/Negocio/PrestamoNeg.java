package Negocio;

import Dominio.Prestamo;
import java.util.List;

public interface PrestamoNeg {
    int insertar(Prestamo prestamo); 
    boolean eliminarPrestamoPorSolicitud(int id);
}
