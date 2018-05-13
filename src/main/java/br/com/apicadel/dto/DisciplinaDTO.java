package br.com.apicadel.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.apicadel.domain.Disciplina;

public class DisciplinaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 100, message = "O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;

	public DisciplinaDTO() {
	}

	public DisciplinaDTO(Disciplina Disciplina) {
		super();
		this.id = Disciplina.getId();
		this.nome = Disciplina.getNome();
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

}
