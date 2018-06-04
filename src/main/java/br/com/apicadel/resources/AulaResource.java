package br.com.apicadel.resources;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.apicadel.domain.Aluno;
import br.com.apicadel.domain.Aula;
import br.com.apicadel.domain.ClasseTurma;
import br.com.apicadel.domain.FrequenciaAluno;
import br.com.apicadel.domain.Turma;
import br.com.apicadel.resources.utils.CodigoMensagem;
import br.com.apicadel.services.AlunoService;
import br.com.apicadel.services.AulaService;
import br.com.apicadel.services.TurmaService;

@RestController
@RequestMapping(value = "/aulas")
public class AulaResource {

	@Autowired
	private AulaService service;

	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private TurmaService turmaService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> insert(@RequestBody Aula obj) {
		Aula aula = service.fromInsertAula(obj);
		service.save(aula);
		return ResponseEntity.ok().body(aula.getId().toString());
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<String> update(@RequestBody Aula obj) {
		obj = service.fromUpdateAula(obj);
		service.save(obj);
		return ResponseEntity.ok().body(CodigoMensagem.COD_UPDATE_SUCCESS.getCodigoMsg());
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Aula>> findAll() {
		List<Aula> list = service.findAll();		
		list.sort(Comparator.comparing(Aula::getData));
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Aula> find(@PathVariable Long id) {
		Aula obj = service.find(id);
		for (ClasseTurma ct : obj.getClasse().getTurmasClasse()) {
			Turma turma = turmaService.find(ct.getTurma().getId());
			Turma newTurma = new Turma();
			
			newTurma.setId(turma.getId());
			newTurma.setSigla(turma.getSigla());
			
			obj.getClasse().getTurmas().add(newTurma);
		}
		for (FrequenciaAluno f : obj.getFrequenciasAlunos()) {
			Aluno aluno = alunoService.find(f.getAluno().getId());
			aluno.setPresenca(f.getPresenca());
			obj.getAlunos().add(aluno);
		}
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ResponseEntity<List<Aula>> search(
			@RequestParam(value = "semestre", defaultValue = "") String semestre,
			@RequestParam(value = "ano", defaultValue = "") String ano,
			@RequestParam(value = "idCurso", defaultValue = "") Long idCurso,
			@RequestParam(value = "idDisciplina", defaultValue = "") Long idDisciplina,
			@RequestParam(value = "idProfessor", defaultValue = "") Long idProfessor) {
		
		List<Aula> list = service.search(semestre, ano, idCurso, idDisciplina, idProfessor);
		list.sort(Comparator.comparing(Aula::getData));
		
		return ResponseEntity.ok().body(list);
	}
	
}
