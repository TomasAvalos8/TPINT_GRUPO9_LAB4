package DatosImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	//private String pass = "password";
	private String pass = "root";
	private String dbName = "BancoDB"; //poner nombre de tu base
	
	protected Connection connection;
	
	
	public Connection Open() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection(host + dbName + "?useSSL=false", user, pass);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.connection;
		
	}
	
	
	public ResultSet query(String query)
	{
		Statement st;
		ResultSet rs=null;
		try
		{
			st= connection.createStatement();
			rs= st.executeQuery(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	public boolean execute(String query)
	{
		Statement st;
		boolean save = true;
		try {
			st = connection.createStatement();
		    st.executeUpdate(query);
		}
		catch(SQLException e)
		{
			save = false;
			e.printStackTrace();
		}
		return save;
	}
	
	public boolean close()
	{
		boolean ok=true;
		try {
			connection.close();
		}
		catch(Exception e)
		{
			ok= false;
			e.printStackTrace();
		}
		return ok;
	}
	//modificacion del execute
	public ResultSet executeQuery(String query) {
	    ResultSet rs = null;
	    try {
	        Statement stmt = connection.createStatement();
	        rs = stmt.executeQuery(query);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return rs;
	}
	
	//Devuelve el id del usuario generado
	
	public int executeDevuelveIdUsuario(String query) {
	    int idGenerado = -1;
	    try {
	        PreparedStatement pst = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
	        pst.executeUpdate();
	        ResultSet rs = pst.getGeneratedKeys();
	        if (rs.next()) {
	            idGenerado = rs.getInt(1); 
	        }
	        rs.close();
	        pst.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return idGenerado;
	}


	
}
