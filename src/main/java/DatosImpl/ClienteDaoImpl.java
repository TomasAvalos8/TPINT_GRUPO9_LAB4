package DatosImpl;

import Datos.ClienteDao;

public class ClienteDaoImpl implements ClienteDao {
private Conexion conexion;
	@Override
	public boolean insertarCliente() {
		
		boolean estado= true;
		
		conexion= new Conexion();
		conexion.Open();
		//Falta realizar el proceso de insertar el cliente a la base
		return estado;
	}

}
