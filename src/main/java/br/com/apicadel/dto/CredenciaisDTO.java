package br.com.apicadel.dto;

import java.io.Serializable;

public class CredenciaisDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String matricula;
	private String senha;

	public CredenciaisDTO() {
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
