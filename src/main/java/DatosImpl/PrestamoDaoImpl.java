package DatosImpl;

import Datos.PrestamoDao;
import Dominio.Prestamo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDaoImpl implements PrestamoDao {
    // Suponiendo que tienes una clase Conexion para obtener la conexión
    private Connection getConnection() throws SQLException {
        return Conexion.getConexion();
    }

 
}
