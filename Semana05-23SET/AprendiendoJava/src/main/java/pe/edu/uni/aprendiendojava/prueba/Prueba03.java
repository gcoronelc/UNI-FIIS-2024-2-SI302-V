package pe.edu.uni.aprendiendojava.prueba;

import pe.edu.uni.aprendiendojava.impl.MateSistC;
import pe.edu.uni.aprendiendojava.spec.MateSpec;

public class Prueba03 {

	public static void main(String[] args) {
		// Datos
		int pp = 13;
		int ep = 15;
		int ef = 18;
		// Proceso
		MateSpec bean = new MateSistC(); 
		int pf = bean.promedio(pp, ep, ef);
		// Reporte
		System.out.println("Promedio: " + pf);
	}
	
}
