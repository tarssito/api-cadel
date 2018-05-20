package br.com.apicadel.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.apicadel.domain.Professor;

@Repository
public interface ProfessorRepository extends GenericRepository<Professor, Long> {

	@Query("from Professor p WHERE p.matricula = :matricula")
	public Professor authenticator(@Param("matricula") String matricula);
}
