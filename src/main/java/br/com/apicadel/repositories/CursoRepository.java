package br.com.apicadel.repositories;

import org.springframework.stereotype.Repository;

import br.com.apicadel.domain.Curso;

@Repository
public interface CursoRepository extends GenericRepository<Curso, Long> {

}
