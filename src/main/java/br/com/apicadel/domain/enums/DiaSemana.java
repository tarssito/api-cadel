package br.com.apicadel.domain.enums;

public enum DiaSemana {

	SEGUNDA(2), TERCA(3), QUARTA(4), QUINTA(5), SEXTA(6), SABADO(7);

	private int codDiaSemana;

	private DiaSemana(int dia) {
		this.codDiaSemana = dia;
	}

	public int getCodDiaSemana() {
		return codDiaSemana;
	}

	public static DiaSemana toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (DiaSemana t : DiaSemana.values()) {
			if (cod.equals(t.getCodDiaSemana())) {
				return t;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
