package Dominio;


public class Usuario {
	private int id_usuario;
	private String Usuario;
	private String contraseña;
	private TipoUsuario tipoUsuario; //1=admin, 2=cliente;
	
	// CONSTRUCTORES
	
	 public Usuario (String usuario, String contraseña, TipoUsuario tipoUsuario) {
	        this.Usuario = usuario;
	        this.contraseña = contraseña;
	        this.tipoUsuario = tipoUsuario;
	    }
	    
	 public Usuario() {}

	 
	 // GETS Y  SETS
	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	// Metodo ToString
	
	@Override
	public String toString() {
		return "Usuario [id_usuario=" + id_usuario + ", contraseña=" + contraseña + ", Usuario=" + Usuario
				+ ", tipoUsuario=" + tipoUsuario + "]";
	}
	
	
	
	
	
}


