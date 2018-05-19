package br.com.apicadel.repositories;

import org.springframework.stereotype.Repository;

import br.com.apicadel.domain.Professor;

@Repository
public interface ProfessorRepository extends GenericRepository<Professor, Long> {

	Professor findByMatricula(String matricula);
}
