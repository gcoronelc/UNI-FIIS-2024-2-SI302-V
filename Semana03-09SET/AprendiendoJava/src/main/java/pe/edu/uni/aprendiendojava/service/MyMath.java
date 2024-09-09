package pe.edu.uni.aprendiendojava.service;

public class MyMath {

	private MyMath() {
		
	}
	
	public static int sumar(int n1, int n2) {
		int suma;
		suma = (n1 + n2);
		return suma;
	}
	
	public static int mayor(int... datos){
		int mayor = datos[0];
		for (int dato : datos){
			if (dato > mayor) mayor = dato;
		}
		return mayor;
	}
	
	public static double potencia(int base, int exponente){
		double p = 1;
		for (int i = 1; i <= exponente; i++) {
			p*=base;
		}
		return p;
	}
	
	public static boolean esPrimo(int n){
		if(n<=1){
			return false;
		}
		boolean primo=true;
		for(int i=2;i<n;i++){
			if(n%i==0){
				primo=false;
				break;
			}
		}
		return primo;
	}
	
	
}
