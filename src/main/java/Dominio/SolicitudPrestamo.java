package Dominio;

public class SolicitudPrestamo {
    private int id_solicitud;
    private Cliente cliente;
    private long importe_solicitado;
    private long numero_cuenta_deposito;
    private int cuotas;
    private java.sql.Date fecha_solicitud;
    private int autorizacion;
    private boolean estado;

    public int getId_solicitud() {
        return id_solicitud;
    }
    public void setId_solicitud(int id_solicitud) {
        this.id_solicitud = id_solicitud;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public long getImporte_solicitado() {
        return importe_solicitado;
    }
    public void setImporte_solicitado(long importe_solicitado) {
        this.importe_solicitado = importe_solicitado;
    }
    public long getNumero_cuenta_deposito() {
        return numero_cuenta_deposito;
    }
    public void setNumero_cuenta_deposito(long numero_cuenta_deposito) {
        this.numero_cuenta_deposito = numero_cuenta_deposito;
    }
    public int getCuotas() {
        return cuotas;
    }
    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }
    public java.sql.Date getFecha_solicitud() {
        return fecha_solicitud;
    }
    public void setFecha_solicitud(java.sql.Date fecha_solicitud) {
        this.fecha_solicitud = fecha_solicitud;
    }
    public int getAutorizacion() {
        return autorizacion;
    }
    public void setAutorizacion(int autorizacion) {
        this.autorizacion = autorizacion;
    }
    public boolean getEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
