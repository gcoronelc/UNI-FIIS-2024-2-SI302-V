package pe.edu.uni.pc2solucion.prueba;

import java.util.List;
import pe.edu.uni.pc2solucion.db.ResumenDTO;
import pe.edu.uni.pc2solucion.service.LibreriaService;

public class PruebaServicio1 {

	public static void main(String[] args) {
		// Proceso
		LibreriaService service = new LibreriaService();
		List<ResumenDTO> resumen = service.resumenVentas();
		// Reporte
		System.out.printf("%-10s %-30s %-15s %-15s%n", "IDTIPO", "DESCRIPCION", "CANTIDAD TOTAL", "VENTAS TOTALES");
		for (ResumenDTO bean : resumen) {
			System.out.printf("%-10s %-30s %-15d %-15.2f%n",
					  bean.getIdTipo(), bean.getDescripcion(),
					  bean.getCantidadTotal(), bean.getVentasTotal());
		}
	}
}
