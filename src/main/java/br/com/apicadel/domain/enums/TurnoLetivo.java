package br.com.apicadel.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TurnoLetivo {

	MATUTINO("M"), VESPERTINO("V"), NOTURNO("N");

	private String turno;

	private TurnoLetivo(String turno) {
		this.turno = turno;
	}

	@JsonValue
	public String getTurno() {
		return turno;
	}
	
	public static TurnoLetivo toEnum(String cod) {
		if (cod == null) {
			return null;
		}
		for (TurnoLetivo t : TurnoLetivo.values()) {
			if (cod.equals(t.getTurno())) {
				return t;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
	
}
