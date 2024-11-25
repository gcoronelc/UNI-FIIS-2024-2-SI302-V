package pe.edu.uni.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import pe.edu.uni.app.dto.EmpleadoDto;

@Service
public class SeguridadService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public EmpleadoDto validar(String usuario, String clave) {
		String sql = """
			select chr_emplcodigo codigo, vch_emplpaterno paterno,  
			vch_emplmaterno materno, vch_emplnombre nombre,   
			vch_emplciudad ciudad, vch_empldireccion direccion,
			vch_emplusuario usuario, '**********' clave
			from empleado where vch_emplusuario = ?
			and vch_emplclave = ?
		""";
		EmpleadoDto bean;
		try {
			bean = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(EmpleadoDto.class), usuario, clave);
		} catch (EmptyResultDataAccessException e) {
			bean = null;
		}
		return bean;	
	}
}
