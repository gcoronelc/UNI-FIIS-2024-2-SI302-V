package pe.edu.uni.aprendiendojava.prueba;

import pe.edu.uni.aprendiendojava.service.VentaAbstract;
import pe.edu.uni.aprendiendojava.service.VentaImpl;

public class Prueba04 {

	public static void main(String[] args) {
		// Datos
		double precio = 15.65;
		int cantidad = 18;
		// Proceso
		VentaAbstract bean = new VentaImpl();
		double importe = bean.calcImporte(precio, cantidad);
		double impuesto = bean.calcImpuesto(precio, cantidad);
		double total = bean.calcTotal(precio, cantidad);
		// Reporte
		System.out.println("Importe: " + importe);
		System.out.println("Impuesto: " + impuesto);
		System.out.println("Promedio: " + total);
	}
	
}
