package DatosImpl;

import java.sql.Connection;
import java.sql.SQLException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Datos.CuentaDao;
import Dominio.Cuenta;

public class CuentaDaoImpl implements CuentaDao {

	private static final String insertar = "INSERT INTO cuentas (dni, CBU, creacion, tipo, saldo, estado) VALUES (?, ?, ?, ?, ?, ?)";
	
	public boolean crearCuenta(Cuenta cuenta) {
		
		PreparedStatement statement = null;
	    Conexion cn = new Conexion();
	    Connection conexion = cn.Open();
	    boolean isInsertExitoso = false;
	    try {
	        statement = conexion.prepareStatement(insertar);
	        statement.setInt(1, cuenta.getDni());
	        statement.setString(2, cuenta.getCBU());
	        statement.setDate(3, new java.sql.Date(cuenta.getCreacion().getTime()));
	        statement.setInt(4, cuenta.getTipo());
	        statement.setFloat(5, cuenta.getSaldo());
	        statement.setBoolean(6, cuenta.isEstado());

	        int filas = statement.executeUpdate();
	        isInsertExitoso = filas > 0;

	    } catch (SQLException e) {
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
	        String sql = "SELECT COALESCE(MAX(id), 0) AS maxId FROM cuenta";
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
	
}