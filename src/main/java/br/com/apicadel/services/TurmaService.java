package br.com.apicadel.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apicadel.domain.Aluno;
import br.com.apicadel.domain.AlunoTurma;
import br.com.apicadel.domain.Curso;
import br.com.apicadel.domain.Disciplina;
import br.com.apicadel.domain.Turma;
import br.com.apicadel.dto.TurmaDTO;
import br.com.apicadel.repositories.AlunoRepository;
import br.com.apicadel.repositories.AlunoTurmaRepository;
import br.com.apicadel.repositories.CursoRepository;
import br.com.apicadel.repositories.DisciplinaRepository;
import br.com.apicadel.repositories.TurmaRepository;

@Service
public class TurmaService extends GenericServiceImpl<Turma, Long> {

	private TurmaRepository turmaRepository;

	@Autowired
	private CursoRepository cursoRepository;

	@Autowired
	private DisciplinaRepository disciplinaRepository;

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private AlunoTurmaRepository alunoTurmaRepository;

	@Autowired
	public TurmaService(TurmaRepository repository) {
		super(repository);
		this.turmaRepository = repository;
	}

	@Override
	public Turma save(Turma entity) {
		List<AlunoTurma> alunosTurma = entity.getAlunosTurma();
		if (entity.getId() != null) {
			List<AlunoTurma> alunos = alunoTurmaRepository.findByTurma(entity);
			alunoTurmaRepository.deleteAll(alunos);
		}
		Turma turma = super.save(entity);
		for (AlunoTurma alunoTurma : alunosTurma) {
			AlunoTurma at = new AlunoTurma();
			at.setTurma(turma);
			at.setAluno(alunoTurma.getAluno());
			alunoTurmaRepository.save(at);
		}
		return turma;
	}

	public Turma fromDTO(TurmaDTO objDTO) {
		Curso curso = cursoRepository.findById(objDTO.getCurso().getId()).get();
		objDTO.setCurso(curso);
		Disciplina disciplina = disciplinaRepository.findById(objDTO.getDisciplina().getId()).get();
		objDTO.setDisciplina(disciplina);
		List<Aluno> alunos = objDTO.getAlunos();
		List<AlunoTurma> newAlunosTurma = new ArrayList<>();
		for (Aluno aluno : alunos) {
			AlunoTurma alunoTurma = new AlunoTurma();
			Aluno alunoBd = alunoRepository.findById(aluno.getId()).get();
			alunoTurma.setAluno(alunoBd);
			newAlunosTurma.add(alunoTurma);
		}
		return new Turma(objDTO.getId(), objDTO.getSigla(), objDTO.getSemestre(), objDTO.getAno(), objDTO.getCurso(),
				objDTO.getTurnoLetivo(), objDTO.getDisciplina(), newAlunosTurma);
	}

	public List<Turma> findByTurno(String turno) {
		return turmaRepository.findByTurnoLetivo(turno);
	}

	public List<Turma> findByDisciplina(Long idDisciplina) {
		Disciplina disciplina = disciplinaRepository.findById(idDisciplina).get();
		return turmaRepository.findByDisciplina(disciplina);
	}

	public List<Turma> search(String sigla, String semestre, String ano, String turno, Long idCurso, Long idDisciplina) {
		return turmaRepository.search(sigla, semestre, ano, turno, idCurso, idDisciplina);
	}
}
