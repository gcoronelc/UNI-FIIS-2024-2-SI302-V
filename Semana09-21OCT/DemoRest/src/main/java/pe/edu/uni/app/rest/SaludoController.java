package pe.edu.uni.app.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saludo")
public class SaludoController {
	
	@GetMapping
	public String saludo() {
		return "Hola todos";
	}
	
	@GetMapping("/v2")
	public String saludo(String nombre) {
		return "Hola " + nombre;
	}

}
