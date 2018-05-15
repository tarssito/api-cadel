package br.com.apicadel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apicadel.domain.Disciplina;
import br.com.apicadel.dto.DisciplinaDTO;
import br.com.apicadel.repositories.DisciplinaRepository;

@Service
public class DisciplinaService extends GenericServiceImpl<Disciplina, Long> {

	@Autowired
	public DisciplinaService(DisciplinaRepository repository) {
		super(repository);
	}

	public Disciplina fromDTO(DisciplinaDTO objDTO) {
		return new Disciplina(objDTO.getId(), objDTO.getNome(), objDTO.getCargaHoraria());
	}

}
