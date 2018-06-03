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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.apicadel.domain.enums.StatusAula;

@Entity
public class Aula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date data;

	@ManyToOne
	@JoinColumn(name = "classe_id")
	private Classe classe;

	private int status;

	private String registro;

	@JsonIgnore
	@OneToMany(mappedBy = "aula")
	private List<FrequenciaAluno> frequenciasAlunos = new ArrayList<>();

	@Transient
	private List<Aluno> alunos = new ArrayList<>();

	public Aula() {
	}

	public Aula(Long id, Date data, Classe classe, StatusAula status, String registro) {
		super();
		this.id = id;
		this.data = data;
		this.classe = classe;
		this.status = status.getCodStatus();
	}

	public Aula(Long id, Date data, Classe classe, StatusAula status, String registro, List<FrequenciaAluno> frequenciasAlunos) {
		super();
		this.id = id;
		this.classe = classe;
		this.status = status.getCodStatus();
		this.registro = registro;
		this.frequenciasAlunos = frequenciasAlunos;
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

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public List<FrequenciaAluno> getFrequenciasAlunos() {
		return frequenciasAlunos;
	}

	public void setFrequenciasAlunos(List<FrequenciaAluno> frequenciasAlunos) {
		this.frequenciasAlunos = frequenciasAlunos;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("Aula, número: 1");
		// builder.append(getId());
		builder.append(", Data: 30/05/2018");
		// builder.append(getData());
		builder.append(", Dia: Quarta");
		// builder.append(DiaSemana.toEnum(getClasse().getDia()));
		builder.append(", Disciplina: Lab. de Software");
		// builder.append(getClasse().getDisciplina().getNome());
		// builder.append(". Caro Professor: ");
		// builder.append(getClasse().getProfessor().getNome());
		builder.append(", Fique atento ao horário de fechamento da aula.");
		builder.append(" Início: 18:30");
		// builder.append(getClasse().getHoraAbertura());
		builder.append(", Término: 20:30");
		// builder.append(getClasse().getHoraFechamento());

		return builder.toString();
	}

}
