package pe.edu.uni.clase02.dos;

import pe.edu.uni.clase02.uno.ClaseA;

public class ClaseD extends ClaseA {

	public void metodoD() {
		ClaseA obj = new ClaseA();
		System.out.println("n1 = Sin acceso");
		System.out.println("n2 = Sin acceso");
		System.out.println("n3 = Sin acceso");
		System.out.println("n4 = " + obj.n4);
	}

}
