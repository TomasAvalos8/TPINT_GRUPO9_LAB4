package DatosImpl;

import Datos.TipoCuentaDao;
import Dominio.TipoCuenta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TipoCuentaDaoImpl implements TipoCuentaDao {
    @Override
    public List<TipoCuenta> obtenerTodos() {
        List<TipoCuenta> tipos = new ArrayList<>();
        Conexion cn = new Conexion();
        Connection conexion = cn.Open();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT id_tipo_cuenta, descripcion FROM TipoCuenta";
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                TipoCuenta tipo = new TipoCuenta();
                tipo.setIdTipoCuenta(rs.getInt("id_tipo_cuenta"));
                tipo.setDescripcion(rs.getString("descripcion"));
                tipos.add(tipo);
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
        return tipos;
    }
}
