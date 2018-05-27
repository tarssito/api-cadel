package br.com.apicadel.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.apicadel.domain.Classe;
import br.com.apicadel.domain.ClasseTurma;

@Repository
public interface ClasseTurmaRepository extends GenericRepository<ClasseTurma, Long> {

	public List<ClasseTurma> findByClasse(Classe classe);
}
