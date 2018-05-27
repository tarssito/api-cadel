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

import br.com.apicadel.domain.Aula;
import br.com.apicadel.domain.AulaTurma;
import br.com.apicadel.domain.Turma;
import br.com.apicadel.dto.AulaDTO;
import br.com.apicadel.resources.utils.CodigoMensagem;
import br.com.apicadel.services.AulaService;
import br.com.apicadel.services.TurmaService;

@RestController
@RequestMapping(value = "/aulas")
public class AulaResource {

	@Autowired
	private AulaService service;

	@Autowired
	private TurmaService turmaService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Aula> find(@PathVariable Long id) {
		Aula obj = service.find(id);
		for (AulaTurma at : obj.getTurmasAula()) {
			Turma turma = turmaService.find(at.getTurma().getId());
			Turma newTurma = new Turma();
			newTurma.setId(turma.getId());
			newTurma.setSigla(turma.getSigla());
			obj.getTurmas().add(newTurma);
		}
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> insert(@Valid @RequestBody AulaDTO objDTO) {
		Aula obj = service.fromDTO(objDTO);
		service.save(obj);
		return ResponseEntity.ok().body(CodigoMensagem.COD_INSERT_SUCCESS.getCodigoMsg());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> update(@Valid @RequestBody AulaDTO objDTO, @PathVariable Long id) {
		Aula obj = service.fromDTO(objDTO);
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
	public ResponseEntity<List<AulaDTO>> findAll() {
		List<Aula> list = service.findAll();
		list.sort(Comparator.comparing(Aula::getData));
		List<AulaDTO> listDTO = list.stream().map(obj -> new AulaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<AulaDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Aula> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<AulaDTO> listDTO = list.map(obj -> new AulaDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}

	@RequestMapping(value = "dashboard", method = RequestMethod.GET)
	public ResponseEntity<List<AulaDTO>> findAlunosDualList(@RequestParam(value = "idProfessor") Long idProfessor) {
		List<Aula> list = service.findAulasAbertasProfessor(idProfessor);
		list.sort(Comparator.comparing(Aula::getData));
		List<AulaDTO> listDTO = list.stream().map(obj -> new AulaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
}
