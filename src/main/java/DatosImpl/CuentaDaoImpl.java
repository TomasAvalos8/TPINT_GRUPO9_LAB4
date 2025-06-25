package DatosImpl;

import java.sql.Connection;
import java.sql.SQLException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Datos.CuentaDao;
import Dominio.Cuenta;
import Excepciones.ClienteNoExisteException;

public class CuentaDaoImpl implements CuentaDao {

	private static final String insertar = "INSERT INTO Cuenta (id, dni_cliente, fecha_creacion, tipo_cuenta, CBU, saldo) VALUES (?, ?, ?, ?, ?, ?)";
	
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
			System.out.println("El cliente");
	        if (!rsCliente.next()) {
				System.out.println("El cliente no existe");
	            rsCliente.close();
	            psCliente.close();
	            throw new ClienteNoExisteException("El cliente con DNI ingresado no existe.");
	        }
	        rsCliente.close();
	        psCliente.close();

	        
	        statement = conexion.prepareStatement(insertar);
	        statement.setLong(1, cuenta.getId());
	        statement.setInt(2, cuenta.getDni());
	        statement.setDate(3, new java.sql.Date(cuenta.getCreacion().getTime()));
	        statement.setInt(4, cuenta.getTipo());
	        statement.setString(5, cuenta.getCBU());
	        statement.setFloat(6, cuenta.getSaldo());

	        int filas = statement.executeUpdate();
	        isInsertExitoso = filas > 0;

	    } catch (Exception e) {
	    	System.out.println("catch");
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
	
	
    public String crearCuentaConMensaje(Cuenta cuenta) {
        PreparedStatement statement = null;
        Conexion cn = new Conexion();
        Connection conexion = cn.Open();
        try {
            
            String checkCliente = "SELECT 1 FROM Cliente WHERE dni = ?";
            PreparedStatement psCliente = conexion.prepareStatement(checkCliente);
            psCliente.setInt(1, cuenta.getDni());
            ResultSet rsCliente = psCliente.executeQuery();
            if (!rsCliente.next()) {
                rsCliente.close();
                psCliente.close();
                return "El cliente no existe";
            }
            rsCliente.close();
            psCliente.close();

            
            String checkTipo = "SELECT 1 FROM TipoCuenta WHERE id_tipo_cuenta = ?";
            PreparedStatement psTipo = conexion.prepareStatement(checkTipo);
            psTipo.setInt(1, cuenta.getTipo());
            ResultSet rsTipo = psTipo.executeQuery();
            if (!rsTipo.next()) {
                rsTipo.close();
                psTipo.close();
                return "El tipo de cuenta no existe";
            }
            rsTipo.close();
            psTipo.close();

            
            statement = conexion.prepareStatement(insertar);
            statement.setLong(1, cuenta.getId());
            statement.setInt(2, cuenta.getDni());
            statement.setDate(3, new java.sql.Date(cuenta.getCreacion().getTime()));
            statement.setInt(4, cuenta.getTipo());
            statement.setString(5, cuenta.getCBU());
            statement.setFloat(6, cuenta.getSaldo());

            int filas = statement.executeUpdate();
            if (filas > 0) {
                return null; 
            } else {
                return "No se pudo registrar la cuenta";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error en la base de datos: " + e.getMessage();
        } finally {
            try {
                if (statement != null) statement.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public java.util.List<Cuenta> obtenerTodasLasCuentas() {
        java.util.List<Cuenta> lista = new java.util.ArrayList<>();
        Conexion cn = new Conexion();
        Connection conexion = cn.Open();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT id, dni_cliente, fecha_creacion, tipo_cuenta, CBU, saldo FROM Cuenta";
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setId(rs.getInt("id"));
                cuenta.setDni(rs.getInt("dni_cliente"));
                cuenta.setCreacion(rs.getDate("fecha_creacion"));
                cuenta.setTipo(rs.getInt("tipo_cuenta"));
                cuenta.setCBU(rs.getString("CBU"));
                cuenta.setSaldo(rs.getFloat("saldo"));
                cuenta.setEstado(true); 
                lista.add(cuenta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
            String sql = "DELETE FROM Cuenta WHERE id = ?";
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
            ps.setInt(3, cuenta.getTipo());
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
}