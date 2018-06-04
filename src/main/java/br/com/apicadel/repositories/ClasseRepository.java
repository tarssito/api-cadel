package br.com.apicadel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.apicadel.domain.Classe;

@Repository
public interface ClasseRepository extends GenericRepository<Classe, Long> {

	@Query("select c from Classe c where c.professor.id = :idProfessor and c.dia = :dia"
			+ " AND c not in (select a.classe from Aula a where a.status = 2) ")
	public List<Classe> findClasseDiaProfessor(@Param("idProfessor") Long idProfessor, @Param("dia") int dia);

	@Transactional(readOnly = true)
	@Query("select c from Classe c WHERE (c.dia = :dia OR :dia IS NULL) " + " AND (c.semestre LIKE %:semestre%) "
			+ " AND (c.ano LIKE %:ano%) " + " AND (c.curso.id = :idCurso OR :idCurso IS NULL) ")
	public List<Classe> search(@Param("dia") Integer dia, @Param("semestre") String semestre, @Param("ano") String ano,
			@Param("idCurso") Long idCurso);
}
