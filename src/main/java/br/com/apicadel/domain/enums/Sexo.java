package br.com.apicadel.domain.enums;


public enum Sexo {

	M('M'), F('F');

	private char sexo;
	
	private Sexo(char sexo) {
		this.sexo = sexo;
	}
	
	public char getSexo() {
		return sexo;
	}
}
