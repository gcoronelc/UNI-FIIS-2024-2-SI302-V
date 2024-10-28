package pe.edu.uni.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import pe.edu.uni.app.dto.ClienteDto;

@Service
public class ClienteService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Map<String,Object>> consultarTodos(){
		List<Map<String,Object>> lista = null;
		String sql = """
			select chr_cliecodigo codigo, vch_cliepaterno paterno,
			vch_cliematerno materno  
			from cliente
		""";
		lista = jdbcTemplate.queryForList(sql);
		return lista;
	}
	
	public List<ClienteDto> consultarTodosV2(){
		List<ClienteDto> lista = null;
		String sql = """
			select chr_cliecodigo codigo, vch_cliepaterno paterno,
			vch_cliematerno materno, vch_clienombre nombre,
			chr_cliedni dni, vch_clieciudad ciudad,   
			vch_cliedireccion direccion, vch_clietelefono telefono, 
			vch_clieemail email from cliente
		""";
		lista = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ClienteDto.class));
		return lista;
	}
}
