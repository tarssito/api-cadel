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

	@Transactional(readOnly = true)
	@Query("select p from Professor p WHERE p.perfil = 2")
	public List<Professor> findProfessores();
	
	@Transactional(readOnly = true)
	@Query("select distinct p from Professor p left join p.disciplinasProfessor dp WHERE (p.nome LIKE %:nome%) "
			+ "	AND (p.matricula LIKE %:matricula%) "
			+ " AND (dp.disciplina.id = :idDisciplina OR :idDisciplina IS NULL) "
			+ "	AND p.perfil = 2")
	public List<Professor> search(@Param("nome") String nome, @Param("matricula") String matricula,
			@Param("idDisciplina") Long idDisciplina);
}
