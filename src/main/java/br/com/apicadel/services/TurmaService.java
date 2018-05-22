package br.com.apicadel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apicadel.domain.Curso;
import br.com.apicadel.domain.Turma;
import br.com.apicadel.dto.TurmaDTO;
import br.com.apicadel.repositories.CursoRepository;
import br.com.apicadel.repositories.TurmaRepository;

@Service
public class TurmaService extends GenericServiceImpl<Turma, Long> {

	@Autowired
	private CursoRepository cursoRepository;

	@Autowired
	public TurmaService(TurmaRepository repository) {
		super(repository);
	}

	public Turma fromDTO(TurmaDTO objDTO) {
		Curso curso = cursoRepository.findById(objDTO.getCurso().getId()).get();
		objDTO.setCurso(curso);
		return new Turma(objDTO.getId(), objDTO.getSigla(), objDTO.getSemestre(), objDTO.getAno(), objDTO.getCurso(),
				objDTO.getTurnoLetivo());
	}

}
