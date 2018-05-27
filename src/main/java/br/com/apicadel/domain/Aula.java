package br.com.apicadel.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.apicadel.domain.enums.DiaSemana;
import br.com.apicadel.domain.enums.StatusAula;
import br.com.apicadel.domain.enums.TurnoLetivo;

@Entity
public class Aula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date data;

	@Column(name = "codigo_dia")
	private int dia;

	private String turno;

	private String horaAbertura;
	private String horaFechamento;

	@JsonIgnore
	private int status;

	@JsonIgnore
	@OneToMany(mappedBy = "aula")
	private List<FrequenciaAluno> frequenciasAlunos = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "aula")
	private List<AulaTurma> turmasAula = new ArrayList<>();

	@Transient
	private List<Turma> turmas = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "curso_id")
	private Curso curso;

	@ManyToOne
	@JoinColumn(name = "professor_id")
	private Professor professor;

	@ManyToOne
	@JoinColumn(name = "disciplina_id")
	private Disciplina disciplina;

	public Aula() {
	}

	public Aula(Long id, Date data, DiaSemana dia, TurnoLetivo turno, String horaAbertura, String horaFechamento,
			Curso curso, Professor professor, Disciplina disciplina, StatusAula status) {
		super();
		this.id = id;
		this.data = data;
		this.dia = dia.getCodDiaSemana();
		this.turno = turno.getTurno();
		this.horaAbertura = horaAbertura;
		this.horaFechamento = horaFechamento;
		this.curso = curso;
		this.professor = professor;
		this.disciplina = disciplina;
		this.status = status.getCodStatus();
	}

	public Aula(Long id, Date data, DiaSemana dia, TurnoLetivo turno, String horaAbertura, String horaFechamento,
			Curso curso, Professor professor, Disciplina disciplina, StatusAula status, List<AulaTurma> turmasAula) {
		super();
		this.id = id;
		this.data = data;
		this.dia = dia.getCodDiaSemana();
		this.turno = turno.getTurno();
		this.horaAbertura = horaAbertura;
		this.horaFechamento = horaFechamento;
		this.curso = curso;
		this.professor = professor;
		this.disciplina = disciplina;
		this.status = status.getCodStatus();
		this.turmasAula = turmasAula;
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

	public void setData(Date data) {
		this.data = data;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isAberta() {
		return StatusAula.toEnum(this.status).equals(StatusAula.ABERTA);
	}

	public boolean isFechada() {
		return StatusAula.toEnum(this.status).equals(StatusAula.FECHADA);
	}

	public List<FrequenciaAluno> getFrequenciasAlunos() {
		return frequenciasAlunos;
	}

	public void setFrequenciasAlunos(List<FrequenciaAluno> frequenciasAlunos) {
		this.frequenciasAlunos = frequenciasAlunos;
	}

	public List<AulaTurma> getTurmasAula() {
		return turmasAula;
	}

	public void setTurmasAula(List<AulaTurma> turmasAula) {
		this.turmasAula = turmasAula;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
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
