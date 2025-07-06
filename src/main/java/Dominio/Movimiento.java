package Dominio;


import java.time.LocalDate;

public class Movimiento {
	
	private int idMovimiento;
	private int numeroCuenta;
	private TipoMovimiento tipoMovimiento;
	private String detalle;
	private float monto;
	private LocalDate fecha;
	
	public Movimiento() {}
	
	public Movimiento(int idMovimiento, int numeroCuenta, TipoMovimiento tipoMovimiento, String detalle, float monto,
			LocalDate fecha) {
		super();
		this.idMovimiento = idMovimiento;
		this.numeroCuenta = numeroCuenta;
		this.tipoMovimiento = tipoMovimiento;
		this.detalle = detalle;
		this.monto = monto;
		this.fecha = fecha;
	}
	public int getIdMovimiento() {
		return idMovimiento;
	}
	public void setIdMovimiento(int idMovimiento) {
		this.idMovimiento = idMovimiento;
	}
	public int getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public TipoMovimiento getTipoMovimiento() {
		return tipoMovimiento;
	}
	public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public float getMonto() {
		return monto;
	}
	public void setMonto(float monto) {
		this.monto = monto;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	

}
