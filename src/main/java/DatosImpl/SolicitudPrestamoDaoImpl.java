package DatosImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Datos.SolicitudPrestamoDao;
import Dominio.SolicitudPrestamo;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

public class SolicitudPrestamoDaoImpl implements SolicitudPrestamoDao {
    @Override
    public boolean insertar(SolicitudPrestamo prestamo) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.Open();
        boolean exito = false;
        String sql = "INSERT INTO SolicitudPrestamos (dni_cliente, importe_solicitado, numero_cuenta_deposito, cuotas, fecha_solicitud, autorizacion, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, prestamo.getCliente().getDni());
            ps.setLong(2, prestamo.getImporte_solicitado());
            ps.setLong(3, prestamo.getCuentaDeposito().getId());
            ps.setInt(4, prestamo.getCuotas());
            ps.setDate(5, prestamo.getFecha_solicitud());
            ps.setInt(6, prestamo.getAutorizacion());
            ps.setBoolean(7, prestamo.getEstado());
            exito = ps.executeUpdate() > 0;
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exito;
    }

    @Override
    public List<SolicitudPrestamo> obtenerTodos() {
        List<SolicitudPrestamo> lista = new ArrayList<>();
        Conexion conexion = new Conexion();
        Connection conn = conexion.Open();
        String sql = "SELECT * FROM SolicitudPrestamos";
        try {
            ClienteDaoImpl clienteDao = new ClienteDaoImpl();
            Datos.CuentaDao cuentaDao = new DatosImpl.CuentaDaoImpl();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SolicitudPrestamo sp = new SolicitudPrestamo();
                sp.setId_solicitud(rs.getInt("id_solicitud"));
                sp.setCliente(clienteDao.obtenerClientePorDni(rs.getLong("dni_cliente")));
                sp.setImporte_solicitado(rs.getLong("importe_solicitado"));
                sp.setCuotas(rs.getInt("cuotas"));
                sp.setFecha_solicitud(rs.getDate("fecha_solicitud"));
                sp.setAutorizacion(rs.getInt("autorizacion"));
                sp.setEstado(rs.getBoolean("estado"));
                sp.setCuentaDeposito(cuentaDao.obtenerCuentaPorId(rs.getInt("numero_cuenta_deposito")));
                lista.add(sp);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean actualizarAutorizacion(int idSolicitud, int nuevaAutorizacion) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.Open();
        boolean exito = false;
        String sql = "UPDATE SolicitudPrestamos SET autorizacion = ? WHERE id_solicitud = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, nuevaAutorizacion);
            ps.setInt(2, idSolicitud);
            exito = ps.executeUpdate() > 0;
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exito;
    }
}
