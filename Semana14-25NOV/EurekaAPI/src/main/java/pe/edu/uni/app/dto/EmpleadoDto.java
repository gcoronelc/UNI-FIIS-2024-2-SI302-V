package pe.edu.uni.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoDto {

	private String codigo;
	private String paterno;
	private String materno;
	private String nombre;
	private String ciudad;
	private String direccion;
	private String usuario;
	private String clave;
}
