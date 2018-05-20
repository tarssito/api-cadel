package br.com.apicadel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apicadel.domain.Curso;
import br.com.apicadel.dto.CursoDTO;
import br.com.apicadel.repositories.CursoRepository;

@Service
public class CursoService extends GenericServiceImpl<Curso, Long> {

	@Autowired
	public CursoService(CursoRepository repository) {
		super(repository);
	}

	public Curso fromDTO(CursoDTO objDTO) {
		return new Curso(objDTO.getId(), objDTO.getNome());
	}

}
