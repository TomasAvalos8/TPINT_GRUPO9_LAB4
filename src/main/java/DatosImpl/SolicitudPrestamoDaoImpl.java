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
        String sql = "INSERT INTO SolicitudPrestamos (dni_cliente, importe_solicitado, importe_a_pagar, numero_cuenta_deposito, cuotas, fecha_solicitud, autorizacion, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, prestamo.getCliente().getDni());
            ps.setLong(2, prestamo.getImporte_solicitado());
            ps.setDouble(3, prestamo.getImporte_pagar_intereses());
            ps.setLong(4, prestamo.getCuentaDeposito().getId());
            ps.setInt(5, prestamo.getCuotas());
            ps.setDate(6, prestamo.getFecha_solicitud());
            ps.setInt(7, prestamo.getAutorizacion());
            ps.setBoolean(8, prestamo.getEstado());

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
                sp.setImporte_pagar_intereses(rs.getDouble("importe_a_pagar"));
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

    public SolicitudPrestamo obtenerSolicitudPorId(int idSolicitud){
        Conexion conexion = new Conexion();
        Connection conn = conexion.Open();
        SolicitudPrestamo solicitud = null;
        String sql = "SELECT * FROM SolicitudPrestamos WHERE id_solicitud = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idSolicitud);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                solicitud = new SolicitudPrestamo();
                solicitud.setId_solicitud(rs.getInt("id_solicitud"));
                solicitud.setCliente(new ClienteDaoImpl().obtenerClientePorDni(rs.getLong("dni_cliente")));
                solicitud.setImporte_solicitado((long) rs.getDouble("importe_solicitado"));
                solicitud.setCuotas(rs.getInt("cuotas"));
                solicitud.setFecha_solicitud(rs.getDate("fecha_solicitud"));
                solicitud.setAutorizacion(rs.getInt("autorizacion"));
                solicitud.setEstado(rs.getBoolean("estado"));
                solicitud.setCuentaDeposito(new DatosImpl.CuentaDaoImpl().obtenerCuentaPorId(rs.getInt("numero_cuenta_deposito")));
                solicitud.setImporte_pagar_intereses(rs.getDouble("importe_a_pagar"));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return solicitud;
    }

    @Override
    public List<SolicitudPrestamo> obtenerSolicitudesPorUsuario(Integer idUsuario) {
        List<SolicitudPrestamo> solicitudes = new ArrayList<>();
        Conexion cn = new Conexion();
        Connection conexion = cn.Open();
        String sql = "SELECT * FROM SolicitudPrestamos WHERE dni_cliente = ?";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SolicitudPrestamo solicitud = new SolicitudPrestamo();
                solicitud.setId_solicitud(rs.getInt("id_solicitud"));
                solicitud.setCliente(new ClienteDaoImpl().obtenerClientePorDni(rs.getLong("dni_cliente")));
                solicitud.setImporte_solicitado((long) rs.getDouble("importe_solicitado"));
                solicitud.setCuotas(rs.getInt("cuotas"));
                solicitud.setFecha_solicitud(rs.getDate("fecha_solicitud"));
                solicitud.setAutorizacion(rs.getInt("autorizacion"));
                solicitud.setEstado(rs.getBoolean("estado"));
                solicitud.setCuentaDeposito(new DatosImpl.CuentaDaoImpl().obtenerCuentaPorId(rs.getInt("numero_cuenta_deposito")));
                solicitud.setImporte_pagar_intereses(rs.getDouble("importe_a_pagar"));
                
                String estadoString;
                
                estadoString = rs.getInt("autorizacion") == 0 ? "Pendiente" :
                              rs.getInt("autorizacion") == 1 ? "Aprobada" :
                              rs.getInt("autorizacion") == 2 ? "Rechazada" : "";
                solicitud.setEstadoString(estadoString);
                
                solicitudes.add(solicitud);
            }
            rs.close();
            ps.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return solicitudes;
    }
}
