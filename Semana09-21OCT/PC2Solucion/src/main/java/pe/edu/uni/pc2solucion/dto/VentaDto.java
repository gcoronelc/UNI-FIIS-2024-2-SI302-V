package pe.edu.uni.pc2solucion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog www.desarrollasoftware.com
 * @email gcoronelc@gmail.com
 * @youtube www.youtube.com/DesarrollaSoftware
 * @facebook www.facebook.com/groups/desarrollasoftware/
 * @cursos gcoronelc.github.io
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaDto {

	private int idventa;
	private String idPublicacion;
	private int cantidad;
	private String cliente;
	private int idEmpleado;
	private double totalVenta;

}
