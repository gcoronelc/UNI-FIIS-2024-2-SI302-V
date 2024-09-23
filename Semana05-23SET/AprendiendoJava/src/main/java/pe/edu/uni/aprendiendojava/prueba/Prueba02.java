package pe.edu.uni.aprendiendojava.prueba;

import pe.edu.uni.aprendiendojava.herencia.Clase1;
import pe.edu.uni.aprendiendojava.herencia.Clase2;

public class Prueba02 {

	public static void main(String[] args) {
		Clase1 bean = new Clase2();
		int suma = bean.sumar(8, 7);
		//long p = bean.potencia(3,4);
		System.out.println("Suma: " + suma);
		//System.out.println("Patencia: " + p);
	}
	
}
