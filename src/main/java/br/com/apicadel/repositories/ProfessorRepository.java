package br.com.apicadel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.apicadel.domain.Professor;

@Repository
public interface ProfessorRepository extends GenericRepository<Professor, Long> {

	@Query("from Professor p WHERE p.matricula = :matricula")
	public Professor authenticator(@Param("matricula") String matricula);
	
	@Transactional(readOnly=true)
	public List<Professor> findByNomeContaining(String nome);
	
	@Transactional(readOnly=true)
	public List<Professor> findByMatriculaContaining(String matricula);
	
	@Transactional(readOnly=true)
	public List<Professor> findByMatriculaContainingAndNomeContaining(String matricula, String nome);
}
