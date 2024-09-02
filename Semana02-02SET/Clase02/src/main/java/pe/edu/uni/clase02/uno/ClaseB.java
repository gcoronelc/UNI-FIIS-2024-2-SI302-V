package pe.edu.uni.clase02.uno;

public class ClaseB {
	
	public void metodoB(){
		ClaseA obj = new ClaseA();
		System.out.println("n1 = Sin acceso");
		System.out.println("n2 = " + obj.n2);
		System.out.println("n3 = " + obj.n3);
		System.out.println("n4 = " + obj.n4);
	}
}
