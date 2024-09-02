package pe.edu.uni.clase02.service;

public class VentaService {

	private double precio;
	private int cantidad;

	public VentaService() {
		//this.precio = 100;
		//this.cantidad = 5;
		this(150, 6);
		System.out.println("Objeto creado!!!");
	}

	public VentaService(double precio, int cantidad) {
		this.precio = precio;
		this.cantidad = cantidad;
	}

	@Override
	protected void finalize() throws Throwable {
		System.err.println("Chau objeto.");
	}
	
	
	
	
	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public double getImporte(){
		return (this.precio * this.cantidad);
	}
	
}
