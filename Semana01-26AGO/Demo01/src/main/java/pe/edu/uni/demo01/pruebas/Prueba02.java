package pe.edu.uni.demo01.pruebas;

import pe.edu.uni.demo01.service.MateService;

public class Prueba02 {
	
	public static void main(String[] args) {
		// Datos
		int n1 = 30;
		int n2 = 20;
		// Proceso
		MateService service = new MateService();
		int producto = service.multiplicar(n1, n2);
		// Reporte
		System.out.println("Numero 1: " + n1);
		System.out.println("Numero 2: " + n2);
		System.out.println("Producto: " + producto);
	}
	
	
}
