package br.com.apicadel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apicadel.domain.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}
