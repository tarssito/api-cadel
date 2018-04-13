package br.com.apicadel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apicadel.domain.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}
