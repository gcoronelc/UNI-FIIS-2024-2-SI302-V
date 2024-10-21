package pe.edu.uni.pc2solucion.prueba;

import java.sql.Connection;
import pe.edu.uni.pc2solucion.db.AccesoDB;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog www.desarrollasoftware.com
 * @email gcoronelc@gmail.com
 * @youtube www.youtube.com/DesarrollaSoftware
 * @facebook www.facebook.com/groups/desarrollasoftware/
 * @cursos gcoronelc.github.io
 */
public class PruebaConexion {

	public static void main(String[] args) {
		try {
			Connection cn = AccesoDB.getConnection();
			System.out.println("Ok");
			cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
