package br.com.apicadel.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Disciplina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, length = 100)
	private String nome;

	@Column(nullable = false)
	private int cargaHoraria;

	@JsonIgnore
	@OneToMany(mappedBy = "disciplina")
	private List<CursoDisciplina> cursosDisciplina = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "disciplina")
	private List<ProfessorDisciplina> professoresDisciplina = new ArrayList<>();

	@Transient
	private List<Curso> cursos = new ArrayList<>();

	public Disciplina() {
	}

	public Disciplina(Long id, String nome, int cargaHoraria) {
		super();
		this.id = id;
		this.nome = nome;
		this.cargaHoraria = cargaHoraria;
	}

	public Disciplina(Long id, String nome, int cargaHoraria, List<CursoDisciplina> cursos) {
		super();
		this.id = id;
		this.nome = nome;
		this.cargaHoraria = cargaHoraria;
		this.cursosDisciplina = cursos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public List<ProfessorDisciplina> getProfessoresDisciplina() {
		return professoresDisciplina;
	}

	public void setProfessoresDisciplina(List<ProfessorDisciplina> professoresDisciplina) {
		this.professoresDisciplina = professoresDisciplina;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public List<CursoDisciplina> getCursosDisciplina() {
		return cursosDisciplina;
	}

	public void setCursosDisciplina(List<CursoDisciplina> cursos) {
		this.cursosDisciplina = cursos;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
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
		Disciplina other = (Disciplina) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
