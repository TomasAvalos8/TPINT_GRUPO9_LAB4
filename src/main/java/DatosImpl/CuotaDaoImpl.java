package DatosImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Datos.CuotaDao;
import Dominio.Cuenta;
import Dominio.Cuota;
import Dominio.Prestamo;
import Dominio.TipoCuenta;

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

    @Override
    public List<Cuota> obtenerCuotasPorCliente(int dni_cliente) {
        Conexion cn = new Conexion();
        Connection conexion = cn.Open();
        List<Cuota> listaCuotas = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT c.id, c.numero_cuota, c.monto, c.fecha_pago, c.pagado, p.*, cli.dni, cli.nombre, cli.apellido, cli.telefono, cli.correo_electronico, cu.id AS numero_cuenta, cu.saldo, cu.fecha_creacion, cu.tipo_cuenta, cu.CBU, cu.activo FROM Cuotas c JOIN Prestamo p ON c.id_prestamo = p.id_prestamo JOIN Cliente cli ON p.dni_cliente = cli.dni JOIN Cuenta cu ON p.numero_cuenta = cu.id WHERE p.dni_cliente = ?";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, dni_cliente);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cuota cuota = new Cuota();
                cuota.setId(rs.getInt("id"));
                cuota.setNumeroCuota(rs.getInt("numero_cuota"));
                cuota.setMonto(rs.getDouble("monto"));
                cuota.setFechaPago(rs.getDate("fecha_pago"));
                cuota.setPagado(rs.getBoolean("pagado"));

                Prestamo prestamo = new Prestamo();
                prestamo.setId_prestamo(rs.getInt("id_prestamo"));
                prestamo.setFecha_alta(rs.getDate("fecha_alta"));
                prestamo.setCuotas(rs.getInt("cuotas"));
                prestamo.setImporte_pagar_por_mes(rs.getDouble("importe_pagar_por_mes"));
                prestamo.setImporte_total_intereses(rs.getDouble("importe_pagar_intereses"));
                prestamo.setPlazo_pago_meses(rs.getInt("plazo_pago_meses"));
                prestamo.setImporte_solicitado(rs.getDouble("importe_solicitado"));
                prestamo.setActivo(rs.getBoolean("activo"));;



                Cuenta cuenta = new Cuenta();
                cuenta.setId(rs.getInt("numero_cuenta"));
                System.out.println("CUENTA: " + cuenta.getId());
                prestamo.setCuenta(cuenta);
                cuota.setPrestamo(prestamo);

                System.out.println("Prestamo: " + prestamo.getId_prestamo() + ", Cuenta: " + prestamo.getCuenta().getId());

                listaCuotas.add(cuota);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conexion != null) conexion.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listaCuotas;
    }

    @Override
    public boolean actualizarCuota(Cuota cuota) {
        Conexion cn = new Conexion();
        Connection conexion = cn.Open();
        boolean resultado = false;
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE Cuotas SET pagado = ? WHERE id = ?";
            ps = conexion.prepareStatement(sql);
            ps.setBoolean(1, cuota.isPagado());
            ps.setInt(2, cuota.getId());
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
    public Cuota obtenerCuotaPorId(int id) {
        Conexion cn = new Conexion();
        Connection conexion = cn.Open();
        Cuota cuota = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT c.id, c.numero_cuota, c.monto, c.fecha_pago, c.pagado, p.*, cu.id AS numero_cuenta, cu.saldo, cu.fecha_creacion, cu.tipo_cuenta, cu.CBU, cu.activo, cu.dni_cliente FROM Cuotas c JOIN Prestamo p ON c.id_prestamo = p.id_prestamo JOIN Cuenta cu ON p.numero_cuenta = cu.id WHERE c.id = ?";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                cuota = new Cuota();
                cuota.setId(rs.getInt("id"));
                cuota.setNumeroCuota(rs.getInt("numero_cuota"));
                cuota.setMonto(rs.getDouble("monto"));
                cuota.setFechaPago(rs.getDate("fecha_pago"));
                cuota.setPagado(rs.getBoolean("pagado"));
                Prestamo prestamo = new Prestamo();
                prestamo.setId_prestamo(rs.getInt("id_prestamo"));
                prestamo.setFecha_alta(rs.getDate("fecha_alta"));
                prestamo.setCuotas(rs.getInt("cuotas"));
                prestamo.setImporte_pagar_por_mes(rs.getDouble("importe_pagar_por_mes"));
                prestamo.setImporte_total_intereses(rs.getDouble("importe_pagar_intereses"));
                prestamo.setPlazo_pago_meses(rs.getInt("plazo_pago_meses"));
                prestamo.setImporte_solicitado(rs.getDouble("importe_solicitado"));
                prestamo.setActivo(rs.getBoolean("activo"));
                Cuenta cuenta = new Cuenta();
                cuenta.setId(rs.getInt("numero_cuenta"));
                cuenta.setDni(rs.getInt("dni_cliente"));
                cuenta.setCBU(rs.getString("CBU"));
                cuenta.setCreacion(rs.getDate("fecha_creacion"));
                cuenta.setTipo(new TipoCuenta(rs.getInt("tipo_cuenta"), null));
                cuenta.setSaldo(rs.getFloat("saldo"));
                cuenta.setEstado(rs.getBoolean("activo"));
                prestamo.setCuenta(cuenta);
                cuota.setPrestamo(prestamo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conexion != null) conexion.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cuota;
    }
}
