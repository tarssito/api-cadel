package br.com.apicadel.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Turma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, length = 10)
	private String sigla;

	@Column(nullable = false, length = 1)
	private String semestre;

	@Column(nullable = false, length = 4)
	private String ano;

	@ManyToOne
	@JoinColumn(name = "curso_id")
	private Curso curso;

	private String turnoLetivo;

	@JsonIgnore
	@OneToMany(mappedBy = "turma")
	private List<AlunoTurma> alunosTurma = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "turma")
	private List<ProfessorTurma> professoresTurma = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "turma")
	private List<FrequenciaTurma> frequenciasTurma = new ArrayList<>();

	public Turma() {
	}

	public Turma(Long id, String sigla, String semestre, String ano, Curso curso, String turnoLetivo) {
		super();
		this.id = id;
		this.sigla = sigla;
		this.semestre = semestre;
		this.ano = ano;
		this.curso = curso;
		this.turnoLetivo = turnoLetivo;
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

	public List<AlunoTurma> getAlunosTurma() {
		return alunosTurma;
	}

	public void setAlunosTurma(List<AlunoTurma> alunosTurma) {
		this.alunosTurma = alunosTurma;
	}

	public List<ProfessorTurma> getProfessoresTurma() {
		return professoresTurma;
	}

	public void setProfessoresTurma(List<ProfessorTurma> professoresTurma) {
		this.professoresTurma = professoresTurma;
	}

	public List<FrequenciaTurma> getFrequenciasTurma() {
		return frequenciasTurma;
	}

	public void setFrequenciasTurma(List<FrequenciaTurma> frequenciasTurma) {
		this.frequenciasTurma = frequenciasTurma;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Turma other = (Turma) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
