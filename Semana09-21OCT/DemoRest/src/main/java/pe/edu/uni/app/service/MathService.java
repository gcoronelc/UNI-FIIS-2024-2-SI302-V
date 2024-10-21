package pe.edu.uni.app.service;

import org.springframework.stereotype.Service;

@Service
public class MathService {

	
	public long factorial(int n) {
		long f = 1;
		while(n>1) {
			f *= n--;
		}
		return f;
	}
	
	
	public long potencia(int base, int exponente) {
		long p = 1;
		for(int i=1; i<=exponente; i++) {
			p *= base;
		}
		return p;
	}
}
