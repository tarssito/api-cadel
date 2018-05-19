package br.com.apicadel.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.apicadel.domain.enums.Perfil;

@Entity
public class Professor extends Pessoa {
	private static final long serialVersionUID = 1L;

	@ManyToMany
	@JoinTable(name = "professor_disciplina", joinColumns = @JoinColumn(name = "professor_id"), inverseJoinColumns = @JoinColumn(name = "disciplina_id"))
	private List<Disciplina> disciplinas = new ArrayList<>();

	@OneToMany(mappedBy = "professor")
	private List<Aula> aulas = new ArrayList<>();

	@Column(columnDefinition = "int default 10")
	private Integer notificacaoEmail;

	@ManyToMany
	@JoinTable(name = "professor_turma", joinColumns = @JoinColumn(name = "professor_id"), inverseJoinColumns = @JoinColumn(name = "turma_id"))
	private List<Turma> turmas = new ArrayList<>();

	

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	private Set<Integer> perfis = new HashSet<>();
	
	@JsonIgnore
	private String senha;

	public Professor() {
		addPerfil(Perfil.ADMIN);
	}

	public Professor(Long id, String nome, String cpf, String matricula, String email, String sexo, boolean ativo,
			Integer notificacaoEmail, Perfil perfil, String senha) {
		super(id, nome, cpf, matricula, email, sexo);
		this.notificacaoEmail = notificacaoEmail;
		this.senha = senha;
		addPerfil(perfil);
	}

	public Integer getNotificacaoEmail() {
		return notificacaoEmail;
	}

	public void setNotificacaoEmail(Integer notificacaoEmail) {
		this.notificacaoEmail = notificacaoEmail;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public List<Aula> getAulas() {
		return aulas;
	}

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
