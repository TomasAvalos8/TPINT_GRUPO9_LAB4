package DatosImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Datos.TransferenciaDao;
import Dominio.Cuenta;

public class TransferenciaDaoImpl implements TransferenciaDao {

	public boolean Transferir(Cuenta CuentaSaliente, Cuenta CuentaDestino, float monto, Date fecha) {
	    String sqlSaldo = "SELECT saldo FROM cuenta WHERE id = ?";
	    String sqlDebito = "UPDATE cuenta SET saldo = saldo - ? WHERE id = ?";
	    String sqlCredito = "UPDATE cuenta SET saldo = saldo + ? WHERE id = ?";
	    String sqlMovimiento = "INSERT INTO movimiento (numero_cuenta, id_tipo_movimiento, detalle, monto, fecha) VALUES (?, ?, ?, ?, ?)";

	    Conexion cn = new Conexion();
	    Connection conexion = cn.Open();

	    PreparedStatement stmtSaldo = null;
	    PreparedStatement stmtDebito = null;
	    PreparedStatement stmtCredito = null;
	    PreparedStatement stmtMovimiento = null;

	    try {
	        conexion.setAutoCommit(false);

	        //Saldo
	        stmtSaldo = conexion.prepareStatement(sqlSaldo);
	        stmtSaldo.setInt(1, CuentaSaliente.getId());
	        ResultSet rs = stmtSaldo.executeQuery();
	        if (!rs.next() || rs.getFloat("saldo") < monto) {
	            return false;
	        }

	        //Debitar cuenta saliente
	        stmtDebito = conexion.prepareStatement(sqlDebito);
	        stmtDebito.setFloat(1, monto);
	        stmtDebito.setInt(2, CuentaSaliente.getId());
	        stmtDebito.executeUpdate();

	        //Acreditar cuenta destino
	        stmtCredito = conexion.prepareStatement(sqlCredito);
	        stmtCredito.setFloat(1, monto);
	        stmtCredito.setInt(2, CuentaDestino.getId());
	        stmtCredito.executeUpdate();

	        //Registrar movimiento débito (monto negativo)
	        stmtMovimiento = conexion.prepareStatement(sqlMovimiento);
	        stmtMovimiento.setInt(1, CuentaSaliente.getId());
	        stmtMovimiento.setInt(2, 1); // 1 = Débito
	        stmtMovimiento.setString(3, "Transferencia a cuenta " + CuentaDestino.getId());
	        stmtMovimiento.setFloat(4, -monto);
	        stmtMovimiento.setDate(5, new java.sql.Date(fecha.getTime()));
	        stmtMovimiento.executeUpdate();

	        //Registrar movimiento crédito (monto positivo)
	        stmtMovimiento.setInt(1, CuentaDestino.getId());
	        stmtMovimiento.setInt(2, 2); // 2 = Crédito
	        stmtMovimiento.setString(3, "Transferencia desde cuenta " + CuentaSaliente.getId());
	        stmtMovimiento.setFloat(4, monto);
	        stmtMovimiento.setDate(5, new java.sql.Date(fecha.getTime()));
	        stmtMovimiento.executeUpdate();

	        //Confirmar transacción
	        conexion.commit();
	        return true;

	    } catch (SQLException e) {
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
