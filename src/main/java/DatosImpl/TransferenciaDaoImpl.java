package DatosImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import Datos.TransferenciaDao;

public class TransferenciaDaoImpl implements TransferenciaDao {

	public boolean Transferir(int CuentaSaliente, int CuentaDestino, Float monto, Date fecha) {
		String sqlSaldo = "SELECT saldo FROM cuenta WHERE id = ?";
	    String sqlDebito = "UPDATE cuenta SET saldo = saldo - ? WHERE id = ?";
	    String sqlCredito = "UPDATE cuenta SET saldo = saldo + ? WHERE id = ?";
	    String sqlMovimiento = "INSERT INTO movimiento (id_cuenta, fecha, concepto, importe, tipo) VALUES (?, ?, ?, ?, ?)";

	    Conexion cn = new Conexion();
	    Connection conexion = cn.Open();

	    PreparedStatement stmtSaldo = null;
	    PreparedStatement stmtDebito = null;
	    PreparedStatement stmtCredito = null;
	    PreparedStatement stmtMovimiento = null;

	    try {
	    	//inicio
	        conexion.setAutoCommit(false);

	        //Saldo
	        stmtSaldo = (PreparedStatement) conexion.prepareStatement(sqlSaldo);
	        stmtSaldo.setInt(1, CuentaSaliente);
	        ResultSet rs = stmtSaldo.executeQuery();
	        if (!rs.next() || rs.getFloat("saldo") < monto) {
	            return false;
	        }

	        //cuenta saliente
	        stmtDebito = (PreparedStatement) conexion.prepareStatement(sqlDebito);
	        stmtDebito.setFloat(1, monto);
	        stmtDebito.setInt(2, CuentaSaliente);
	        stmtDebito.executeUpdate();

	        //cuenta destino
	        stmtCredito = (PreparedStatement) conexion.prepareStatement(sqlCredito);
	        stmtCredito.setFloat(1, monto);
	        stmtCredito.setInt(2, CuentaDestino);
	        stmtCredito.executeUpdate();

	        //movimiento en cuenta saliente
	        stmtMovimiento = (PreparedStatement) conexion.prepareStatement(sqlMovimiento);
	        stmtMovimiento.setInt(1, CuentaSaliente);
	        stmtMovimiento.setDate(2, new java.sql.Date(fecha.getTime()));
	        stmtMovimiento.setString(3, "Transferencia a cuenta " + CuentaDestino);
	        stmtMovimiento.setFloat(4, -monto);
	        stmtMovimiento.setString(5, "Débito");
	        stmtMovimiento.executeUpdate();

	        //movimiento en cuenta destino
	        stmtMovimiento.setInt(1, CuentaDestino);
	        stmtMovimiento.setDate(2, new java.sql.Date(fecha.getTime()));
	        stmtMovimiento.setString(3, "Transferencia desde cuenta " + CuentaSaliente);
	        stmtMovimiento.setFloat(4, monto);
	        stmtMovimiento.setString(5, "Crédito");
	        stmtMovimiento.executeUpdate();

	        //confirmacion
	        conexion.commit();
	        return true;

	    } catch (Exception e) {
	        e.printStackTrace();
	        try {
	            if (conexion != null) conexion.rollback();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        return false;

	    } finally {
	        try {
	            if (stmtMovimiento != null) stmtMovimiento.close();
	            if (stmtCredito != null) stmtCredito.close();
	            if (stmtDebito != null) stmtDebito.close();
	            if (stmtSaldo != null) stmtSaldo.close();
	            if (conexion != null) conexion.setAutoCommit(true);
	            cn.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	}

}
