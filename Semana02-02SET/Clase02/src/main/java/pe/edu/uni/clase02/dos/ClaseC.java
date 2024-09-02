package pe.edu.uni.clase02.dos;

import pe.edu.uni.clase02.uno.ClaseA;

public class ClaseC extends ClaseA {

	private int n3 = 1000;

	public void metodoC() {
		ClaseA obj = new ClaseA();
		System.out.println("n1 = Sin acceso");
		System.out.println("n2 = Sin acceso");
		System.out.println("this.n3 = " + this.n3);
		System.out.println("super.n3 = " + super.n3);
		System.out.println("n4 = " + obj.n4);
	}

}
