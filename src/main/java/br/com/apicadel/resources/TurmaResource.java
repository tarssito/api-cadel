package br.com.apicadel.resources;

import java.util.ArrayList;
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
import br.com.apicadel.domain.AlunoTurma;
import br.com.apicadel.domain.Disciplina;
import br.com.apicadel.domain.Turma;
import br.com.apicadel.domain.enums.TurnoLetivo;
import br.com.apicadel.dto.AlunoDTO;
import br.com.apicadel.dto.TurmaDTO;
import br.com.apicadel.resources.utils.CodigoMensagem;
import br.com.apicadel.services.AlunoService;
import br.com.apicadel.services.DisciplinaService;
import br.com.apicadel.services.TurmaService;

@RestController
@RequestMapping(value = "/turmas")
public class TurmaResource {

	@Autowired
	private TurmaService service;

	@Autowired
	private AlunoService alunoService;

	@Autowired
	private DisciplinaService disciplinaService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Turma> find(@PathVariable Long id) {
		Turma obj = service.find(id);

		Disciplina disciplina = disciplinaService.find(obj.getDisciplina().getId());
		obj.setDisciplina(disciplina);

		for (AlunoTurma at : obj.getAlunosTurma()) {
			Aluno aluno = alunoService.find(at.getAluno().getId());
			obj.getAlunos().add(aluno);
		}

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

	@RequestMapping(value = "alunos", method = RequestMethod.GET)
	public ResponseEntity<String> findAlunosDualList(
			@RequestParam(value = "matricula", defaultValue = "") String matricula) throws JSONException {
		List<Aluno> listAlunos = new ArrayList<>();
		if (matricula.isEmpty()) {
			listAlunos = alunoService.findAll();
		} else {
			listAlunos = alunoService.findByMatricula(matricula);
		}

		listAlunos.sort(Comparator.comparing(Aluno::getNome));
		List<AlunoDTO> listDTO = listAlunos.stream().map(obj -> new AlunoDTO(obj)).collect(Collectors.toList());
		JSONArray jsonArray = new JSONArray();
		for (AlunoDTO alunoDTO : listDTO) {
			JSONObject json = new JSONObject();
			json.put("id", alunoDTO.getId());
			json.put("nome", alunoDTO.getNome());
			json.put("cpf", alunoDTO.getCpf());
			json.put("matricula", alunoDTO.getMatricula());
			json.put("email", alunoDTO.getEmail());
			json.put("sexo", alunoDTO.getSexo());
			jsonArray.put(json);
		}

		JSONObject mainObj = new JSONObject();
		mainObj.put("results", jsonArray);
		return ResponseEntity.ok().body(mainObj.toString());
	}

	@RequestMapping(value = "/disciplina", method = RequestMethod.GET)
	public ResponseEntity<List<TurmaDTO>> findByDisciplina(@RequestParam(value = "id") Long idDisciplina) {
		List<Turma> list = service.findByDisciplina(idDisciplina);
		list.sort(Comparator.comparing(Turma::getSigla));
		List<TurmaDTO> listDTO = list.stream().map(obj -> new TurmaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@RequestMapping(value = "filter", method = RequestMethod.GET)
	public ResponseEntity<List<TurmaDTO>> findByTurno(@RequestParam(value = "turno", defaultValue = "") String turno) {
		List<Turma> list = service.findByTurno(turno);
		list.sort(Comparator.comparing(Turma::getSigla));
		List<TurmaDTO> listDTO = list.stream().map(obj -> new TurmaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
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
