package pe.edu.uni.educasoft.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.uni.educasoft.db.AccesoDB;
import pe.edu.uni.educasoft.dto.MatriculaDto;

public class EducaService {
	
	
	public List<MatriculaDto> obtenerMatriculados(int idCurso){
		Connection cn = null;
		PreparedStatement pstm;
		ResultSet rs;
		String sql = "with ";
		sql += "t1 as ( ";
		sql += "	select cur_id, alu_id, emp_id, mat_tipo, ";
		sql += "	convert(varchar(20), mat_fecha, 103 ) fecha, ";
		sql += "	mat_precio, mat_cuotas, mat_nota ";
		sql += "	from matricula ";
		sql += "	where cur_id = ? ";
		sql += "), ";
		sql += "t2 as ( ";
		sql += "	select alu_id, sum(pag_importe) pago ";
		sql += "	from pago ";
		sql += "	where cur_id = ? ";
		sql += "	group by alu_id ";
		sql += ") ";
		sql += "select t1.cur_id, t1.alu_id, t1.emp_id, ";
		sql += "t1.mat_tipo, t1.mat_cuotas, t1.fecha, t1.mat_precio, ";
		sql += "ISNULL(t1.mat_nota,0) nota, ISNULL(t2.pago,0) pago, ";
		sql += "t1.mat_precio - ISNULL(t2.pago,0) deuda ";
		sql += "from t1 left join t2 on t1.alu_id=t2.alu_id ";
		List<MatriculaDto> lista = new ArrayList<>();
		try {
			cn = AccesoDB.getConnection();
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, idCurso);
			pstm.setInt(2, idCurso);
			rs = pstm.executeQuery();
			while(rs.next()){
				MatriculaDto bean = new MatriculaDto();
				bean.setIdCurso(idCurso);
				bean.setIdAlumno(rs.getInt("alu_id"));
				bean.setTipo(rs.getString("mat_tipo"));
				bean.setFecha(rs.getString("fecha"));
				bean.setPrecio(rs.getDouble("mat_precio"));
				bean.setCuotas(rs.getInt("mat_cuotas"));
				bean.setPago(rs.getDouble("pago"));
				bean.setDeuda(rs.getDouble("deuda"));
				lista.add(bean);
			}
			rs.close();
			pstm.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException("Error en el proceso, intentelo de nuevo.");
		} finally{
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return lista;
	}
	
	
	public MatriculaDto procMatricula(MatriculaDto bean){
		Connection cn = null;
		PreparedStatement pstm;
		ResultSet rs;
		String sql;
		double precio;
		try {
			// Paso 1: Inicio de Tx
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			// Paso 2: Validaciones
			
			// Paso 3: Leer el precio
			sql = "select cur_precio from curso where cur_id=?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, bean.getIdCurso());
			rs = pstm.executeQuery();
			if(!rs.next()){
				throw new SQLException("No se encontro el curso.");
			}
			precio = rs.getDouble("cur_precio");
			rs.close();
			pstm.close();
			// Paso 4: Determinar precio final
			String tipo = bean.getTipo().toUpperCase();
			precio = switch(tipo){
				case "BECA" -> 0;
				case "MEDIABECA" -> precio/2.0;
				default -> precio;
			};
			// Paso 5: Registrar matricula
			sql = "insert into matricula(cur_id,alu_id,emp_id,mat_tipo,";
			sql += "mat_fecha,mat_precio,mat_cuotas) ";
			sql += "values(?,?,?,?,GETDATE(),?,?)";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, bean.getIdCurso());
			pstm.setInt(2, bean.getIdAlumno());
			pstm.setInt(3, bean.getIdEmpleado());
			pstm.setString(4, tipo);
			pstm.setDouble(5, precio);
			pstm.setInt(6, bean.getCuotas());
			pstm.executeUpdate();
			// Paso 6: Actualizar matriculados
			sql = "update curso ";
			sql += "set cur_matriculados = cur_matriculados + 1 ";
			sql += "where cur_id = ?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, bean.getIdCurso());
			pstm.executeUpdate();
			// Paso 7: Confirmar Tx
			cn.commit();
		} catch (SQLException e) {
			try {
				cn.rollback();
			} catch (Exception e1) {
			}
			throw new RuntimeException(e.getMessage());
		}catch (Exception e) {
			try {
				cn.rollback();
			} catch (Exception e1) {
			}
			throw new RuntimeException("");
		}finally{
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return bean;
	}
}
