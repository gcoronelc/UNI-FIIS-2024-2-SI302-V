package pe.edu.uni.viaexitos.dto;

public class PagoDto {

	// Datos
	private int dias;
	private int horasDia;
	private double pagoHora;
	// Reporte
	private double ingresos;
	private double renta;
	private double neto;

	public PagoDto() {
		this(0, 0, 0);
	}

	public PagoDto(int dias, int horasDia, double pagoHora) {
		this.dias = dias;
		this.horasDia = horasDia;
		this.pagoHora = pagoHora;
		this.ingresos = 0;
		this.renta = 0;
		this.neto = 0;
	}

	public int getDias() {
		return dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}

	public int getHorasDia() {
		return horasDia;
	}

	public void setHorasDia(int horasDia) {
		this.horasDia = horasDia;
	}

	public double getPagoHora() {
		return pagoHora;
	}

	public void setPagoHora(double pagoHora) {
		this.pagoHora = pagoHora;
	}

	public double getIngresos() {
		return ingresos;
	}

	public void setIngresos(double ingresos) {
		this.ingresos = ingresos;
	}

	public double getRenta() {
		return renta;
	}

	public void setRenta(double renta) {
		this.renta = renta;
	}

	public double getNeto() {
		return neto;
	}

	public void setNeto(double neto) {
		this.neto = neto;
	}

}
