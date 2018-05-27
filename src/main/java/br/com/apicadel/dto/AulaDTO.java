package br.com.apicadel.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.apicadel.domain.Aula;
import br.com.apicadel.domain.Curso;
import br.com.apicadel.domain.Disciplina;
import br.com.apicadel.domain.Professor;
import br.com.apicadel.domain.Turma;

public class AulaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotNull(message = "Preenchimento obrigatório")
	private int dia;

	@NotNull(message = "Preenchimento obrigatório")
	private String turno;

	@NotNull(message = "Preenchimento obrigatório")
	private String horaAbertura;

	@NotNull(message = "Preenchimento obrigatório")
	private String horaFechamento;

	@NotNull(message = "Preenchimento obrigatório")
	private Curso curso;

	@NotNull(message = "Preenchimento obrigatório")
	private Professor professor;

	@NotNull(message = "Preenchimento obrigatório")
	private Disciplina disciplina;

	private List<Turma> turmas = new ArrayList<>();

	public AulaDTO() {
	}

	public AulaDTO(Aula aula) {
		super();
		this.id = aula.getId();
		this.dia = aula.getDia();
		this.turno = aula.getTurno();
		this.horaAbertura = aula.getHoraAbertura();
		this.horaFechamento = aula.getHoraFechamento();
		this.curso = aula.getCurso();
		this.professor = aula.getProfessor();
		this.disciplina = aula.getDisciplina();
		this.turmas = aula.getTurmas();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public String getHoraAbertura() {
		return horaAbertura;
	}

	public void setHoraAbertura(String horaAbertura) {
		this.horaAbertura = horaAbertura;
	}

	public String getHoraFechamento() {
		return horaFechamento;
	}

	public void setHoraFechamento(String horaFechamento) {
		this.horaFechamento = horaFechamento;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
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

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

}
