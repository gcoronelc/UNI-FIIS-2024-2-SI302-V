package pe.edu.uni.aprendiendojava.service;

public class VentaImpl extends VentaAbstract{

	@Override
	public double calcTotal(double precio, int cantidad) {
		double total = precio * cantidad;
		return total;
	}

	@Override
	public double calcImporte(double precio, int cantidad) {
		return this.calcTotal(precio, cantidad)/1.18;
	}

	@Override
	public double calcImpuesto(double precio, int cantidad) {
		double total = this.calcTotal(precio, cantidad);
		double importe = this.calcImporte(precio, cantidad);
		double impuesto = total - importe;
		return impuesto;
	}
	
}
