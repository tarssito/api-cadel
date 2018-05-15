package br.com.apicadel.repositories;

import org.springframework.stereotype.Repository;

import br.com.apicadel.domain.Disciplina;

@Repository
public interface DisciplinaRepository extends GenericRepository<Disciplina, Long> {
	
}
