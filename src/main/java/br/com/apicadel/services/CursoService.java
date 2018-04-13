package br.com.apicadel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.apicadel.domain.Curso;
import br.com.apicadel.dto.CursoDTO;
import br.com.apicadel.repositories.CursoRepository;
import br.com.apicadel.services.exceptions.DataIntegrityException;
import br.com.apicadel.services.exceptions.ObjectNotFoundException;

@Service
public class CursoService {

	@Autowired
	private CursoRepository repository;

	public Curso find(Long id) {
		Optional<Curso> curso = repository.findById(id);
		return curso.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Curso.class.getName()));
	}

	public List<Curso> findAll() {
		return repository.findAll();
	}

	public Curso insert(Curso obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Curso update(Curso obj) {
		Curso newObj = find(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	public void delete(Long id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um curso que possuí turmas e disciplinas");
		}
	}

	public Page<Curso> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	public Curso fromDTO(CursoDTO objDTO) {
		return new Curso(objDTO.getId(), objDTO.getNome());
	}
	
	private void updateData(Curso newObj, Curso obj) {
		newObj.setNome(obj.getNome());
	}
}
