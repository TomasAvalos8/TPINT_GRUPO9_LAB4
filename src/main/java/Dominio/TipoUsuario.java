package Dominio;

public class TipoUsuario {
	private int idTipoUsuario;
	private String descripcion;
	
	
	// Constructores
	
    public TipoUsuario() {
    	
    }

    public TipoUsuario(int id, String descripcion) {
        this.idTipoUsuario = id;
        this.descripcion = descripcion;
    }
    
    // GETS Y SETS

	public int getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(int idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "TipoUsuario [idTipoUsuario=" + idTipoUsuario + ", descripcion=" + descripcion + "]";
	}
    
    
    

}
