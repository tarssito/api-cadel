package br.com.apicadel.services;

import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apicadel.domain.Curso;
import br.com.apicadel.domain.Disciplina;
import br.com.apicadel.domain.Professor;
import br.com.apicadel.domain.ProfessorDisciplina;
import br.com.apicadel.domain.enums.Perfil;
import br.com.apicadel.dto.ProfessorDTO;
import br.com.apicadel.dto.UserDTO;
import br.com.apicadel.repositories.CursoRepository;
import br.com.apicadel.repositories.DisciplinaRepository;
import br.com.apicadel.repositories.ProfessorDisciplinaRepository;
import br.com.apicadel.repositories.ProfessorRepository;

@Service
public class ProfessorService extends GenericServiceImpl<Professor, Long> {

	private ProfessorRepository professorRepository;

	@Autowired
	private ProfessorDisciplinaRepository professorDisciplinaRepository;

	@Autowired
	private DisciplinaRepository disciplinaRepository;

	@Autowired
	private CursoRepository cursoRepository;

	@Autowired
	public ProfessorService(ProfessorRepository repository) {
		super(repository);
		this.professorRepository = repository;
	}

	@Override
	public Professor save(Professor entity) {
		List<ProfessorDisciplina> disciplinasProfessor = entity.getDisciplinasProfessor();
		if (entity.getId() != null) {
			List<ProfessorDisciplina> disciplinas = professorDisciplinaRepository.findByProfessor(entity);
			professorDisciplinaRepository.deleteAll(disciplinas);
		}
		Professor professor = super.save(entity);
		for (ProfessorDisciplina professorDisciplina : disciplinasProfessor) {
			ProfessorDisciplina pd = new ProfessorDisciplina();
			pd.setDisciplina(professorDisciplina.getDisciplina());
			pd.setProfessor(professor);
			professorDisciplinaRepository.save(pd);
		}
		return professor;
	}

	public Professor fromDTO(ProfessorDTO objDTO) {
		Curso curso = cursoRepository.findById(objDTO.getCurso().getId()).get();

		List<Disciplina> disciplinas = objDTO.getDisciplinas();
		List<ProfessorDisciplina> newDisciplinasProfessor = new ArrayList<>();
		for (Disciplina disciplina : disciplinas) {
			ProfessorDisciplina professorDisciplina = new ProfessorDisciplina();
			Disciplina disciplinaBd = disciplinaRepository.findById(disciplina.getId()).get();
			professorDisciplina.setDisciplina(disciplinaBd);
			newDisciplinasProfessor.add(professorDisciplina);
		}
		return new Professor(objDTO.getId(), objDTO.getNome(), objDTO.getCpf(), objDTO.getMatricula(),
				objDTO.getEmail(), objDTO.getSexo(), true, objDTO.getNotificacaoEmail(), Perfil.PROFESSOR,
				BCrypt.hashpw(objDTO.getCpf(), BCrypt.gensalt()), newDisciplinasProfessor, curso);
	}

	public UserDTO fromUser(Professor obj) {
		return new UserDTO(obj);
	}

	public Professor authenticator(Professor obj) {
		Professor professor = professorRepository.authenticator(obj.getMatricula());
		if (professor != null && BCrypt.checkpw(obj.getSenha(), professor.getSenha())) {
			return professor;
		}
		return null;
	}

	public List<Professor> search(String nome, String matricula, Long idDisciplina) {
		return professorRepository.search(nome, matricula, idDisciplina);
	}

}
