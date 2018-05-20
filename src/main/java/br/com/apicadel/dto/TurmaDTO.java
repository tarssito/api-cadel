package br.com.apicadel.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.apicadel.domain.Curso;
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

	public TurmaDTO() {
	}

	public TurmaDTO(Turma turma) {
		super();
		this.id = turma.getId();
		this.sigla = turma.getSigla();
		this.semestre = turma.getSemestre();
		this.ano = turma.getAno();
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

}
