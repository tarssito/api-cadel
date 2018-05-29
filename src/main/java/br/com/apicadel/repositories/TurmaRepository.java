package br.com.apicadel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.apicadel.domain.Disciplina;
import br.com.apicadel.domain.Turma;

@Repository
public interface TurmaRepository extends GenericRepository<Turma, Long> {

	@Transactional(readOnly = true)
	public List<Turma> findByTurnoLetivo(String turno);

	@Transactional(readOnly = true)
	public List<Turma> findByDisciplina(Disciplina disciplina);

	@Transactional(readOnly=true)
	@Query("select t from Turma t WHERE (t.sigla LIKE %:sigla%) " + "	AND (t.semestre LIKE %:semestre%) "
			+ " AND (t.ano LIKE %:ano%) " + " AND (t.curso.id = :idCurso OR :idCurso IS NULL) ")
	public List<Turma> search(@Param("sigla") String sigla, @Param("semestre") String semestre,
			@Param("ano") String ano, @Param("idCurso") Long idCurso);
}
