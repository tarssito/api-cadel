package br.com.apicadel.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Aluno extends Pessoa {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@OneToMany(mappedBy = "aluno")
	private List<FrequenciaAluno> frequencias = new ArrayList<>();

	@Transient
	private boolean presenca;

	public Aluno() {
	}

	public Aluno(Long id, String nome, String cpf, String matricula, String email, String sexo) {
		super(id, nome, cpf, matricula, email, sexo);
	}

	public List<FrequenciaAluno> getFrequencias() {
		return frequencias;
	}

	public void setFrequencias(List<FrequenciaAluno> frequencias) {
		this.frequencias = frequencias;
	}

	public boolean getPresenca() {
		return presenca;
	}

	public void setPresenca(boolean presenca) {
		this.presenca = presenca;
	}

}
