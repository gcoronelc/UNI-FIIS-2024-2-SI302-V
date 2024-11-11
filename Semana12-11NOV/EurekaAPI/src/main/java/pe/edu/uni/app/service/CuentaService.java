package pe.edu.uni.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.uni.app.dto.CuentaDto;

@Service
public class CuentaService {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
	public CuentaDto crearCuenta( CuentaDto bean) {
		// Validaciones
		validarClienteDigitos(bean.getCliente());
		validarClienteExiste(bean.getCliente());
		validarMoneda(bean.getMoneda());
		validarEmpleado(bean.getEmpleado());
		validarImporte(bean.getImporte());
		validarClave(bean.getClave());
		// Proceso
		String sucursal = obtenerSucursal(bean.getEmpleado());
		int contCuenta = obtenerContadorCuenta(sucursal);
		String cuenta = sucursal + String.format("%05d", contCuenta);
		bean.setCuenta(cuenta);
		bean.setSucursal(sucursal);
		registrarCuenta(bean);
		registrarMovApertura(bean);
		bean.setClave("******");
		return bean;
	}

	@Transactional(propagation=Propagation.MANDATORY, rollbackFor=Exception.class)
	private void registrarMovApertura(CuentaDto bean) {
		String sql = """
			insert into Movimiento(chr_cuencodigo,int_movinumero,dtt_movifecha,
			chr_emplcodigo,chr_tipocodigo,dec_moviimporte,chr_cuenreferencia)
			values(?,1,GETDATE(),?,'001',?,NULL)				
		""";
		Object[] datos = {
				bean.getCuenta(),bean.getEmpleado(),bean.getImporte()
		};
		jdbcTemplate.update(sql,datos);
	}

	@Transactional(propagation=Propagation.MANDATORY, rollbackFor=Exception.class)
	private void registrarCuenta(CuentaDto bean) {
		String sql = """
			insert into cuenta(chr_cuencodigo,chr_monecodigo,chr_sucucodigo,
			chr_emplcreacuenta,chr_cliecodigo,dec_cuensaldo,dtt_cuenfechacreacion,
			vch_cuenestado,int_cuencontmov,chr_cuenclave) 
			values(?,?,?,?,?,?,GETDATE(),'ACTIVO',1,?)				
		""";
		Object[] datos = {
				bean.getCuenta(), bean.getMoneda(),bean.getSucursal(),
				bean.getEmpleado(),bean.getCliente(),bean.getImporte(),
				bean.getClave()
		};
		jdbcTemplate.update(sql, datos);
	}

	@Transactional(propagation=Propagation.MANDATORY, rollbackFor=Exception.class)
	private int obtenerContadorCuenta(String sucursal) {
		String sql = """
				update sucursal 
				set int_sucucontcuenta = int_sucucontcuenta + 1 
				where chr_sucucodigo=?
				""";
		jdbcTemplate.update(sql,sucursal);
		sql = "select int_sucucontcuenta from Sucursal where chr_sucucodigo=?";
		int cont = jdbcTemplate.queryForObject(sql, Integer.class, sucursal);
		return cont;
	}

	@Transactional(propagation=Propagation.MANDATORY, rollbackFor=Exception.class)
	private String obtenerSucursal(String empleado) {
		String sql = "select chr_sucucodigo from Asignado where chr_emplcodigo=? and dtt_asigfechabaja is null";
		String sucursal = jdbcTemplate.queryForObject(sql, String.class, empleado);
		return sucursal;
	}

	private void validarClave(String clave) {
		if(!clave.matches("^\\d{6}$")) {
			throw new RuntimeException("La clave debe ser de 6 digitos.");
		}
	}

	private void validarImporte(Double importe) {
		if(importe<=0) {
			throw new RuntimeException("El importe debe ser un valor positivo.");
		}
	}

	@Transactional(propagation=Propagation.MANDATORY, rollbackFor=Exception.class)
	private void validarEmpleado(String empleado) {
		String sql = "select count(1) cont from Asignado where chr_emplcodigo=? and dtt_asigfechabaja is null";
		int filas = jdbcTemplate.queryForObject(sql, Integer.class, empleado);
		if(filas!=1) {
			throw new RuntimeException("Empleado no existe.");
		}
	}

	@Transactional(propagation=Propagation.MANDATORY, rollbackFor=Exception.class)
	private void validarMoneda(String moneda) {
		String sql ="select count(1) cont from moneda where chr_monecodigo=?";
		int filas = jdbcTemplate.queryForObject(sql, Integer.class, moneda);
		if(filas!=1) {
			throw new RuntimeException("Moneda no existe.");
		}
	}

	@Transactional(propagation=Propagation.MANDATORY, rollbackFor=Exception.class)
	private void validarClienteDigitos(String cliente) {
		if(!cliente.matches("^\\d{5}$")) {
			throw new RuntimeException("Codigo de cliente deben ser digitos.");
		}
	}

	@Transactional(propagation=Propagation.MANDATORY, rollbackFor=Exception.class)
	private void validarClienteExiste(String cliente) {
		String sql = "select count(1) cont from cliente where chr_cliecodigo=?";
		int filas = jdbcTemplate.queryForObject(sql, Integer.class, cliente);
		if(filas!=1) {
			throw new RuntimeException("Cliente no existe.");
		}
	}

}
