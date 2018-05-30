package br.com.apicadel.resources;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.apicadel.domain.Disciplina;
import br.com.apicadel.domain.Professor;
import br.com.apicadel.domain.ProfessorDisciplina;
import br.com.apicadel.dto.ProfessorDTO;
import br.com.apicadel.dto.UserDTO;
import br.com.apicadel.resources.utils.CodigoMensagem;
import br.com.apicadel.services.DisciplinaService;
import br.com.apicadel.services.ProfessorService;

@RestController
@RequestMapping(value = "/professores")
public class ProfessorResource {

	@Autowired
	private ProfessorService service;

	@Autowired
	private DisciplinaService disciplinaService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ProfessorDTO> find(@PathVariable Long id) {
		Professor obj = service.find(id);
		for (ProfessorDisciplina pd : obj.getDisciplinasProfessor()) {
			Disciplina disciplina = disciplinaService.find(pd.getDisciplina().getId());
			obj.getDisciplinas().add(disciplina);
		}
		ProfessorDTO objDTO = new ProfessorDTO(obj);
		return ResponseEntity.ok().body(objDTO);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> insert(@Valid @RequestBody ProfessorDTO objDTO) {
		Professor obj = service.fromDTO(objDTO);
		service.save(obj);
		return ResponseEntity.ok().body(CodigoMensagem.COD_INSERT_SUCCESS.getCodigoMsg());
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<String> update(@Valid @RequestBody ProfessorDTO objDTO) {
		Professor obj = service.fromDTO(objDTO);
		service.save(obj);
		return ResponseEntity.ok().body(CodigoMensagem.COD_UPDATE_SUCCESS.getCodigoMsg());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.ok().body(CodigoMensagem.COD_DELETE_SUCCESS.getCodigoMsg());
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ProfessorDTO>> findAll(
			@RequestParam(value = "matricula", defaultValue = "") String matricula, 
			@RequestParam(value = "nome", defaultValue = "") String nome) {
		List<Professor> list = new ArrayList<>();
		if (matricula.isEmpty() && nome.isEmpty()) {
			list = service.findAll();
		}else {
			ProfessorDTO prof = new ProfessorDTO();
			prof.setMatricula(matricula);
			prof.setNome(nome);
			list = service.search(prof);
		}
		list.sort(Comparator.comparing(Professor::getNome));
		List<ProfessorDTO> listDTO = list.stream().map(obj -> new ProfessorDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ProfessorDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Professor> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ProfessorDTO> listDTO = list.map(obj -> new ProfessorDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}

	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public ResponseEntity<UserDTO> auth(@RequestBody Professor obj) {
		Professor professor = service.authenticator(obj);
		if (professor != null) {
			UserDTO user = service.fromUser(professor);
			return ResponseEntity.ok().body(user);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

}
