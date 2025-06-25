package Dominio;

import java.sql.Date;

public class Cuenta {
	private int id;
	private int dni;
	private String CBU;
	private Date creacion;
	private TipoCuenta tipo;
	private float saldo;
	private boolean estado;
	
	public Cuenta() {
		this.estado = true;
	}

	public Cuenta(int id, int dni, String CBU, Date creacion, TipoCuenta tipo, float saldo, boolean estado) {
		this.id = id;
		this.dni = dni;
		this.CBU = CBU;
		this.creacion = creacion;
		this.tipo = tipo;
		this.saldo = saldo;
		this.estado = estado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getCBU() {
		return CBU;
	}

	public void setCBU(String cBU) {
		CBU = cBU;
	}

	public Date getCreacion() {
		return creacion;
	}

	public void setCreacion(Date creacion) {
		this.creacion = creacion;
	}

	public TipoCuenta getTipo() {
		return tipo;
	}

	public void setTipo(TipoCuenta tipo) {
		this.tipo = tipo;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Cuenta [id=" + id + ", dni=" + dni + ", CBU=" + CBU + ", creacion=" + creacion + ", tipo=" + tipo
				+ ", saldo=" + saldo + ", estado=" + estado + "]";
	}
}