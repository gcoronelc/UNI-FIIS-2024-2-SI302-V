package pe.edu.uni.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.uni.app.dto.ClienteDto;

@Service
public class ClienteService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String SELECT_BASE = """
			select chr_cliecodigo codigo, vch_cliepaterno paterno,
			vch_cliematerno materno, vch_clienombre nombre,
			chr_cliedni dni, vch_clieciudad ciudad,   
			vch_cliedireccion direccion, vch_clietelefono telefono, 
			vch_clieemail email from cliente
		""";
	
	public List<Map<String,Object>> consultarTodos(){
		List<Map<String,Object>> lista = null;
		lista = jdbcTemplate.queryForList(SELECT_BASE);
		return lista;
	}
	
	public List<ClienteDto> consultarTodosV2(){
		List<ClienteDto> lista = null;
		lista = jdbcTemplate.query(SELECT_BASE, new BeanPropertyRowMapper<>(ClienteDto.class));
		return lista;
	}

	public ClienteDto getCliente(String codigo) {
		String sql = SELECT_BASE + " where chr_cliecodigo=?";
		ClienteDto bean;
		try {
			bean = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(ClienteDto.class), codigo);
		} catch (EmptyResultDataAccessException e) {
			bean = null;
		}
		return bean;
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ClienteDto crearCliente(ClienteDto bean) {
		// Contador
		String sql = """
			select int_contitem, int_contlongitud 
			from contador where vch_conttabla='cliente'
		""";
		Map<String,Object> rec = jdbcTemplate.queryForMap(sql);
		int cont = Integer.parseInt(rec.get("int_contitem").toString()) + 1;
		int longitud = Integer.parseInt(rec.get("int_contlongitud").toString());
		sql = """
			update contador 
			set int_contitem = ? 
			where vch_conttabla='cliente'
		""";
		int filas = jdbcTemplate.update(sql, cont);
		if(filas != 1) {
			throw new RuntimeException("El contador de la tabla CLIENTE no existe.");
		}
		// Crear el codigo
		String formato = "%0" + longitud + "d";
		String codigo = String.format(formato, cont);
		// Registrar el nuevo cliente
		sql = """
			insert into cliente(chr_cliecodigo, vch_cliepaterno, 
			vch_cliematerno, vch_clienombre, chr_cliedni, 
			vch_clieciudad, vch_cliedireccion, vch_clietelefono,
			vch_clieemail) values(?,?,?,?,?,?,?,?,?)	
		""";
		Object[] args = { codigo, bean.getPaterno(), bean.getMaterno(),
			bean.getNombre(), bean.getDni(), bean.getCiudad(),
			bean.getDireccion(), bean.getTelefono(),bean.getEmail() };
		jdbcTemplate.update(sql, args);
		// Retornar el codigo
		bean.setCodigo(codigo);
		return bean;
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ClienteDto actualizarCliente(ClienteDto bean) {
		String sql = """
			update cliente set 
			vch_cliepaterno=?, vch_cliematerno=?,
			vch_clienombre=?, chr_cliedni=?, 
			vch_clieciudad=?, vch_cliedireccion=?, 
			vch_clietelefono=?, vch_clieemail=?
			where chr_cliecodigo=?;		
		""";
		Object[] args = { bean.getPaterno(), bean.getMaterno(),
			bean.getNombre(), bean.getDni(), bean.getCiudad(),
			bean.getDireccion(), bean.getTelefono(),
			bean.getEmail(), bean.getCodigo() };
		jdbcTemplate.update(sql, args);
		return bean;
	}
}
