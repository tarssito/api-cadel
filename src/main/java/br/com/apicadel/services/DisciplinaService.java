package br.com.apicadel.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apicadel.domain.Curso;
import br.com.apicadel.domain.CursoDisciplina;
import br.com.apicadel.domain.Disciplina;
import br.com.apicadel.dto.DisciplinaDTO;
import br.com.apicadel.repositories.CursoDisciplinaRepository;
import br.com.apicadel.repositories.CursoRepository;
import br.com.apicadel.repositories.DisciplinaRepository;

@Service
public class DisciplinaService extends GenericServiceImpl<Disciplina, Long> {
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private CursoDisciplinaRepository cursoDisciplinaRepository;
	
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
	@Autowired
	public DisciplinaService(DisciplinaRepository repository) {
		super(repository);
		this.disciplinaRepository = repository;
	}
	
	@Override
	public Disciplina save(Disciplina entity) {	
		List<CursoDisciplina> cursosDisciplinas = entity.getCursosDisciplina();
		if (entity.getId() != null) {
			List<CursoDisciplina> cursos = cursoDisciplinaRepository.findByDisciplina(entity);
			cursoDisciplinaRepository.deleteAll(cursos);
		}
		Disciplina disciplina = super.save(entity);
		for (CursoDisciplina cursoDisciplina : cursosDisciplinas) {
			CursoDisciplina cd = new CursoDisciplina();
			cd.setDisciplina(disciplina);
			cd.setCurso(cursoDisciplina.getCurso());
			cursoDisciplinaRepository.save(cd);
		}		
		return disciplina;
	}
	
	@Override
	public void delete(Long id) {
		Disciplina disciplina = find(id);
		List<CursoDisciplina> cursos = cursoDisciplinaRepository.findByDisciplina(disciplina);
		cursoDisciplinaRepository.deleteAll(cursos);
		super.delete(id);
	}
	
	public Disciplina fromDTO(DisciplinaDTO objDTO) {
		List<Curso> cursos = objDTO.getCursos();
		List<CursoDisciplina> newCursos = new ArrayList<>();
		for (Curso curso : cursos) {
			CursoDisciplina cd = new CursoDisciplina();
			Curso cursoBd = cursoRepository.findById(curso.getId()).get();
			cd.setCurso(cursoBd);
			newCursos.add(cd);
		}
		return new Disciplina(objDTO.getId(), objDTO.getNome(), objDTO.getCargaHoraria(), newCursos);
	}

	public List<Disciplina> search(DisciplinaDTO disciplina) {
		if (disciplina.getNome() != null && disciplina.getCargaHoraria() > 0) {
			return disciplinaRepository.findByNomeContainingAndCargaHoraria(disciplina.getNome(), disciplina.getCargaHoraria());
		} else if (disciplina.getNome() != null && disciplina.getCargaHoraria() == 0) {
			return disciplinaRepository.findByNomeContaining(disciplina.getNome());
		} else {
			return disciplinaRepository.findByCargaHoraria(disciplina.getCargaHoraria());
		}		
	}
	
	public List<Disciplina> findByCurso(Long idCurso) {
		return disciplinaRepository.findByCurso(idCurso);
	}
}
