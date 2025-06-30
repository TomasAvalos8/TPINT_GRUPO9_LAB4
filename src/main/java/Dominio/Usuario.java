package Dominio;

import java.time.LocalDate;

public class Usuario {
	private int id_usuario;
	private String Usuario;
	private String contraseña;
	private LocalDate fechaAlta;
	private TipoUsuario tipoUsuario; //1=admin, 2=cliente;
	private boolean activo;
	
	// CONSTRUCTORES
	
	 public Usuario (String usuario, String contraseña, TipoUsuario tipoUsuario,LocalDate fechaAlta,boolean activo) {
	        this.Usuario = usuario;
	        this.contraseña = contraseña;
	        this.tipoUsuario = tipoUsuario;
	        this.fechaAlta = fechaAlta;
	        this.activo=activo;
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
	
	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "Usuario [id_usuario=" + id_usuario + ", contraseña=" + contraseña + ", Usuario=" + Usuario
				+ ", tipoUsuario=" + tipoUsuario + "]";
	}
	
	
	
	
	
}


