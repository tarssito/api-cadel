package br.com.apicadel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apicadel.domain.Curso;
import br.com.apicadel.dto.CursoDTO;
import br.com.apicadel.repositories.CursoRepository;

@Service
public class CursoService extends GenericServiceImpl<Curso, Long> {

	private CursoRepository cursoRepository;
	
	@Autowired
	public CursoService(CursoRepository repository) {
		super(repository);
		this.cursoRepository = repository;
	}

	public Curso fromDTO(CursoDTO objDTO) {
		return new Curso(objDTO.getId(), objDTO.getNome());
	}

	public List<Curso> findByNome(String nome) {
		return cursoRepository.findByNomeContaining(nome);
	}
}
