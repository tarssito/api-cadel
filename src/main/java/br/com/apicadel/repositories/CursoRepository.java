package br.com.apicadel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apicadel.domain.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

}
