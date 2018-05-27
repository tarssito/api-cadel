package br.com.apicadel.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.apicadel.domain.Professor;
import br.com.apicadel.domain.ProfessorDisciplina;

@Repository
public interface ProfessorDisciplinaRepository extends GenericRepository<ProfessorDisciplina, Long> {

	public List<ProfessorDisciplina> findByProfessor(Professor professor);

}
