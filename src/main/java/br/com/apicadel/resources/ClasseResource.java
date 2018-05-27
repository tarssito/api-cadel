package br.com.apicadel.resources;

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

import br.com.apicadel.domain.Classe;
import br.com.apicadel.domain.ClasseTurma;
import br.com.apicadel.domain.Turma;
import br.com.apicadel.dto.ClasseDTO;
import br.com.apicadel.resources.utils.CodigoMensagem;
import br.com.apicadel.services.ClasseService;
import br.com.apicadel.services.TurmaService;

@RestController
@RequestMapping(value = "/classes")
public class ClasseResource {

	@Autowired
	private ClasseService service;

	@Autowired
	private TurmaService turmaService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Classe> find(@PathVariable Long id) {
		Classe obj = service.find(id);
		for (ClasseTurma ct : obj.getTurmasClasse()) {
			Turma turma = turmaService.find(ct.getTurma().getId());
			Turma newTurma = new Turma();
			newTurma.setId(turma.getId());
			newTurma.setSigla(turma.getSigla());
			obj.getTurmas().add(newTurma);
		}
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> insert(@Valid @RequestBody ClasseDTO objDTO) {
		Classe obj = service.fromDTO(objDTO);
		service.save(obj);
		return ResponseEntity.ok().body(CodigoMensagem.COD_INSERT_SUCCESS.getCodigoMsg());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> update(@Valid @RequestBody ClasseDTO objDTO, @PathVariable Long id) {
		Classe obj = service.fromDTO(objDTO);
		obj.setId(id);
		service.save(obj);
		return ResponseEntity.ok().body(CodigoMensagem.COD_UPDATE_SUCCESS.getCodigoMsg());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.ok().body(CodigoMensagem.COD_DELETE_SUCCESS.getCodigoMsg());
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClasseDTO>> findAll() {
		List<Classe> list = service.findAll();
		list.sort(Comparator.comparing(Classe::getDia));
		List<ClasseDTO> listDTO = list.stream().map(obj -> new ClasseDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ClasseDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Classe> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClasseDTO> listDTO = list.map(obj -> new ClasseDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}

	@RequestMapping(value = "dashboard", method = RequestMethod.GET)
	public ResponseEntity<List<ClasseDTO>> findAlunosDualList(@RequestParam(value = "idProfessor") Long idProfessor) {
		List<Classe> list = service.findClasseDiaProfessor(idProfessor);
		list.sort(Comparator.comparing(Classe::getDia));
		List<ClasseDTO> listDTO = list.stream().map(obj -> new ClasseDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
}
