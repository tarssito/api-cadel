package br.com.apicadel.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import br.com.apicadel.domain.Aluno;
import br.com.apicadel.domain.Aula;
import br.com.apicadel.domain.FrequenciaAluno;

public class FrequenciaAlunoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotNull(message = "Preenchimento obrigatório")
	private Aula aula;

	@NotNull(message = "Preenchimento obrigatório")
	private Aluno aluno;

	private boolean presenca;

	public FrequenciaAlunoDTO() {
	}

	public FrequenciaAlunoDTO(FrequenciaAluno frequenciaAluno) {
		super();
		this.id = frequenciaAluno.getId();
		this.aula = frequenciaAluno.getAula();
		this.aluno = frequenciaAluno.getAluno();
		this.presenca = frequenciaAluno.isPresenca();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public boolean isPresenca() {
		return presenca;
	}

	public void setPresenca(boolean presenca) {
		this.presenca = presenca;
	}

}
