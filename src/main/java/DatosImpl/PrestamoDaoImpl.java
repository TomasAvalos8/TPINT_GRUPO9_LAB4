package DatosImpl;

import Datos.PrestamoDao;
import Dominio.Prestamo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDaoImpl implements PrestamoDao {

    @Override
    public int insertar(Prestamo prestamo) {
        Conexion cn = new Conexion();
        Connection conexion = cn.Open();
        int idGenerado = -1;
        String sql = "INSERT INTO Prestamo (id_solicitud, dni_cliente, numero_cuenta, fecha_alta, cuotas, importe_pagar_por_mes, plazo_pago_meses, importe_solicitado, activo) VALUES (?, ?, ?, NOW(), ?, ?, ?, ?, 1)";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, prestamo.getSolicitud().getId_solicitud());
            ps.setInt(2, prestamo.getCliente().getDni());
            ps.setInt(3, prestamo.getCuenta().getId());
            ps.setInt(4, prestamo.getCuotas());
            ps.setDouble(5, prestamo.getImporte_pagar_por_mes());
            ps.setInt(6, prestamo.getPlazo_pago_meses());
            ps.setDouble(7, prestamo.getImporte_solicitado());
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    idGenerado = rs.getInt(1);
                }
                rs.close();
            }
            ps.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idGenerado;
    }

    @Override
    public boolean eliminarPrestamoPorSolicitud(int id) {
        Conexion cn = new Conexion();
        Connection conexion = cn.Open();

        String sql = "DELETE FROM Prestamo WHERE id_solicitud = ?";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


 
}
