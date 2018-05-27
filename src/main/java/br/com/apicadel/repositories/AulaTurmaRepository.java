package br.com.apicadel.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.apicadel.domain.Aula;
import br.com.apicadel.domain.AulaTurma;

@Repository
public interface AulaTurmaRepository extends GenericRepository<AulaTurma, Long> {

	public List<AulaTurma> findByAula(Aula aula);
}
