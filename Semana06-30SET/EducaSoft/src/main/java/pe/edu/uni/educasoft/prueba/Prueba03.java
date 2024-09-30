package pe.edu.uni.educasoft.prueba;

import java.util.List;
import pe.edu.uni.educasoft.dto.MatriculaDto;
import pe.edu.uni.educasoft.service.EducaService;

public class Prueba03 {
	
	public static void main(String[] args) {
		try {
			// Datos
			MatriculaDto bean = new MatriculaDto();
			bean.setIdCurso(1);
			bean.setIdAlumno(6);
			bean.setIdEmpleado(2);
			bean.setTipo("regular");
			bean.setCuotas(10);
			// Proceso
			EducaService educaService = new EducaService();
			bean = educaService.procMatricula(bean);
			// Reporte
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
}
