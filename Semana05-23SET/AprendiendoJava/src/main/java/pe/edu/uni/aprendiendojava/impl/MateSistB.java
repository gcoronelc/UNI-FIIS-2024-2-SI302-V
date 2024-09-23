package pe.edu.uni.aprendiendojava.impl;

import pe.edu.uni.aprendiendojava.spec.MateSpec;

public class MateSistB implements MateSpec{

	@Override
	public int promedio(int pp, int ep, int ef) {
		return (pp * 2 + ep + ef) / 4;
	}
	
}
