package Dominio;
import java.util.Date;

public class Reporte {
    private String tipoReporte;
    private Date fechaInicio;
    private Date fechaFin;
    private float montoTotal;
    private int cantidadTotal;
    private String descripcion;
    private String categoria;
    private float porcentaje;
    
    private int totalPrestamos;
    private float porcAprobados;
    private float porcRechazados;
    private float porcPendientes;
    
    public Reporte() {}
    
    public Reporte(String tipoReporte, Date fechaInicio, Date fechaFin, 
                  float montoTotal, int cantidadTotal, String descripcion, int totalPrestamos, float porcAprobados, float porcRechazados, float porcPendientes) {
    	
        this.tipoReporte = tipoReporte;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.montoTotal = montoTotal;
        this.cantidadTotal = cantidadTotal;
        this.descripcion = descripcion;
        this.totalPrestamos = totalPrestamos;
        this.porcAprobados = porcAprobados;
        this.porcRechazados = porcRechazados;
        this.porcPendientes = porcPendientes;
    }
    
    // Getters y Setters
    public String getTipoReporte() {
        return tipoReporte;
    }
    
    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }
    
    public Date getFechaInicio() {
        return fechaInicio;
    }
    
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    
    public Date getFechaFin() {
        return fechaFin;
    }
    
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    public float getMontoTotal() {
        return montoTotal;
    }
    
    public void setMontoTotal(float montoTotal) {
        this.montoTotal = montoTotal;
    }
    
    public int getCantidadTotal() {
        return cantidadTotal;
    }
    
    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getCategoria() {
        return categoria;
    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public float getPorcentaje() {
        return porcentaje;
    }
    
    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    }

	public int getTotalPrestamos() {
		return totalPrestamos;
	}

	public void setTotalPrestamos(int totalPrestamos) {
		this.totalPrestamos = totalPrestamos;
	}

	public float getPorcAprobados() {
		return porcAprobados;
	}

	public void setPorcAprobados(float porcAprobados) {
		this.porcAprobados = porcAprobados;
	}

	public float getPorcRechazados() {
		return porcRechazados;
	}

	public void setPorcRechazados(float porcRechazados) {
		this.porcRechazados = porcRechazados;
	}

	public float getPorcPendientes() {
		return porcPendientes;
	}

	public void setPorcPendientes(float porcPendientes) {
		this.porcPendientes = porcPendientes;
	}
    
    
}

