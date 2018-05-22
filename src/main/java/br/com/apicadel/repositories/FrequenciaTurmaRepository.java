package br.com.apicadel.repositories;

import org.springframework.stereotype.Repository;

import br.com.apicadel.domain.FrequenciaAluno;

@Repository
public interface FrequenciaTurmaRepository extends GenericRepository<FrequenciaAluno, Long> {

}
