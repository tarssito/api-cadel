package br.com.apicadel.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.apicadel.domain.AlunoTurma;
import br.com.apicadel.domain.Turma;

@Repository
public interface AlunoTurmaRepository extends GenericRepository<AlunoTurma, Long> {

	public List<AlunoTurma> findByTurma(Turma turma);
}
