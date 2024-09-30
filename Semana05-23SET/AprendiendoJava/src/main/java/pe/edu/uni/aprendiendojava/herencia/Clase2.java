package pe.edu.uni.aprendiendojava.herencia;

public class Clase2 extends Clase1{

	@Override
	public int sumar(int n1, int n2) {
		int suma = (n1*n2)/(n1-n2);
		return suma; 
	}
	
	public long potencia(int base, int exponente){
		if(exponente==0){
			return 1;
		}
		return base * potencia(base, exponente-1);
	}
	
	
}
