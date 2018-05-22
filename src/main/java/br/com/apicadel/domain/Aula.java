package br.com.apicadel.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Aula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date data;

	private Date horaAbertura;
	private Date horaFechamento;

	@ManyToOne
	@JoinColumn(name = "professor_disciplina_id")
	private ProfessorDisciplina professorDisciplina;

	@JsonIgnore
	@OneToMany(mappedBy = "aula")
	private List<FrequenciaAluno> frequenciasAlunos = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "aula")
	private List<FrequenciaTurma> frequenciasTurmas = new ArrayList<>();

	public Aula() {
	}

	public Aula(Long id, Date data, Date horaAbertura, Date horaFechamento, ProfessorDisciplina professorDisciplina) {
		super();
		this.id = id;
		this.data = data;
		this.horaAbertura = horaAbertura;
		this.horaFechamento = horaFechamento;
		this.professorDisciplina = professorDisciplina;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getHoraAbertura() {
		return horaAbertura;
	}

	public void setHoraAbertura(Date horaAbertura) {
		this.horaAbertura = horaAbertura;
	}

	public Date getHoraFechamento() {
		return horaFechamento;
	}

	public void setHoraFechamento(Date horaFechamento) {
		this.horaFechamento = horaFechamento;
	}

	public ProfessorDisciplina getProfessorDisciplina() {
		return professorDisciplina;
	}

	public void setProfessorDisciplina(ProfessorDisciplina professorDisciplina) {
		this.professorDisciplina = professorDisciplina;
	}

	public List<FrequenciaAluno> getFrequenciasAlunos() {
		return frequenciasAlunos;
	}

	public void setFrequenciasAlunos(List<FrequenciaAluno> frequenciasAlunos) {
		this.frequenciasAlunos = frequenciasAlunos;
	}

	public List<FrequenciaTurma> getFrequenciasTurmas() {
		return frequenciasTurmas;
	}

	public void setFrequenciasTurmas(List<FrequenciaTurma> frequenciasTurmas) {
		this.frequenciasTurmas = frequenciasTurmas;
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
		Aula other = (Aula) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
