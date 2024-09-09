package pe.edu.uni.aprendiendojava.service;

import java.util.ArrayList;
import java.util.List;
import pe.edu.uni.aprendiendojava.dto.ProductoDto;

public class ProductoService {
	
	public List<ProductoDto> obtenerProductos(){
		List<ProductoDto> lista = new ArrayList<>();
		lista.add(new ProductoDto("Televisor", 4500.00));
		lista.add(new ProductoDto("Refrigeradora", 3500.00));
		lista.add(new ProductoDto("Lavadora", 4200.00));
		lista.add(new ProductoDto("Microhondas", 500.00));
		return lista;
	}
	
}
