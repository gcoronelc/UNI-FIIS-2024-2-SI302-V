package pe.edu.uni.app.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public List<Map<String, Object>> consultarTodos() {
		return clienteService.consultarTodos();
	}

	@GetMapping("/listado")
	public List<ClienteDto> listado() {
		return clienteService.consultarTodosV2();
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<?> getCliente(@PathVariable String codigo) {
		try {
			ClienteDto cliente = clienteService.getCliente(codigo);
			if (cliente != null) {
				return new ResponseEntity<>(cliente, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Cliente no encontrado.", HttpStatus.NOT_FOUND); // Cliente no encontrado
			}
		} catch (Exception e) {
			// Log the exception (opcional)
			return new ResponseEntity<>("Se ha producido un error en el servidor.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping // Endpoint para crear un nuevo cliente
    public ResponseEntity<?> crearCliente(@RequestBody ClienteDto bean) {
        try {
            bean = clienteService.crearCliente(bean);
            return new ResponseEntity<>(bean, HttpStatus.CREATED); // 201 Created
        } catch (Exception e) {
            // Log the exception (opcional)
        	System.out.println("ERROR: " + e.getMessage());
            return new ResponseEntity<>("Error en el proceso.",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	// Nuevo método PUT para actualizar un cliente
    @PutMapping("/{codigo}")
    public ResponseEntity<?> actualizarCliente(@PathVariable String codigo, @RequestBody ClienteDto bean) {
        try {
            ClienteDto clienteExistente = clienteService.getCliente(codigo);
            if (clienteExistente == null) {
                return new ResponseEntity<>("Cliente no encontrado.", HttpStatus.NOT_FOUND); // Cliente no encontrado
            }

            // Actualizar el cliente utilizando el servicio
            bean.setCodigo(codigo); // Asegúrate de que el código se mantenga
            ClienteDto clienteModificado = clienteService.actualizarCliente(bean);
            
            return new ResponseEntity<>(clienteModificado, HttpStatus.OK); // 200 OK
        } catch (Exception e) {
            // Log the exception (opcional)
            System.out.println("ERROR: " + e.getMessage());
            return new ResponseEntity<>("Se ha producido un error en el servidor.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
