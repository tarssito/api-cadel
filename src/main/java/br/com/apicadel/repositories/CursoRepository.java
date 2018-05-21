package br.com.apicadel.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.apicadel.domain.Curso;

@Repository
public interface CursoRepository extends GenericRepository<Curso, Long> {

	@Transactional(readOnly=true)
	public List<Curso> findByNomeContaining(String nome);
}
