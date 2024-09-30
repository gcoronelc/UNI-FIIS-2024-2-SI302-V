package pe.edu.uni.educasoft.prueba;

import java.util.List;
import pe.edu.uni.educasoft.dto.MatriculaDto;
import pe.edu.uni.educasoft.service.EducaService;

public class Prueba02 {
	
	public static void main(String[] args) {
		try {
			EducaService educaService = new EducaService();
			List<MatriculaDto> lista = educaService.obtenerMatriculados(1);
			for (MatriculaDto dto : lista) {
				System.out.println(dto);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
}
