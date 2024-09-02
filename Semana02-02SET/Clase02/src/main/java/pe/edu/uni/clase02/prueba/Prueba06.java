package pe.edu.uni.clase02.prueba;

import pe.edu.uni.clase02.service.VentaService;

public class Prueba06 {
	
	public static void main(String[] args) {
		VentaService service = new VentaService();
		System.out.println("Precio: " + service.getPrecio());
		System.out.println("Cantidad: " + service.getCantidad());
		System.out.println("Importe: " + service.getImporte());
	}
	
}
