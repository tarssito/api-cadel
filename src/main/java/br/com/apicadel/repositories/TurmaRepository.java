package br.com.apicadel.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.apicadel.domain.Disciplina;
import br.com.apicadel.domain.Turma;

@Repository
public interface TurmaRepository extends GenericRepository<Turma, Long> {

	@Transactional(readOnly=true)
	public List<Turma> findByTurnoLetivo(String turno);
	
	@Transactional(readOnly=true)
	public List<Turma> findByDisciplina(Disciplina disciplina);
}
