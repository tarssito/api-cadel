package br.com.apicadel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apicadel.domain.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {

}
