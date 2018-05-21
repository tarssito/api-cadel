package br.com.apicadel.resources;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.apicadel.domain.Curso;
import br.com.apicadel.domain.CursoDisciplina;
import br.com.apicadel.domain.Disciplina;
import br.com.apicadel.dto.DisciplinaDTO;
import br.com.apicadel.services.CursoService;
import br.com.apicadel.services.DisciplinaService;

@RestController
@RequestMapping(value = "/disciplinas")
public class DisciplinaResource {

	@Autowired
	private DisciplinaService service;

	@Autowired
	private CursoService cursoService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Disciplina> find(@PathVariable Long id) {
		Disciplina obj = service.find(id);
		for (CursoDisciplina cd : obj.getCursosDisciplina()) {
			Curso curso = cursoService.find(cd.getCurso().getId());
			obj.getCursos().add(curso);
		}
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody DisciplinaDTO objDTO) {
		Disciplina obj = service.fromDTO(objDTO);
		obj = service.save(obj);
		/* Retorna na url o id inserido (Disciplinas/{id}) */
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody DisciplinaDTO objDTO, @PathVariable Long id) {
		Disciplina obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.save(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<DisciplinaDTO>> findAll() {
		List<Disciplina> list = service.findAll();
		list.sort(Comparator.comparing(Disciplina::getNome));
		List<DisciplinaDTO> listDTO = list.stream().map(obj -> new DisciplinaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity<List<DisciplinaDTO>> search(@RequestBody DisciplinaDTO objDTO) {
		List<Disciplina> list = service.search(objDTO);
		list.sort(Comparator.comparing(Disciplina::getNome));
		List<DisciplinaDTO> listDTO = list.stream().map(obj -> new DisciplinaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<DisciplinaDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Disciplina> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<DisciplinaDTO> listDTO = list.map(obj -> new DisciplinaDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
}
