package Dominio;

public class Localidad {
private int id_localidad;
private String descripcion;
private int id_provincia;

public Localidad(int id_localidad, String descripcion, int id_provincia) {
	super();
	this.id_localidad = id_localidad;
	this.descripcion = descripcion;
	this.id_provincia = id_provincia;
}

public Localidad() {}

public int getId_localidad() {
	return id_localidad;
}
public void setId_localidad(int id_localidad) {
	this.id_localidad = id_localidad;
}
public String getDescripcion() {
	return descripcion;
}
public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}
public int getId_provincia() {
	return id_provincia;
}
public void setId_provincia(int id_provincia) {
	this.id_provincia = id_provincia;
}
}
