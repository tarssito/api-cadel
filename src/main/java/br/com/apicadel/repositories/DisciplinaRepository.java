package br.com.apicadel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.apicadel.domain.Disciplina;

@Repository
public interface DisciplinaRepository extends GenericRepository<Disciplina, Long> {
	
	@Transactional(readOnly=true)
	public List<Disciplina> findByCargaHoraria(int cargaHoraria);
	
	@Transactional(readOnly=true)
	public List<Disciplina> findByNomeContaining(String nome);
	
	@Transactional(readOnly=true)
	public List<Disciplina> findByNomeContainingAndCargaHoraria(String nome, Integer cargaHoraria);
	
	@Query("select d from Disciplina d inner join d.cursosDisciplina cd where cd.curso.id = :idCurso")
	public List<Disciplina> findByCurso(@Param("idCurso") Long idCurso);
}
