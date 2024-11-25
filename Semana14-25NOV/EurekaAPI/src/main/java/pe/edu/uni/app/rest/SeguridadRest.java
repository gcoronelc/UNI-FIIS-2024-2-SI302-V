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
import pe.edu.uni.app.dto.EmpleadoDto;
import pe.edu.uni.app.service.SeguridadService;

@RestController
@RequestMapping("/seguridad")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SeguridadRest {
	
	@Autowired
	private SeguridadService seguridadService;
	
	@PostMapping("/logon")
	public ResponseEntity<?> crearCuenta(@RequestBody EmpleadoDto bean){
		System.out.println(bean);
		try {
			bean = seguridadService.validar(bean.getUsuario(), bean.getClave());
			if(bean == null) {
				throw new RuntimeException("Datos incorrectos.");
			}
			return new ResponseEntity<>(bean, HttpStatus.OK);
		} catch (Exception e) {
			String mensaje = "Error en el proceso. " + e.getMessage();
			return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
		}
	} 
	
	
	

}
