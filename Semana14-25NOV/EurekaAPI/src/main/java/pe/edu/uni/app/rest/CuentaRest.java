package pe.edu.uni.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.app.dto.CuentaDto;
import pe.edu.uni.app.service.CuentaService;

@RestController
@RequestMapping("/cuentas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CuentaRest {
	
	@Autowired
	private CuentaService cuentaService;
	
	@PostMapping("/crearcuenta")
	public ResponseEntity<?> crearCuenta(@RequestBody CuentaDto bean){
		System.out.println(bean);
		try {
			bean = cuentaService.crearCuenta(bean);
			return new ResponseEntity<>(bean, HttpStatus.OK);
		} catch (Exception e) {
			String mensaje = "Error en el proceso. " + e.getMessage();
			return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
		}
	}

}
