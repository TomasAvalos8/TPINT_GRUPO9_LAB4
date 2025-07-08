package DatosImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Datos.TransferenciaDao;
import Dominio.Cuenta;

public class TransferenciaDaoImpl implements TransferenciaDao {

	public int transferir(Cuenta CuentaSaliente, Cuenta CuentaDestino, float monto, Date fecha) {
	    if (CuentaSaliente.getId() == CuentaDestino.getId()) {
	        return 2;
	    }
	    
	    if (!CuentaSaliente.isEstado() || !CuentaDestino.isEstado()) {
	        return 3;
	    }
	    
	    String sqlSaldo = "SELECT saldo FROM cuenta WHERE id = ?";
	    String sqlDebito = "UPDATE cuenta SET saldo = saldo - ? WHERE id = ?";
	    String sqlCredito = "UPDATE cuenta SET saldo = saldo + ? WHERE id = ?";
	    String sqlMovimiento = "INSERT INTO movimiento (numero_cuenta, id_tipo_movimiento, detalle, monto, fecha) VALUES (?, ?, ?, ?, ?)";
	    String sqlTransferencia = "INSERT INTO transferencias (numero_cuenta_saliente, numero_cuenta_destino, monto, fecha) VALUES (?, ?, ?, ?)";

	    Conexion cn = new Conexion();
	    Connection conexion = cn.Open();

	    PreparedStatement stmtSaldo = null;
	    PreparedStatement stmtDebito = null;
	    PreparedStatement stmtCredito = null;
	    PreparedStatement stmtMovimiento = null;
	    PreparedStatement stmtTransferencia = null;

	    try {
	        conexion.setAutoCommit(false);

	        //Verificar saldo
	        stmtSaldo = conexion.prepareStatement(sqlSaldo);
	        stmtSaldo.setInt(1, CuentaSaliente.getId());
	        ResultSet rs = stmtSaldo.executeQuery();
	        if (!rs.next() || rs.getFloat("saldo") < monto) {
	            return 1;
	        }

	        //Debito en cuenta origen
	        stmtDebito = conexion.prepareStatement(sqlDebito);
	        stmtDebito.setFloat(1, monto);
	        stmtDebito.setInt(2, CuentaSaliente.getId());
	        stmtDebito.executeUpdate();

	        //Credito en cuenta destino
	        stmtCredito = conexion.prepareStatement(sqlCredito);
	        stmtCredito.setFloat(1, monto);
	        stmtCredito.setInt(2, CuentaDestino.getId());
	        stmtCredito.executeUpdate();

	        //Registrar movimiento debito
	        stmtMovimiento = conexion.prepareStatement(sqlMovimiento);
	        stmtMovimiento.setInt(1, CuentaSaliente.getId());
	        stmtMovimiento.setInt(2, 1); // 1 = Débito
	        stmtMovimiento.setString(3, "Transferencia a cuenta " + CuentaDestino.getId());
	        stmtMovimiento.setFloat(4, -monto);
	        stmtMovimiento.setDate(5, new java.sql.Date(fecha.getTime()));
	        stmtMovimiento.executeUpdate();

	        //Registrar movimiento credito
	        stmtMovimiento.setInt(1, CuentaDestino.getId());
	        stmtMovimiento.setInt(2, 2); // 2 = Crédito
	        stmtMovimiento.setString(3, "Transferencia desde cuenta " + CuentaSaliente.getId());
	        stmtMovimiento.setFloat(4, monto);
	        stmtMovimiento.setDate(5, new java.sql.Date(fecha.getTime()));
	        stmtMovimiento.executeUpdate();

	        // Insertar en tabla transferencias
	        stmtTransferencia = conexion.prepareStatement(sqlTransferencia);
	        stmtTransferencia.setInt(1, CuentaSaliente.getId());
	        stmtTransferencia.setInt(2, CuentaDestino.getId());
	        stmtTransferencia.setFloat(3, monto);
	        stmtTransferencia.setDate(4, new java.sql.Date(fecha.getTime()));
	        stmtTransferencia.executeUpdate();

	        conexion.commit();
	        return 0;

	    } catch (SQLException e) {
	        try {
	            if (conexion != null) conexion.rollback();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        e.printStackTrace();
	        return 4;
	    } finally {
	        try {
	            if (stmtTransferencia != null) stmtTransferencia.close();
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
