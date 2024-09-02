package pe.edu.uni.clase02.prueba;

import pe.edu.uni.clase02.service.VentaService;

public class Prueba05 {
	
	public static void main(String[] args) {
		VentaService service = new VentaService();
		service.setPrecio(67.89);
		service.setCantidad(5);
		System.out.println("Precio: " + service.getPrecio());
		System.out.println("Cantidad: " + service.getCantidad());
		System.out.println("Importe: " + service.getImporte());
	}
	
}
