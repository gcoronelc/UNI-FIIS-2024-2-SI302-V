package pe.edu.uni.aprendiendojava.service;

public class MateService {

		
	public int promedio(int n1, int n2) {
		int pr;
		pr = (n1 + n2) / 2;
		return pr;
	}

	public int promedio(int n1, int n2, int n3) {
		int pr;
		pr = (n1 + n2 + n3) / 3;
		return pr;
	}

	public double otroPromedio(double... datos) {
		double suma = 0.0;
		for(double dato: datos){
			suma += dato;
		}
		return (suma / datos.length);
	}
}
