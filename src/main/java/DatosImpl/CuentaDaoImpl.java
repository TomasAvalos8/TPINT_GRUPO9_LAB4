package DatosImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Datos.CuentaDao;
import Dominio.Cuenta;
import Dominio.TipoCuenta;
import Excepciones.ClienteNoExisteException;

public class CuentaDaoImpl implements CuentaDao {

	private Conexion conexion;
	public boolean crearCuenta(Cuenta cuenta) throws ClienteNoExisteException {
        PreparedStatement statement = null;
        Conexion cn = new Conexion();
        Connection conexion = cn.Open();
        boolean isInsertExitoso = false;
        try {
            String checkCliente = "SELECT 1 FROM Cliente WHERE dni = ?";
            PreparedStatement psCliente = conexion.prepareStatement(checkCliente);
            psCliente.setInt(1, cuenta.getDni());
            ResultSet rsCliente = psCliente.executeQuery();
            if (!rsCliente.next()) {
                rsCliente.close();
                psCliente.close();
                throw new ClienteNoExisteException("El cliente con DNI ingresado no existe.");
            }
            rsCliente.close();
            psCliente.close();

            String insertar = "INSERT INTO Cuenta (id, dni_cliente, fecha_creacion, tipo_cuenta, CBU, saldo, activo) VALUES (?, ?, ?, ?, ?, ?, ?)";
            statement = conexion.prepareStatement(insertar);
            statement.setLong(1, cuenta.getId());
            statement.setInt(2, cuenta.getDni());
            statement.setDate(3, new java.sql.Date(cuenta.getCreacion().getTime()));
            statement.setInt(4, cuenta.getTipo().getIdTipoCuenta());
            statement.setString(5, cuenta.getCBU());
            statement.setFloat(6, cuenta.getSaldo());
            statement.setBoolean(7, cuenta.isEstado());

            int filas = statement.executeUpdate();
            isInsertExitoso = filas > 0;
        } catch (ClienteNoExisteException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isInsertExitoso;
	}

	public int obtenerSiguienteIdCuenta() {
		int siguienteId = 1;
	    Conexion conexion = new Conexion();
	    try {
	        conexion.Open();
	        String sql = "SELECT COALESCE(MAX(id), 0) AS maxId FROM Cuenta";
	        ResultSet rs = conexion.query(sql);
	        if (rs.next()) {
	            siguienteId = rs.getInt("maxId") + 1;
	        }
	        rs.close();
	        conexion.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return siguienteId;
	}
	
	
    @Override
    public List<Cuenta> obtenerTodasLasCuentas() {
        conexion = new Conexion();
        conexion.Open();
        List<Cuenta> lista = new ArrayList<Cuenta>();

        String query = "SELECT c.id, c.dni_cliente, c.fecha_creacion, c.CBU, c.saldo, c.tipo_cuenta, tc.descripcion AS descripcion_tipo"
        		+ " FROM Cuenta c "
        		+ "LEFT JOIN TipoCuenta tc ON c.tipo_cuenta = tc.id_tipo_cuenta "
        		+ "WHERE c.activo = 1;";

        try {
            ResultSet rs = conexion.query(query);

            while(rs.next()) {
                Cuenta nCuenta = new Cuenta();
                nCuenta.setId(rs.getInt("id"));
                nCuenta.setDni(rs.getInt("dni_cliente"));
                nCuenta.setCreacion(rs.getDate("fecha_creacion"));
                nCuenta.setCBU(rs.getString("CBU"));
                nCuenta.setSaldo(rs.getFloat("saldo")); 

                TipoCuenta tipoCuenta = new TipoCuenta();
                int idTipo = rs.getInt("tipo_cuenta");
                String descripcionTipo = rs.getString("descripcion_tipo");
                
                tipoCuenta.setIdTipoCuenta(idTipo);
                tipoCuenta.setDescripcion(descripcionTipo);
                nCuenta.setTipo(tipoCuenta);

                lista.add(nCuenta);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.close();
        }

        return lista;
    }

    @Override
    public boolean eliminarCuenta(int id) {
        PreparedStatement ps = null;
        Conexion cn = new Conexion();
        Connection conexion = cn.Open();
        boolean eliminado = false;
        try {
            String sql = "UPDATE Cuenta SET activo = false WHERE id = ?";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            eliminado = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return eliminado;
    }

    @Override
    public boolean actualizarCuenta(Cuenta cuenta) {
        PreparedStatement ps = null;
        Conexion cn = new Conexion();
        Connection conexion = cn.Open();
        boolean actualizado = false;
        try {
            String sql = "UPDATE Cuenta SET dni_cliente=?, fecha_creacion=?, tipo_cuenta=?, CBU=?, saldo=? WHERE id=?";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, cuenta.getDni());
            ps.setDate(2, new java.sql.Date(cuenta.getCreacion().getTime()));
            ps.setInt(3, cuenta.getTipo().getIdTipoCuenta());
            ps.setString(4, cuenta.getCBU());
            ps.setFloat(5, cuenta.getSaldo());
            ps.setInt(6, cuenta.getId());
            actualizado = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return actualizado;
    }

	@Override
	public List<Cuenta> obtenerCuentasPorTipo(int idTipoCuenta) {
	    conexion = new Conexion();
	    conexion.Open();
	    List<Cuenta> lista = new ArrayList<Cuenta>();

	    String query = "SELECT c.id, c.dni_cliente, c.fecha_creacion, c.CBU, c.saldo, c.tipo_cuenta, tc.descripcion AS descripcion_tipo " +
	                   "FROM Cuenta c LEFT JOIN TipoCuenta tc ON c.tipo_cuenta = tc.id_tipo_cuenta " +
	                   "WHERE c.activo = 1 AND c.tipo_cuenta = ?";

	    try {
	        PreparedStatement ps = conexion.Open().prepareStatement(query);
	        ps.setInt(1, idTipoCuenta);
	        ResultSet rs = ps.executeQuery();

	        while(rs.next()) {
	            Cuenta nCuenta = new Cuenta();
	            nCuenta.setId(rs.getInt("id"));
	            nCuenta.setDni(rs.getInt("dni_cliente"));
	            nCuenta.setCreacion(rs.getDate("fecha_creacion"));
	            nCuenta.setCBU(rs.getString("CBU"));
	            nCuenta.setSaldo(rs.getFloat("saldo"));

	            TipoCuenta tipoCuenta = new TipoCuenta();
	            int idTipo = rs.getInt("tipo_cuenta");
	            String descripcionTipo = rs.getString("descripcion_tipo");
	            
	            tipoCuenta.setIdTipoCuenta(idTipo);
	            tipoCuenta.setDescripcion(descripcionTipo);
	            nCuenta.setTipo(tipoCuenta);

	            lista.add(nCuenta);
	        }
	        
	        rs.close();
	        ps.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        conexion.close();
	    }

	    return lista;

	}

    @Override
    public List<Cuenta> obtenerCuentasPorDni(int dni) {
        List<Cuenta> lista = new ArrayList<>();
        Conexion cn = new Conexion();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT c.id, c.dni_cliente, c.fecha_creacion, c.CBU, c.saldo, c.tipo_cuenta, tc.descripcion AS descripcion_tipo " +
                "FROM Cuenta c LEFT JOIN TipoCuenta tc ON c.tipo_cuenta = tc.id_tipo_cuenta WHERE c.dni_cliente = ? AND c.activo = 1;";
        try {
            conexion = cn.Open();
            ps = conexion.prepareStatement(query);
            ps.setInt(1, dni);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cuenta nCuenta = new Cuenta();
                nCuenta.setId(rs.getInt("id"));
                nCuenta.setDni(rs.getInt("dni_cliente"));
                nCuenta.setCBU(rs.getString("CBU"));
                nCuenta.setSaldo(rs.getFloat("saldo"));
                nCuenta.setCreacion(rs.getDate("fecha_creacion"));
                TipoCuenta tipoCuenta = new TipoCuenta();
                tipoCuenta.setIdTipoCuenta(rs.getInt("tipo_cuenta"));
                tipoCuenta.setDescripcion(rs.getString("descripcion_tipo"));
                nCuenta.setTipo(tipoCuenta);
                lista.add(nCuenta);
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
        return lista;
    }

	@Override
	public Cuenta obtenerCuentaPorId(int id) {
	    Cuenta cuenta = null;
	    Conexion cn = new Conexion();
	    Connection conexion = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    String query = "SELECT c.id, c.dni_cliente, c.fecha_creacion, c.CBU, c.saldo, c.tipo_cuenta, tc.descripcion AS descripcion_tipo " +
	            "FROM Cuenta c LEFT JOIN TipoCuenta tc ON c.tipo_cuenta = tc.id_tipo_cuenta WHERE c.id = ? AND c.activo = 1;";
	    try {
	        conexion = cn.Open();
	        ps = conexion.prepareStatement(query);
	        ps.setInt(1, id);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            cuenta = new Cuenta();
	            cuenta.setId(rs.getInt("id"));
	            cuenta.setDni(rs.getInt("dni_cliente"));
	            cuenta.setCBU(rs.getString("CBU"));
	            cuenta.setSaldo(rs.getFloat("saldo"));
	            cuenta.setCreacion(rs.getDate("fecha_creacion"));
	            TipoCuenta tipoCuenta = new TipoCuenta();
	            tipoCuenta.setIdTipoCuenta(rs.getInt("tipo_cuenta"));
	            tipoCuenta.setDescripcion(rs.getString("descripcion_tipo"));
	            cuenta.setTipo(tipoCuenta);
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
	    return cuenta;
	}

	@Override
	public void depositarEnCuenta(int idCuenta, double monto) {
        Conexion cn = new Conexion();
        Connection conexion = cn.Open();
        try {
            String sql = "UPDATE Cuenta SET saldo = saldo + ? WHERE id = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setDouble(1, monto);
            ps.setInt(2, idCuenta);
            ps.executeUpdate();
            ps.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	@Override
	public List<Cuenta> obtenerCuentasPorIdUsuario(int idUsuario) {
		List<Cuenta> lista = new ArrayList<>();
	    Conexion cn = new Conexion();
	    Connection conexion = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    String query = "SELECT c.id, c.dni_cliente, c.fecha_creacion, c.CBU, c.saldo, c.tipo_cuenta, " +
	                   "tc.descripcion AS descripcion_tipo " +
	                   "FROM Cuenta c " +
	                   "INNER JOIN Cliente cl ON c.dni_cliente = cl.dni " +
	                   "LEFT JOIN TipoCuenta tc ON c.tipo_cuenta = tc.id_tipo_cuenta " +
	                   "WHERE cl.id_usuario = ? AND c.activo = 1";

	    try {
	        conexion = cn.Open();
	        ps = conexion.prepareStatement(query);
	        ps.setInt(1, idUsuario);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            Cuenta nCuenta = new Cuenta();
	            nCuenta.setId(rs.getInt("id"));
	            nCuenta.setDni(rs.getInt("dni_cliente"));
	            nCuenta.setCBU(rs.getString("CBU"));
	            nCuenta.setSaldo(rs.getFloat("saldo"));
	            nCuenta.setCreacion(rs.getDate("fecha_creacion"));

	            TipoCuenta tipoCuenta = new TipoCuenta();
	            tipoCuenta.setIdTipoCuenta(rs.getInt("tipo_cuenta"));
	            tipoCuenta.setDescripcion(rs.getString("descripcion_tipo"));

	            nCuenta.setTipo(tipoCuenta);
	            lista.add(nCuenta);
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
	    
	    return lista;
	}

	@Override
	public Cuenta obtenerCuentaPorCBU(String cbu) {
		Cuenta cuenta = null;
	    Conexion cn = new Conexion();
	    Connection conexion = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    String query = "SELECT c.id, c.dni_cliente, c.fecha_creacion, c.CBU, c.saldo, c.tipo_cuenta, tc.descripcion AS descripcion_tipo " +
	                   "FROM Cuenta c LEFT JOIN TipoCuenta tc ON c.tipo_cuenta = tc.id_tipo_cuenta WHERE c.CBU = ? AND c.activo = 1;";
	    try {
	        conexion = cn.Open();
	        ps = conexion.prepareStatement(query);
	        ps.setString(1, cbu);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            cuenta = new Cuenta();
	            cuenta.setId(rs.getInt("id"));
	            cuenta.setDni(rs.getInt("dni_cliente"));
	            cuenta.setCBU(rs.getString("CBU"));
	            cuenta.setSaldo(rs.getFloat("saldo"));
	            cuenta.setCreacion(rs.getDate("fecha_creacion"));
	            TipoCuenta tipoCuenta = new TipoCuenta();
	            tipoCuenta.setIdTipoCuenta(rs.getInt("tipo_cuenta"));
	            tipoCuenta.setDescripcion(rs.getString("descripcion_tipo"));
	            cuenta.setTipo(tipoCuenta);
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
	    return cuenta;
	}
}