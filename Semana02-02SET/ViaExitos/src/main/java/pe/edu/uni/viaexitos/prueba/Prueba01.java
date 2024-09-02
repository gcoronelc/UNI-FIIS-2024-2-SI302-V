package pe.edu.uni.viaexitos.prueba;

import pe.edu.uni.viaexitos.dto.PagoDto;
import pe.edu.uni.viaexitos.service.PagoService;

public class Prueba01 {
	
	public static void main(String[] args) {
		// Datos
		PagoDto dto = new PagoDto(20, 5, 150);
		// Proceso
		PagoService service = new PagoService();
		dto = service.procesar(dto);
		// Reporte
		System.out.println("Ingresos: " + dto.getIngresos());
		System.out.println("Renta: " + dto.getRenta());
		System.out.println("Neto: " + dto.getNeto());
	}
	
}
