package pe.edu.uni.pc2solucion.prueba;

import pe.edu.uni.pc2solucion.dto.VentaDto;
import pe.edu.uni.pc2solucion.service.LibreriaService;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog www.desarrollasoftware.com
 * @email gcoronelc@gmail.com
 * @youtube www.youtube.com/DesarrollaSoftware
 * @facebook www.facebook.com/groups/desarrollasoftware/
 * @cursos gcoronelc.github.io
 */
public class PruebaServ2Item01 {

	public static void main(String[] args) {
		try {
			// Datos
			VentaDto bean = new VentaDto();
			bean.setIdPublicacion("LIB99999");
			bean.setCantidad(15);
			bean.setCliente("Gustavo Coronel");
			bean.setIdEmpleado(2);
			// Proceso
			LibreriaService service = new LibreriaService();
			service.registrarVenta(bean);
			System.out.println("Proceso OK");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
