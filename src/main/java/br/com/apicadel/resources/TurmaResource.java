package br.com.apicadel.resources;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
import br.com.apicadel.domain.Turma;
import br.com.apicadel.domain.enums.TurnoLetivo;
import br.com.apicadel.dto.AlunoDTO;
import br.com.apicadel.dto.TurmaDTO;
import br.com.apicadel.resources.utils.CodigoMensagem;
import br.com.apicadel.services.AlunoService;
import br.com.apicadel.services.TurmaService;

@RestController
@RequestMapping(value = "/turmas")
public class TurmaResource {

	@Autowired
	private TurmaService service;

	@Autowired
	private AlunoService alunoService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Turma> find(@PathVariable Long id) {
		Turma obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> insert(@Valid @RequestBody TurmaDTO objDTO) {
		Turma obj = service.fromDTO(objDTO);
		service.save(obj);
		return ResponseEntity.ok().body(CodigoMensagem.COD_INSERT_SUCCESS.getCodigoMsg());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> update(@Valid @RequestBody TurmaDTO objDTO, @PathVariable Long id) {
		Turma obj = service.fromDTO(objDTO);
		obj.setId(id);
		service.save(obj);
		return ResponseEntity.ok().body(CodigoMensagem.COD_UPDATE_SUCCESS.getCodigoMsg());
	}

	@RequestMapping(value= "alunos", method = RequestMethod.GET)
	public ResponseEntity<String> findAlunosDualList() throws JSONException {
		List<Aluno> list = alunoService.findAll();
		list.sort(Comparator.comparing(Aluno::getNome));
		List<AlunoDTO> listDTO = list.stream().map(obj -> new AlunoDTO(obj)).collect(Collectors.toList());		
		JSONObject json = new JSONObject();
		for (AlunoDTO alunoDTO : listDTO) {
			json.put("id", alunoDTO.getId());
			json.put("nome", alunoDTO.getNome());
			json.put("cpf", alunoDTO.getCpf());
			json.put("matricula", alunoDTO.getMatricula());
			json.put("email", alunoDTO.getEmail());
			json.put("sexo", alunoDTO.getSexo());
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.put(json);
		JSONObject mainObj = new JSONObject();
		mainObj.put("results", jsonArray);
		return ResponseEntity.ok().body(mainObj.toString());
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.ok().body(CodigoMensagem.COD_DELETE_SUCCESS.getCodigoMsg());
	}

	@RequestMapping(value = "/turnos", method = RequestMethod.GET)
	public ResponseEntity<TurnoLetivo[]> turnos() {
		TurnoLetivo[] turnos = TurnoLetivo.values();
		return ResponseEntity.ok().body(turnos);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TurmaDTO>> findAll() {
		List<Turma> list = service.findAll();
		list.sort(Comparator.comparing(Turma::getSigla));
		List<TurmaDTO> listDTO = list.stream().map(obj -> new TurmaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<TurmaDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Turma> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<TurmaDTO> listDTO = list.map(obj -> new TurmaDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
}
