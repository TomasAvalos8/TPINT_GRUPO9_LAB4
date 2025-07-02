package Dominio;

import java.time.LocalDate;

public class Cliente {
	private int dni;
	private String cuil;
	private String nombre;
	private String apellido;
	private String sexo;
	private String nacionalidad ;
	private LocalDate fecha_nacimiento;
	private String direccion;
	private Localidad localidad;
	private Provincia provincia;
	private String correo_electronico;
	private String telefono;
	private int IdUsuario;
	private boolean activo;
	
	public Cliente(int dni, String cuil, String nombre, String apellido, String sexo, String nacionalidad,
			LocalDate fecha_nacimiento, String direccion, Localidad localidad, Provincia provincia, String correo_electronico,
			String telefono, int  IdUsuario, boolean activo) {
		super();
		this.dni = dni;
		this.cuil = cuil;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.nacionalidad = nacionalidad;
		this.fecha_nacimiento = fecha_nacimiento;
		this.direccion = direccion;
		this.localidad = localidad;
		this.provincia = provincia;
		this.correo_electronico = correo_electronico;
		this.telefono = telefono;
		this.IdUsuario = IdUsuario;
		this.activo = activo;
	}
	
	public Cliente(int dni, String nombre, String apellido, String sexo, String nacionalidad) {
	    this.dni = dni;
	    this.nombre = nombre;
	    this.apellido = apellido;
	    this.sexo = sexo;
	    this.nacionalidad = nacionalidad;
	}
	public Cliente() {}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getCuil() {
		return cuil;
	}

	public void setCuil(String cuil) {
		this.cuil = cuil;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public LocalDate getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public String getCorreo_electronico() {
		return correo_electronico;
	}

	public void setCorreo_electronico(String correo_electronico) {
		this.correo_electronico = correo_electronico;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getIdUsuario() {
		return IdUsuario;
	}

	public void setIdUsuario(int IdUsuario) {
		this.IdUsuario = IdUsuario;
	}
	
	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}
