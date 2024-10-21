package pe.edu.uni.pc2solucion.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.uni.pc2solucion.db.AccesoDB;
import pe.edu.uni.pc2solucion.db.ResumenDTO;
import pe.edu.uni.pc2solucion.dto.VentaDto;

public class LibreriaService {

	public List<ResumenDTO> resumenVentas() {
		List<ResumenDTO> resumen = new ArrayList<>();
		String sql = """
               SELECT 
               	T.idtipo, T.descripcion, 
               	SUM(V.cantidad) AS cantidadTotal, 
               	SUM(V.total) AS ventasTotal 
               FROM TIPO T
               LEFT JOIN PUBLICACION P ON T.idtipo = P.idtipo 
               LEFT JOIN VENTA V ON P.idpublicacion = V.idpublicacion 
               GROUP BY T.idtipo, T.descripcion
               """;
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection(); 
			PreparedStatement pstm = cn.prepareStatement(sql); 
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				String idTipo = rs.getString("idtipo");
				String descripcion = rs.getString("descripcion");
				int cantidadTotal = rs.getInt("cantidadTotal");
				double ventasTotal = rs.getDouble("ventasTotal");
				ResumenDTO bean = new ResumenDTO(idTipo, descripcion, cantidadTotal, ventasTotal);
				resumen.add(bean);
			}
			rs.close();
			pstm.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} catch(Exception e){
			throw new RuntimeException("Error en el proceso, intentelo nuevamente.");
		} finally{
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return resumen;
	}

	public void registrarVenta(VentaDto bean) {

		// Variables
		Connection cn = null;
		String sql;
		PreparedStatement pstm;
		ResultSet rs;
		int cont, idVenta, stock, filas, activo;
		double precio, descuento = 0, subtotal, impuesto, total;
		// Proceso
		try {
			// Inicio de Tx
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			// Verificar idPublicacion
			sql = "select count(1) cont from PUBLICACION where idpublicacion=?";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getIdPublicacion());
			rs = pstm.executeQuery();
			rs.next();
			cont = rs.getInt("cont");
			pstm.close();
			rs.close();
			if (cont != 1) {
				throw new SQLException("La publicacion no existe.");
			}
			// Verificar id de usuario
			sql = "select count(1) cont from USUARIO where idempleado=?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, bean.getIdEmpleado());
			rs = pstm.executeQuery();
			rs.next();
			cont = rs.getInt("cont");
			pstm.close();
			rs.close();
			if (cont != 1) {
				throw new SQLException("El usuario no existe.");
			}
			// Verificar si se encuentra activo
			sql = "select activo from USUARIO where idempleado=?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, bean.getIdEmpleado());
			rs = pstm.executeQuery();
			rs.next();
			activo = rs.getInt("activo");
			pstm.close();
			rs.close();
			if (activo != 1) {
				throw new SQLException("El usuario no se encuentra activo");
			}
			// Verificar la cantidad
			if (bean.getCantidad() < 1) {
				throw new SQLException("La cantidad debe ser positiva.");
			}
			// Datos de la publicacion
			sql = "select precio, stock from PUBLICACION where idpublicacion=?";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getIdPublicacion());
			rs = pstm.executeQuery();
			rs.next();
			precio = rs.getDouble("precio");
			stock = rs.getInt("stock");
			rs.close();
			pstm.close();
			//Validar el stock
			if (bean.getCantidad() > stock) {
				throw new SQLException("No hay suficente stock");
			}
			// Actualizar stock
			stock -= bean.getCantidad();
			sql = "update PUBLICACION set stock=? where idpublicacion=?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, stock);
			pstm.setString(2, bean.getIdPublicacion());
			filas = pstm.executeUpdate();
			pstm.close();
			if (filas != 1) {
				throw new SQLException("Es posible que la publicacion no exista.");
			}
			//Calcular Descuento
			sql = "select porcentaje from PROMOCION where ?>=cantmin and ?<=cantmax";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, bean.getCantidad());
			pstm.setInt(2, bean.getCantidad());
			rs = pstm.executeQuery();
			if (!rs.next()) {
				throw new SQLException("Existe error interno.");
			}
			descuento = rs.getDouble("porcentaje") * precio;
			rs.close();
			pstm.close();
			// Calcular venta
			total = bean.getCantidad() * (precio - descuento);
			// Calculando el  subtotal e impuesto
			sql = "select valor from CONTROL where parametro='IGV'";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			rs.next();
			subtotal = total / (1 + rs.getDouble("valor"));
			impuesto = total - subtotal;
			rs.close();
			pstm.close();
			// Generar IDVENTA
			sql = "select valor from CONTROL where parametro='VENTA'";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			rs.next();
			idVenta = rs.getInt("valor");
			idVenta++;
			rs.close();
			pstm.close();
			// Registrar venta
			sql = "insert into VENTA values(?,?,getdate(),?,?,?,?,?,?,?,?)";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, idVenta);
			pstm.setString(2, bean.getCliente());
			pstm.setInt(3, bean.getIdEmpleado());
			pstm.setString(4, bean.getIdPublicacion());
			pstm.setInt(5, bean.getCantidad());
			pstm.setDouble(6, precio);
			pstm.setDouble(7, descuento);
			pstm.setDouble(8, subtotal);
			pstm.setDouble(9, impuesto);
			pstm.setDouble(10, total);
			pstm.executeUpdate();
			pstm.close();
			// Actualizar contador de ventas
			sql = "update CONTROL set valor=? where parametro='VENTA'";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, "" + idVenta);
			filas = pstm.executeUpdate();
			pstm.close();
			if (filas != 1) {
				throw new SQLException("Error interno.");
			}
			// Confirmar Tx
			cn.commit();
		} catch (SQLException e) {
			try {
				cn.rollback();
			} catch (Exception e1) {
			}
			throw new RuntimeException(e.getMessage());
		} catch (Exception e) {
			try {
				cn.rollback();
			} catch (Exception e1) {
			}
			throw new RuntimeException("Error en el proceso, intentelo mas tarde.");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
	}
}
