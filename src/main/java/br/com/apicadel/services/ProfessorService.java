package br.com.apicadel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apicadel.domain.Professor;
import br.com.apicadel.domain.enums.Perfil;
import br.com.apicadel.dto.ProfessorDTO;
import br.com.apicadel.repositories.ProfessorRepository;

@Service
public class ProfessorService extends GenericServiceImpl<Professor, Long> {

	@Autowired
	public ProfessorService(ProfessorRepository repository) {
		super(repository);
	}

	public Professor fromDTO(ProfessorDTO objDTO) {
		return new Professor(objDTO.getId(), objDTO.getNome(), objDTO.getCpf(), objDTO.getMatricula(), objDTO.getEmail(),
				objDTO.getSexo(), true, objDTO.getNotificacaoEmail(), Perfil.ADMIN);
	}

}
