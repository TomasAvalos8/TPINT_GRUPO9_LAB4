package Dominio;

public class Localidad {
private int id_localidad;
private String descripcion;
private Provincia provincia;

public Localidad(int id_localidad, String descripcion, Provincia provincia) {
	super();
	this.id_localidad = id_localidad;
	this.descripcion = descripcion;
	this.provincia = provincia;
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
public Provincia getProvincia() {
	return provincia;
}
public void setProvincia(Provincia provincia) {
	this.provincia = provincia;
}
}
