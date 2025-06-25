package Dominio;

import java.time.LocalDate;

public class Usuario {
	private int id_usuario;
	private String Usuario;
	private String contraseña;
	private LocalDate fechaAlta;
	private TipoUsuario tipoUsuario; //1=admin, 2=cliente;
	
	// CONSTRUCTORES
	
	 public Usuario (String usuario, String contraseña, TipoUsuario tipoUsuario,LocalDate fechaAlta) {
	        this.Usuario = usuario;
	        this.contraseña = contraseña;
	        this.tipoUsuario = tipoUsuario;
	        this.fechaAlta = fechaAlta;
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

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}
	
	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	// Metodo ToString
	
	@Override
	public String toString() {
		return "Usuario [id_usuario=" + id_usuario + ", contraseña=" + contraseña + ", Usuario=" + Usuario
				+ ", tipoUsuario=" + tipoUsuario + "]";
	}
	
	
	
	
	
}


