package br.com.apicadel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.apicadel.domain.Professor;
import br.com.apicadel.domain.enums.Perfil;
import br.com.apicadel.dto.ProfessorDTO;
import br.com.apicadel.repositories.ProfessorRepository;

@Service
public class ProfessorService extends GenericServiceImpl<Professor, Long> {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public ProfessorService(ProfessorRepository repository) {
		super(repository);
	}

	public Professor fromDTO(ProfessorDTO objDTO) {
		return new Professor(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getMatricula(), objDTO.getEmail(),
				objDTO.getSexo(), true, objDTO.getNotificacaoEmail(), Perfil.toEnum(objDTO.getPerfil()),
				passwordEncoder.encode(objDTO.getSenha()));
	}

}
