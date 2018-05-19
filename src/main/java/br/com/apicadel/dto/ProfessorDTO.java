package br.com.apicadel.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class ProfessorDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 100, message = "O tamanho deve ser entre 5 e 100 caracteres")
	private String nome;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 11, max = 11, message = "O tamanho deve conter somente 11 caracteres")
	private String cpf;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 25, message = "O tamanho deve ser entre 5 e 25 caracteres")
	private String matricula;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "Email inválido")
	private String email;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String sexo;

	private Integer notificacaoEmail;

	private Integer perfil;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String senha;

	public ProfessorDTO() {
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
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

	public void setPerfil(Integer perfil) {
		this.perfil = perfil;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
