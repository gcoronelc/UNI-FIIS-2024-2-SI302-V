package pe.edu.uni.aprendiendojava.service;

public abstract class VentaAbstract {

	public abstract double calcImporte(double precio, int cantidad);

	public abstract double calcImpuesto(double precio, int cantidad);

	public abstract double calcTotal(double precio, int cantidad);

}
