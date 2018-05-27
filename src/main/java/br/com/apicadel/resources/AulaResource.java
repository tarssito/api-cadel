package br.com.apicadel.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.apicadel.domain.Aula;
import br.com.apicadel.resources.utils.CodigoMensagem;
import br.com.apicadel.services.AulaService;

@RestController
@RequestMapping(value = "/aulas")
public class AulaResource {

	@Autowired
	private AulaService service;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> insert(@RequestBody Aula obj) {
		obj = service.fromInsertAula(obj);
		service.save(obj);
		return ResponseEntity.ok().body(CodigoMensagem.COD_INSERT_SUCCESS.getCodigoMsg());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> update(@RequestBody Aula obj, @PathVariable Long id) {
		obj.setId(id);
		obj = service.fromUpdateAula(obj);
		service.save(obj);
		return ResponseEntity.ok().body(CodigoMensagem.COD_UPDATE_SUCCESS.getCodigoMsg());
	}

}
