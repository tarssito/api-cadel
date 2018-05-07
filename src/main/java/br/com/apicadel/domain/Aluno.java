package br.com.apicadel.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Aluno extends Pessoa {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "aluno_disciplina", 
		joinColumns = @JoinColumn(name = "aluno_id"), inverseJoinColumns = @JoinColumn(name = "disciplina_id")
	)
	private List<Disciplina> disciplinas = new ArrayList<>();

	@ManyToMany(mappedBy = "alunos")
	private List<Turma> trumas = new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany(mappedBy = "alunos")
	private List<Aula> frequencias = new ArrayList<>();

	public Aluno() {
	}

	public Aluno(Long id, String nome, String cpf, String matricula, String email, String sexo) {
		super(id, nome, cpf, matricula, email, sexo);
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public List<Turma> getTrumas() {
		return trumas;
	}

	public void setTrumas(List<Turma> trumas) {
		this.trumas = trumas;
	}

	public List<Aula> getFrequencias() {
		return frequencias;
	}

	public void setFrequencias(List<Aula> frequencias) {
		this.frequencias = frequencias;
	}

}
