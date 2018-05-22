package br.com.apicadel.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.apicadel.domain.enums.Perfil;

@Entity
public class Professor extends Pessoa {
	private static final long serialVersionUID = 1L;

	@Column(columnDefinition = "int default 10")
	private Integer notificacaoEmail;

	@JsonIgnore
	@Column(name = "perfil_id", nullable = false)
	private Integer perfil;

	private String senha;

	@JsonIgnore
	@OneToMany(mappedBy = "professor")
	private List<ProfessorDisciplina> disciplinasProfessor = new ArrayList<>();
	
	public Professor() {
	}

	public Professor(Long id, String nome, String cpf, String matricula, String email, String sexo, boolean ativo,
			Integer notificacaoEmail, Perfil perfil, String senha) {
		super(id, nome, cpf, matricula, email, sexo);
		this.notificacaoEmail = notificacaoEmail;
		this.perfil = perfil.getCod();
		this.senha = senha;
	}

	public Integer getNotificacaoEmail() {
		return notificacaoEmail;
	}

	public void setNotificacaoEmail(Integer notificacaoEmail) {
		this.notificacaoEmail = notificacaoEmail;
	}

	public Integer getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil.getCod();
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<ProfessorDisciplina> getDisciplinasProfessor() {
		return disciplinasProfessor;
	}

	public void setDisciplinasProfessor(List<ProfessorDisciplina> disciplinasProfessor) {
		this.disciplinasProfessor = disciplinasProfessor;
	}

	
}
