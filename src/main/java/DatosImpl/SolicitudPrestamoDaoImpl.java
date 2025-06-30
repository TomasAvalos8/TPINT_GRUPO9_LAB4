package DatosImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Datos.SolicitudPrestamoDao;
import Dominio.SolicitudPrestamo;
import java.sql.Date;

public class SolicitudPrestamoDaoImpl implements SolicitudPrestamoDao {
    @Override
    public boolean insertar(SolicitudPrestamo prestamo) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.Open();
        boolean exito = false;
        String sql = "INSERT INTO SolicitudPrestamos (dni_cliente, importe_solicitado, numero_cuenta_deposito, cuotas, fecha_solicitud, autorizacion, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, prestamo.getDni_cliente());
            ps.setLong(2, prestamo.getImporte_solicitado());
            ps.setLong(3, prestamo.getNumero_cuenta_deposito());
            ps.setInt(4, prestamo.getCuotas());
            ps.setDate(5, prestamo.getFecha_solicitud());
            ps.setBoolean(6, prestamo.getAutorizacion());
            ps.setString(7, prestamo.getEstado());
            exito = ps.executeUpdate() > 0;
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exito;
    }
}
