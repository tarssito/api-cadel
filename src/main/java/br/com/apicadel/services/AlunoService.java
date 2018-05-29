package br.com.apicadel.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apicadel.domain.Aluno;
import br.com.apicadel.domain.AlunoTurma;
import br.com.apicadel.domain.Turma;
import br.com.apicadel.dto.AlunoDTO;
import br.com.apicadel.repositories.AlunoRepository;
import br.com.apicadel.repositories.AlunoTurmaRepository;

@Service
public class AlunoService extends GenericServiceImpl<Aluno, Long> {

	@Autowired
	private AlunoTurmaRepository alunoTurmaRepository;
	
	private AlunoRepository alunoRepository;

	@Autowired
	public AlunoService(AlunoRepository repository) {
		super(repository);
		this.alunoRepository = repository;
	}

	public Aluno fromDTO(AlunoDTO objDTO) {
		return new Aluno(objDTO.getId(), objDTO.getNome(), objDTO.getCpf(), objDTO.getMatricula(), objDTO.getEmail(),
				objDTO.getSexo());
	}

	public List<Aluno> search(AlunoDTO aluno) {
		if (aluno.getNome() != null && aluno.getMatricula() != null) {
			return alunoRepository.findByMatriculaContainingAndNomeContaining(aluno.getMatricula(), aluno.getNome());
		} else if (aluno.getNome() != null && aluno.getMatricula() == null) {
			return alunoRepository.findByNomeContaining(aluno.getNome());
		} else {
			return alunoRepository.findByMatriculaContaining(aluno.getMatricula());
		}		
	}
	
	public List<Aluno> findByMatricula(String matricula) {
		return alunoRepository.findByMatriculaContaining(matricula);
	}
	
	public List<Aluno> findByTurma(Turma turma) {
		List<AlunoTurma> list = alunoTurmaRepository.findByTurma(turma);
		return list.stream().map(a -> a.getAluno()).collect(Collectors.toList());
	}
	
}
