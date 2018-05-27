package br.com.apicadel.domain.enums;

public enum StatusAula {

	ABERTA(1), FECHADA(2);
	
	private int codStatus;
	
	private StatusAula(int codStatus) {
		this.codStatus = codStatus;
	}
	
	public int getCodStatus() {
		return codStatus;
	}
	
	public static StatusAula toEnum(int cod) {
		if (cod == 0) {
			return null;
		}
		for (StatusAula t : StatusAula.values()) {
			if (cod == t.getCodStatus()) {
				return t;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
	
}
