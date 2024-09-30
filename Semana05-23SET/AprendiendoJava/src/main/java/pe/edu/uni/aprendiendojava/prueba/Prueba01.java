package pe.edu.uni.aprendiendojava.prueba;

import pe.edu.uni.aprendiendojava.herencia.Clase3;

public class Prueba01 {

	public static void main(String[] args) {
		Clase3 bean = new Clase3();
		int suma = bean.sumar(8, 7);
		long p = bean.potencia(2, 4);
		System.out.println("Suma: " + suma);
		System.out.println("Potencia: " + p);
	}
	
}
