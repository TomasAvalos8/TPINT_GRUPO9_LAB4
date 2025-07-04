package Dominio;

import java.util.Date;

public class Prestamo {
    private int id_prestamo;
    private SolicitudPrestamo solicitud;
    private Cliente cliente;
    private Cuenta cuenta;
    private Date fecha_alta;
    private int cuotas;
    private double importe_pagar_por_mes;
    private double importe_total_intereses;
    private int plazo_pago_meses;
    private double importe_solicitado;
    private boolean activo;

    public int getId_prestamo() { 
        return id_prestamo; 
    }
    public void setId_prestamo(int id_prestamo) { 
        this.id_prestamo = id_prestamo; 
    }

    public SolicitudPrestamo getSolicitud() { 
        return solicitud; 
    }
    public void setSolicitud(SolicitudPrestamo solicitud) { 
        this.solicitud = solicitud; 
    }

    public Cliente getCliente() { 
        return cliente; 
    }
    public void setCliente(Cliente cliente) { 
        this.cliente = cliente; 
    }

    public Cuenta getCuenta() { 
        return cuenta; 
    }
    public void setCuenta(Cuenta cuenta) { 
        this.cuenta = cuenta; 
    }

    public Date getFecha_alta() { 
        return fecha_alta; 
    }
    public void setFecha_alta(Date fecha_alta) { 
        this.fecha_alta = fecha_alta; 
    }
    public int getCuotas() { 
        return cuotas; 
    }
    public void setCuotas(int cuotas) { 
        this.cuotas = cuotas; 
    }
    public double getImporte_pagar_por_mes() { 
        return importe_pagar_por_mes; 
    }
    public void setImporte_pagar_por_mes(double importe_pagar_por_mes) { 
        this.importe_pagar_por_mes = importe_pagar_por_mes; 
    }
    public int getPlazo_pago_meses() { 
        return plazo_pago_meses; 
    }
    public void setPlazo_pago_meses(int plazo_pago_meses) { 
        this.plazo_pago_meses = plazo_pago_meses; 
    }
    public double getImporte_solicitado() { 
        return importe_solicitado; 
    }
    public void setImporte_solicitado(double importe_solicitado) { 
        this.importe_solicitado = importe_solicitado; 
    }
    public boolean isActivo() { 
        return activo; 
    }
    public void setActivo(boolean activo) { 
        this.activo = activo; 
    }
    public double getImporte_total_intereses() {
        return importe_total_intereses;
    }
    public void setImporte_total_intereses(double importe_total_intereses) {
        this.importe_total_intereses = importe_total_intereses;
    }
}
