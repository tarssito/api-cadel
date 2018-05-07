package br.com.apicadel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apicadel.domain.Aluno;
import br.com.apicadel.dto.AlunoDTO;
import br.com.apicadel.repositories.AlunoRepository;

@Service
public class AlunoService extends GenericServiceImpl<Aluno, Long> {

	@Autowired
	public AlunoService(AlunoRepository repository) {
		super(repository);
	}

	public Aluno fromDTO(AlunoDTO objDTO) {
		return new Aluno(objDTO.getId(), objDTO.getNome(), objDTO.getCpf(), objDTO.getMatricula(), objDTO.getEmail(),
				objDTO.getSexo());
	}

}
