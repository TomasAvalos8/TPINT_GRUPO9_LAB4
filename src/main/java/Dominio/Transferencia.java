package Dominio;

import java.sql.Date;

public class Transferencia {
	private int idTransferencia;
    private Cuenta numeroCuentaSaliente;
    private Cuenta numeroCuentaDestino;
    private float monto;
    private String concepto;
    private Date fecha;
    
    public Transferencia() {
    }
    
	public Transferencia(int idTransferencia, Cuenta numeroCuentaSaliente, Cuenta numeroCuentaDestino, float monto
			, String concepto,Date fecha) {
		super();
		this.idTransferencia = idTransferencia;
		this.numeroCuentaSaliente = numeroCuentaSaliente;
		this.numeroCuentaDestino = numeroCuentaDestino;
		this.monto = monto;
		this.concepto = concepto;
		this.fecha = fecha;
	}

	public int getIdTransferencia() {
		return idTransferencia;
	}

	public void setIdTransferencia(int idTransferencia) {
		this.idTransferencia = idTransferencia;
	}

	public Cuenta getNumeroCuentaSaliente() {
		return numeroCuentaSaliente;
	}

	public void setNumeroCuentaSaliente(Cuenta numeroCuentaSaliente) {
		this.numeroCuentaSaliente = numeroCuentaSaliente;
	}

	public Cuenta getNumeroCuentaDestino() {
		return numeroCuentaDestino;
	}

	public void setNumeroCuentaDestino(Cuenta numeroCuentaDestino) {
		this.numeroCuentaDestino = numeroCuentaDestino;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}
	
	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
    
    
}
