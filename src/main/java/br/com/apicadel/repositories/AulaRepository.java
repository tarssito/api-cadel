package br.com.apicadel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.apicadel.domain.Aula;
import br.com.apicadel.domain.Classe;

@Repository
public interface AulaRepository extends GenericRepository<Aula, Long> {

	@Transactional(readOnly = true)
	@Query("select distinct a from Aula a inner join a.classe c inner join a.frequenciasAlunos f WHERE (c.semestre LIKE %:semestre%) "
			+ "	AND (c.ano LIKE %:ano%) " + " AND (c.curso.id = :idCurso OR :idCurso IS NULL) "
			+ " AND (c.disciplina.id = :idDisciplina OR :idDisciplina IS NULL) "
			+ " AND (c.professor.id = :idProfessor OR :idProfessor IS NULL) ")
	List<Aula> search(@Param("semestre") String semestre, @Param("ano") String ano, @Param("idCurso") Long idCurso,
			@Param("idDisciplina") Long idDisciplina, @Param("idProfessor") Long idProfessor);

	@Transactional(readOnly = true)
	@Query("select a from Aula a WHERE a.status = 2 and a.classe = :classe")
	public Aula findByClasse(@Param("classe") Classe classe);

}
