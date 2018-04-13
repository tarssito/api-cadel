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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	@JoinColumn(name = "professor_id")
	private Professor professor;

	@ManyToOne
	@JoinColumn(name = "disciplina_id")
	private Disciplina disciplina;

	@ManyToMany
	@JoinTable(name = "frequencia_aluno", joinColumns = @JoinColumn(name = "aula_id"), inverseJoinColumns = @JoinColumn(name = "aluno_id"))
	private List<Aluno> alunos = new ArrayList<>();

	public Aula() {
	}

	public Aula(Long id, Date data, Date horaAbertura, Date horaFechamento, Professor professor,
			Disciplina disciplina) {
		super();
		this.id = id;
		this.data = data;
		this.horaAbertura = horaAbertura;
		this.horaFechamento = horaFechamento;
		this.professor = professor;
		this.disciplina = disciplina;
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

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public List<Aluno> getAlunos() {
		return alunos;
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
