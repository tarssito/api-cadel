package br.com.apicadel.resources;

import java.util.ArrayList;
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

import br.com.apicadel.domain.Aluno;
import br.com.apicadel.dto.AlunoDTO;
import br.com.apicadel.resources.utils.CodigoMensagem;
import br.com.apicadel.services.AlunoService;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoResource {

	@Autowired
	private AlunoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Aluno> find(@PathVariable Long id) {
		Aluno obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> insert(@Valid @RequestBody AlunoDTO objDTO) {
		Aluno obj = service.fromDTO(objDTO);
		service.save(obj);
		return ResponseEntity.ok().body(CodigoMensagem.COD_INSERT_SUCCESS.getCodigoMsg());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> update(@Valid @RequestBody AlunoDTO objDTO, @PathVariable Long id) {
		Aluno obj = service.fromDTO(objDTO);
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
	public ResponseEntity<List<AlunoDTO>> findAll(
			@RequestParam(value = "matricula", defaultValue = "") String matricula,
			@RequestParam(value = "nome", defaultValue = "") String nome) {
		List<Aluno> list = new ArrayList<>();
		if (matricula.isEmpty() && nome.isEmpty()) {
			list = service.findAll();
		} else {
			AlunoDTO alunoDTO = new AlunoDTO();
			alunoDTO.setMatricula(matricula);
			alunoDTO.setNome(nome);			
			list = service.search(alunoDTO);
		}
		list.sort(Comparator.comparing(Aluno::getNome));
		List<AlunoDTO> listDTO = list.stream().map(obj -> new AlunoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<AlunoDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Aluno> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<AlunoDTO> listDTO = list.map(obj -> new AlunoDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
}
