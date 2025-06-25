package Dominio;

import java.time.LocalDate;

public class Cliente {
	private int dni;
	private int cuil;
	private String nombre;
	private String apellido;
	private String sexo;
	private String nacionalidad ;
	private LocalDate fecha_nacimiento;
	private String direccion;
	private int id_localidad;
	private int id_provincia;
	private String correo_electronico;
	private String telefono;
	private int IdUsuario;
	private boolean activo;
	
	public Cliente(int dni, int cuil, String nombre, String apellido, String sexo, String nacionalidad,
			LocalDate fecha_nacimiento, String direccion, int id_localidad, int id_provincia, String correo_electronico,
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
		this.id_localidad = id_localidad;
		this.id_provincia = id_provincia;
		this.correo_electronico = correo_electronico;
		this.telefono = telefono;
		this.IdUsuario = IdUsuario;
		this.activo = activo;
	}
	public Cliente() {}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public int getCuil() {
		return cuil;
	}

	public void setCuil(int cuil) {
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

	public int getId_localidad() {
		return id_localidad;
	}

	public void setId_localidad(int id_localidad) {
		this.id_localidad = id_localidad;
	}

	public int getId_provincia() {
		return id_provincia;
	}

	public void setId_provincia(int id_provincia) {
		this.id_provincia = id_provincia;
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
