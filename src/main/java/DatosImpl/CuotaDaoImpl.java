package DatosImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Datos.CuotaDao;
import Dominio.Cuota;

public class CuotaDaoImpl implements CuotaDao {
    @Override
    public boolean insertar(Cuota cuota) {

        Conexion cn = new Conexion();
        Connection conexion = cn.Open();
        boolean resultado = false;
        PreparedStatement ps = null;
        try {

            String sql = "INSERT INTO Cuotas (id_prestamo, numero_cuota, monto, fecha_pago, pagado) VALUES (?, ?, ?, ?, ?)";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, cuota.getPrestamo().getId_prestamo());
            ps.setInt(2, cuota.getNumeroCuota());
            ps.setDouble(3, cuota.getMonto());
            ps.setDate(4, new java.sql.Date(cuota.getFechaPago().getTime()));
            ps.setBoolean(5, cuota.isPagado());
            resultado = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (conexion != null) conexion.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultado;
    }


    @Override
    public boolean eliminarCuotasPorPrestamo(int id_prestamo) {
        Conexion cn = new Conexion();
        Connection conexion = cn.Open();
        boolean resultado = false;
        PreparedStatement ps = null;
        try {
            String sql = "DELETE FROM Cuotas WHERE id_prestamo = ?";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id_prestamo);
            resultado = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (conexion != null) conexion.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultado;
    }
}
