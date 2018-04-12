package br.com.apicadel.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import br.com.apicadel.domain.enums.Sexo;

@Entity
public class Aluno extends Pessoa {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="turma_id")
	private Turma turma;

	@ManyToMany(mappedBy="alunos")
	private List<Aula> frequencias = new ArrayList<>();

	public Aluno() {
	}

	public Aluno(Long id, String nome, String cpf, String matricula, String email, Sexo sexo, Turma turma) {
		super(id, nome, cpf, matricula, email, sexo);
		this.turma = turma;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public List<Aula> getFrequencias() {
		return frequencias;
	}

	public void setFrequencias(List<Aula> frequencias) {
		this.frequencias = frequencias;
	}

	
}
