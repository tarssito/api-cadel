package br.com.apicadel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.apicadel.domain.CursoDisciplina;
import br.com.apicadel.domain.Disciplina;

@Repository
public interface CursoDisciplinaRepository extends GenericRepository<CursoDisciplina, Long> {

	@Transactional(readOnly=true)
	@Query("from CursoDisciplina cd WHERE cd.disciplina = :disciplina")
	public List<CursoDisciplina> findByDisciplina(@Param("disciplina") Disciplina disciplina);
}
