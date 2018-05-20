package br.com.apicadel.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

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

	@OneToMany(mappedBy = "disciplina")
	private List<CursoDisciplina> cursos = new ArrayList<>();

	@ManyToMany(mappedBy = "disciplinas")
	private List<Professor> professores = new ArrayList<>();

	@ManyToMany(mappedBy = "disciplinas")
	private List<Aluno> alunos = new ArrayList<>();

	@OneToMany(mappedBy = "disciplina")
	private List<Aula> aulas = new ArrayList<>();

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
		this.cursos = cursos;
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

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public List<CursoDisciplina> getCursos() {
		return cursos;
	}

	public void setCursos(List<CursoDisciplina> cursos) {
		this.cursos = cursos;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public List<Aula> getAulas() {
		return aulas;
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
