package br.com.apicadel.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.apicadel.domain.Aluno;

@Repository
public interface AlunoRepository extends GenericRepository<Aluno, Long> {

	@Transactional(readOnly=true)
	public List<Aluno> findByNomeContaining(String nome);
	
	@Transactional(readOnly=true)
	public List<Aluno> findByMatriculaContaining(String matricula);
	
	@Transactional(readOnly=true)
	public List<Aluno> findByMatriculaContainingAndNomeContaining(String matricula, String nome);
}
