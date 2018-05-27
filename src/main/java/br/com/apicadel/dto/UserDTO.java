package br.com.apicadel.dto;

import java.io.Serializable;

import br.com.apicadel.domain.Professor;
import br.com.apicadel.domain.enums.Perfil;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String cpf;
	private String matricula;
	private String email;
	private String sexo;
	private Integer notificacaoEmail;
	private Perfil perfil;

	public UserDTO() {
	}

	public UserDTO(Professor professor) {
		super();
		this.id = professor.getId();
		this.nome = professor.getNome();
		this.cpf = professor.getCpf();
		this.matricula = professor.getMatricula();
		this.email = professor.getEmail();
		this.sexo = professor.getSexo();
		this.notificacaoEmail = professor.getNotificacaoEmail();
		this.perfil = Perfil.toEnum(professor.getPerfil());

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

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

}
