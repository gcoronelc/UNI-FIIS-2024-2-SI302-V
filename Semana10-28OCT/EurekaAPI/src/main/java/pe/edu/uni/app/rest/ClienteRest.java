package pe.edu.uni.app.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.app.dto.ClienteDto;
import pe.edu.uni.app.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteRest {
	
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public List<Map<String,Object>> consultarTodos(){
		return clienteService.consultarTodos();
	}
	
	@GetMapping("/listado")
	public List<ClienteDto> listado(){
		return clienteService.consultarTodosV2();
	}

}
