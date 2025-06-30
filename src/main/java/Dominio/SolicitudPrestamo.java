package Dominio;

public class SolicitudPrestamo {
    private int id_solicitud;
    private long dni_cliente;
    private boolean autorizacion;
    private int cuotas;
    private long numero_cuenta_deposito;
    private String estado;

    public int getId_solicitud() {
        return id_solicitud;
    }
    public void setId_solicitud(int id_solicitud) {
        this.id_solicitud = id_solicitud;
    }
    public long getDni_cliente() {
        return dni_cliente;
    }
    public void setDni_cliente(long dni_cliente) {
        this.dni_cliente = dni_cliente;
    }
    public boolean isAutorizacion() {
        return autorizacion;
    }
    public void setAutorizacion(boolean autorizacion) {
        this.autorizacion = autorizacion;
    }
    public int getCuotas() {
        return cuotas;
    }
    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }
    public long getNumero_cuenta_deposito() {
        return numero_cuenta_deposito;
    }
    public void setNumero_cuenta_deposito(long numero_cuenta_deposito) {
        this.numero_cuenta_deposito = numero_cuenta_deposito;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
