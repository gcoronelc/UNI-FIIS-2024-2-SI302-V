package pe.edu.uni.pc2solucion.db;

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
public class ResumenDTO {

	private String idTipo;
	private String descripcion;
	private int cantidadTotal;
	private double ventasTotal;

}
