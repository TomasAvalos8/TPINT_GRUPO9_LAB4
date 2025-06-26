package Dominio;

public class Provincia {
	private int id_provincia;
	private String descripcion;
	
	public Provincia(int id_provincia, String descripcion) {
		super();
		this.id_provincia = id_provincia;
		this.descripcion = descripcion;
	}	

	public Provincia() {}

	public int getId_provincia() {
		return id_provincia;
	}

	public void setId_provincia(int id_provincia) {
		this.id_provincia = id_provincia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
