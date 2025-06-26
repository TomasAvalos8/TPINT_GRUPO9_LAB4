package Datos;

import java.util.List;

import Dominio.Localidad;
import Dominio.Provincia;

public interface LocalidadDao {

	public List<Localidad>obtenerLocalidadesPorProvincia(int provincia);
}
