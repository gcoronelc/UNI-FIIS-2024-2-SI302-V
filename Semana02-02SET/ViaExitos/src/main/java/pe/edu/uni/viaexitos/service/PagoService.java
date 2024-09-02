package pe.edu.uni.viaexitos.service;

import pe.edu.uni.viaexitos.dto.PagoDto;

public class PagoService {

	public PagoDto procesar(PagoDto dto){
		// Variables
		double ingresos;
		double renta;
		double neto;
		// Proceso
		ingresos = dto.getDias() * dto.getHorasDia() * dto.getPagoHora();
		renta = ingresos * (ingresos>1500.00?0.08:0.0);
		neto = ingresos - renta;
		// Reporte
		dto.setIngresos(ingresos);
		dto.setRenta(renta);
		dto.setNeto(neto);
		return dto;
	}

	
}
