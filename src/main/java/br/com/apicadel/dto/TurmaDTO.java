package br.com.apicadel.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.apicadel.domain.Aluno;
import br.com.apicadel.domain.Curso;
import br.com.apicadel.domain.Disciplina;
import br.com.apicadel.domain.Turma;

public class TurmaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 10, message = "O tamanho deve ser entre 5 e 10 caracteres")
	private String sigla;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String semestre;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String ano;

	private Curso curso;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String turnoLetivo;

	private Disciplina disciplina;

	private List<Aluno> alunos = new ArrayList<>();

	public TurmaDTO() {
	}

	public TurmaDTO(Turma turma) {
		super();
		this.id = turma.getId();
		this.sigla = turma.getSigla();
		this.semestre = turma.getSemestre();
		this.ano = turma.getAno();
		this.turnoLetivo = turma.getTurnoLetivo();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public String getTurnoLetivo() {
		return turnoLetivo;
	}

	public void setTurnoLetivo(String turnoLetivo) {
		this.turnoLetivo = turnoLetivo;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

}
