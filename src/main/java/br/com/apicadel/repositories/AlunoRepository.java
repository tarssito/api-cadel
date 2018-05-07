package br.com.apicadel.repositories;

import org.springframework.stereotype.Repository;

import br.com.apicadel.domain.Aluno;

@Repository
public interface AlunoRepository extends GenericRepository<Aluno, Long> {

}
