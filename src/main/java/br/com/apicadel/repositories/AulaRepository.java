package br.com.apicadel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.apicadel.domain.Aula;

@Repository
public interface AulaRepository extends GenericRepository<Aula, Long> {

	@Query("select a from Aula a where a.status = 1 and a.professor.id = :idProfessor and a.dia = :dia")
	public List<Aula> findAulaAbertaProfessor(@Param("idProfessor") Long idProfessor, @Param("dia") int dia);
}
