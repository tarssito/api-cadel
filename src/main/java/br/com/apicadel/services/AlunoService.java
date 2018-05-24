package br.com.apicadel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apicadel.domain.Aluno;
import br.com.apicadel.dto.AlunoDTO;
import br.com.apicadel.repositories.AlunoRepository;

@Service
public class AlunoService extends GenericServiceImpl<Aluno, Long> {

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
	
}
