package pe.edu.uni.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.app.service.MathService;

@RestController
@RequestMapping("/math")
public class MathController {
	
	@Autowired
	private MathService mathService;
	
	/*
	@GetMapping("/factorial")
	public String factorial(int n) {
		return "Factorial: " + mathService.factorial(n);
	}
	*/
	
	@GetMapping("/factorial")
	public ResponseEntity<String> factorial(@RequestParam int n) {
	    if (n < 0) {
	        return ResponseEntity.badRequest().body("El valor de 'n' no puede ser negativo.");
	    }
	    return ResponseEntity.ok("Factorial: " + mathService.factorial(n));
	}
	
	@PostMapping("/potencia")
	public String potencia(int base, int expo) {
		long p = mathService.potencia(base, expo);
		return "Potencia: " + p;
	}

	
	

}
