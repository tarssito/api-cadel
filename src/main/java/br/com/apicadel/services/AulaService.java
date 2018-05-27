package br.com.apicadel.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apicadel.domain.Aula;
import br.com.apicadel.domain.AulaTurma;
import br.com.apicadel.domain.Curso;
import br.com.apicadel.domain.Disciplina;
import br.com.apicadel.domain.Professor;
import br.com.apicadel.domain.Turma;
import br.com.apicadel.domain.enums.DiaSemana;
import br.com.apicadel.domain.enums.StatusAula;
import br.com.apicadel.domain.enums.TurnoLetivo;
import br.com.apicadel.dto.AulaDTO;
import br.com.apicadel.repositories.AulaRepository;
import br.com.apicadel.repositories.AulaTurmaRepository;
import br.com.apicadel.repositories.CursoRepository;
import br.com.apicadel.repositories.DisciplinaRepository;
import br.com.apicadel.repositories.ProfessorRepository;

@Service
public class AulaService extends GenericServiceImpl<Aula, Long> {

	@Autowired
	private CursoRepository cursoRepository;

	@Autowired
	private ProfessorRepository professorRepository;

	@Autowired
	private DisciplinaRepository disciplinaRepository;

	@Autowired
	private AulaTurmaRepository aulaTurmaRepository;

	@Autowired
	public AulaService(AulaRepository repository) {
		super(repository);
	}

	@Override
	public Aula save(Aula entity) {
		if (entity.getId() != null) {
			List<AulaTurma> turmasAulasBD = aulaTurmaRepository.findByAula(entity);
			aulaTurmaRepository.deleteAll(turmasAulasBD);
		}
		Aula aula = super.save(entity);
		List<AulaTurma> turmasAula = entity.getTurmasAula();
		for (AulaTurma aulaTurma : turmasAula) {
			AulaTurma at = new AulaTurma();
			at.setAula(aula);
			at.setTurma(aulaTurma.getTurma());
			aulaTurmaRepository.save(at);
		}
		return aula;
	}

	public Aula fromDTO(AulaDTO objDTO) {
		DiaSemana dia = DiaSemana.toEnum(objDTO.getDia());
		TurnoLetivo turno = TurnoLetivo.toEnum(objDTO.getTurno());

		Curso curso = cursoRepository.findById(objDTO.getCurso().getId()).get();
		Professor professor = professorRepository.findById(objDTO.getProfessor().getId()).get();
		Disciplina disciplina = disciplinaRepository.findById(objDTO.getDisciplina().getId()).get();

		List<Turma> turmas = objDTO.getTurmas();
		List<AulaTurma> newTurmasAula = new ArrayList<>();

		LocalDate dataDeInscricao = LocalDate.now();
		Date data = Date.from(dataDeInscricao.atStartOfDay(ZoneId.systemDefault()).toInstant());

		for (Turma turma : turmas) {
			AulaTurma at = new AulaTurma();
			at.setTurma(turma);
			newTurmasAula.add(at);
		}
		
		return new Aula(objDTO.getId(), data, dia, turno, objDTO.getHoraAbertura(), objDTO.getHoraFechamento(), curso,
				professor, disciplina, StatusAula.ABERTA, newTurmasAula);
	}

}
