package br.com.apicadel.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apicadel.domain.Classe;
import br.com.apicadel.domain.ClasseTurma;
import br.com.apicadel.domain.Curso;
import br.com.apicadel.domain.Disciplina;
import br.com.apicadel.domain.Professor;
import br.com.apicadel.domain.Turma;
import br.com.apicadel.domain.enums.DiaSemana;
import br.com.apicadel.domain.enums.TurnoLetivo;
import br.com.apicadel.dto.ClasseDTO;
import br.com.apicadel.repositories.ClasseRepository;
import br.com.apicadel.repositories.ClasseTurmaRepository;
import br.com.apicadel.repositories.CursoRepository;
import br.com.apicadel.repositories.DisciplinaRepository;
import br.com.apicadel.repositories.ProfessorRepository;

@Service
public class ClasseService extends GenericServiceImpl<Classe, Long> {

	private ClasseRepository classeRepository;

	@Autowired
	private CursoRepository cursoRepository;

	@Autowired
	private ProfessorRepository professorRepository;

	@Autowired
	private DisciplinaRepository disciplinaRepository;

	@Autowired
	private ClasseTurmaRepository classeTurmaRepository;

	@Autowired
	public ClasseService(ClasseRepository repository) {
		super(repository);
		this.classeRepository = repository;
	}

	@Override
	public Classe save(Classe entity) {
		if (entity.getId() != null) {
			List<ClasseTurma> turmasClassesBD = classeTurmaRepository.findByClasse(entity);
			classeTurmaRepository.deleteAll(turmasClassesBD);
		}
		Classe classe = super.save(entity);
		List<ClasseTurma> turmasClasse = entity.getTurmasClasse();
		for (ClasseTurma classeTurma : turmasClasse) {
			ClasseTurma ct = new ClasseTurma();
			ct.setClasse(classe);
			ct.setTurma(classeTurma.getTurma());
			classeTurmaRepository.save(ct);
		}
		return classe;
	}

	public Classe fromDTO(ClasseDTO objDTO) {
		DiaSemana dia = DiaSemana.toEnum(objDTO.getDia());
		TurnoLetivo turno = TurnoLetivo.toEnum(objDTO.getTurno());

		Curso curso = cursoRepository.findById(objDTO.getCurso().getId()).get();
		Professor professor = professorRepository.findById(objDTO.getProfessor().getId()).get();
		Disciplina disciplina = disciplinaRepository.findById(objDTO.getDisciplina().getId()).get();

		List<Turma> turmas = objDTO.getTurmas();
		List<ClasseTurma> newTurmasClasse = new ArrayList<>();

		for (Turma turma : turmas) {
			ClasseTurma ct = new ClasseTurma();
			ct.setTurma(turma);
			newTurmasClasse.add(ct);
		}

		return new Classe(objDTO.getId(), dia, turno, objDTO.getHoraAbertura(), objDTO.getHoraFechamento(),
				objDTO.getSemestre(), objDTO.getAno(), curso, professor, disciplina, newTurmasClasse);
	}

	public List<Classe> findClasseDiaProfessor(Long idProfessor) {
		return classeRepository.findClasseDiaProfessor(idProfessor, getCurrentDay());
	}

	public List<Classe> search(Integer dia, String semestre, String ano, Long idCurso) {
		return classeRepository.search(dia, semestre, ano, idCurso);
	}
	
	/**
	 * Método que retorna o código do dia da semana, de acordo com a data atual
	 * 
	 * @return
	 */
	public int getCurrentDay() {
		Date d = new Date();
		Calendar c = new GregorianCalendar();
		c.setTime(d);
		return c.get(Calendar.DAY_OF_WEEK);
	}

}
