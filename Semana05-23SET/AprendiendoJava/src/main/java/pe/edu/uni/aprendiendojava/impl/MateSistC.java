package pe.edu.uni.aprendiendojava.impl;

import pe.edu.uni.aprendiendojava.spec.MateSpec;

public class MateSistC implements MateSpec{

	@Override
	public int promedio(int pp, int ep, int ef) {
		return (pp + ep + ef * 2) / 4;
	}
	
}