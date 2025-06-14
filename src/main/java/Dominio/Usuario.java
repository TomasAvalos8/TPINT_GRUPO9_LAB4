package Dominio;

public class Usuario {
	public static final int admin = 1;
	public static final int cliente = 2;
	
	private String Usuario;
	private int tipoUsuario;//1=admin, 2=cliente;
	
	public Usuario (String usuario, int tipoUsuario) {
		this.Usuario = usuario;
		this.tipoUsuario= tipoUsuario;
	}

	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	public int getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
}
