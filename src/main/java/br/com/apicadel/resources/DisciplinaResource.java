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

import br.com.apicadel.domain.Curso;
import br.com.apicadel.domain.CursoDisciplina;
import br.com.apicadel.domain.Disciplina;
import br.com.apicadel.dto.DisciplinaDTO;
import br.com.apicadel.resources.utils.CodigoMensagem;
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
	public ResponseEntity<String> insert(@Valid @RequestBody DisciplinaDTO objDTO) {
		Disciplina obj = service.fromDTO(objDTO);
		service.save(obj);
		return ResponseEntity.ok().body(CodigoMensagem.COD_INSERT_SUCCESS.getCodigoMsg());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> update(@Valid @RequestBody DisciplinaDTO objDTO, @PathVariable Long id) {
		Disciplina obj = service.fromDTO(objDTO);
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
	public ResponseEntity<List<DisciplinaDTO>> findAll(
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "cargaHoraria", defaultValue = "") Integer cargaHoraria) {
		List<Disciplina> list = new ArrayList<>();
		if(nome.isEmpty() && cargaHoraria == null) {
			list = service.findAll();
		} else {
			DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
			disciplinaDTO.setNome(nome);
			if(cargaHoraria != null) {
				disciplinaDTO.setCargaHoraria(cargaHoraria);
			}			
			list = service.search(disciplinaDTO);
		}
		list.sort(Comparator.comparing(Disciplina::getNome));
		List<DisciplinaDTO> listDTO = list.stream().map(obj -> new DisciplinaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@RequestMapping(value = "/curso", method = RequestMethod.GET)
	public ResponseEntity<List<Disciplina>> findByCurso(@RequestParam(value = "id") Long idCurso) {
		List<Disciplina> list = service.findByCurso(idCurso);
		list.sort(Comparator.comparing(Disciplina::getNome));
		return ResponseEntity.ok().body(list);
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
