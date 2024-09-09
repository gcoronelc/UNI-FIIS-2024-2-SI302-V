package pe.edu.uni.aprendiendojava.prueba;

import pe.edu.uni.aprendiendojava.service.MateService;

public class Prueba01 {
	
	public static void main(String[] args) {
		MateService service = new MateService();
		System.out.println("Promedio: " + 
				  service.promedio(10,20));
		System.out.println("Promedio: " + 
				  service.promedio(10,20, 25));
	}
}
