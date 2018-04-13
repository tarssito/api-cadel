package br.com.apicadel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apicadel.domain.Aula;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {

}
