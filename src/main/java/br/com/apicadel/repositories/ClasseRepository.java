package br.com.apicadel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.apicadel.domain.Classe;

@Repository
public interface ClasseRepository extends GenericRepository<Classe, Long> {

	@Query("select c from Classe c where c.professor.id = :idProfessor and c.dia = :dia")
	public List<Classe> findClasseDiaProfessor(@Param("idProfessor") Long idProfessor, @Param("dia") int dia);
}
