package DatosImpl;

import Datos.PrestamoDao;
import Dominio.Prestamo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDaoImpl implements PrestamoDao {

    @Override
    public boolean insertar(Prestamo prestamo) {
        Conexion cn = new Conexion();
        Connection conexion = cn.Open();

        String sql = "INSERT INTO Prestamo (id_solicitud, dni_cliente, numero_cuenta, fecha_alta, cuotas, importe_pagar_por_mes, plazo_pago_meses, importe_solicitado, activo) VALUES (?, ?, ?, NOW(), ?, ?, ?, ?, 1)";
        try {
             PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, prestamo.getSolicitud().getId_solicitud());
            ps.setInt(2, prestamo.getCliente().getDni());
            ps.setInt(3, prestamo.getCuenta().getId());
            ps.setInt(4, prestamo.getCuotas());
            ps.setDouble(5, prestamo.getImporte_pagar_por_mes());
            ps.setInt(6, prestamo.getPlazo_pago_meses());
            ps.setDouble(7, prestamo.getImporte_solicitado());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


 
}
