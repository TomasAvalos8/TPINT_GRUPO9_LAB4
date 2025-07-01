package Dominio;

import java.sql.Date;

public class Transferencia {
	private int idTransferencia;
    private int numeroCuentaSaliente;
    private int numeroCuentaDestino;
    private float monto;
    private Date fecha;
    
	public Transferencia(int idTransferencia, int numeroCuentaSaliente, int numeroCuentaDestino, float monto,
			Date fecha) {
		super();
		this.idTransferencia = idTransferencia;
		this.numeroCuentaSaliente = numeroCuentaSaliente;
		this.numeroCuentaDestino = numeroCuentaDestino;
		this.monto = monto;
		this.fecha = fecha;
	}

	public int getIdTransferencia() {
		return idTransferencia;
	}

	public void setIdTransferencia(int idTransferencia) {
		this.idTransferencia = idTransferencia;
	}

	public int getNumeroCuentaSaliente() {
		return numeroCuentaSaliente;
	}

	public void setNumeroCuentaSaliente(int numeroCuentaSaliente) {
		this.numeroCuentaSaliente = numeroCuentaSaliente;
	}

	public int getNumeroCuentaDestino() {
		return numeroCuentaDestino;
	}

	public void setNumeroCuentaDestino(int numeroCuentaDestino) {
		this.numeroCuentaDestino = numeroCuentaDestino;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
    
    
}
