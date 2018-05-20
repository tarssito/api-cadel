package br.com.apicadel.services;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

import br.com.apicadel.repositories.GenericRepository;
import br.com.apicadel.services.exceptions.DataIntegrityException;
import br.com.apicadel.services.exceptions.ObjectNotFoundException;

/**
 * Classe generica que implementa funções básicas de um service.
 * @author Tarssito Pereira
 *
 * @param <E>
 * @param <ID>
 */
public abstract class GenericServiceImpl<E, ID extends Serializable> implements GenericService<E, ID> {

	@Autowired
	protected GenericRepository<E, ID> repository;

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public GenericServiceImpl(GenericRepository<E, ID> repository) {
		this.repository = repository;
	}

	@Override
	public E find(ID id) {
		Optional<E> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}

	@Override
	public List<E> findAll() {
		return repository.findAll();
	}

	@Override
	public E save(E entity) {
		try {
			return repository.save(entity);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Já existe essa informação salva no sistema");
		}		
	}

	@Override
	public void delete(ID id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não foi possível excluir, existem dependencias associadas a esta entidade");
		}
	}

	@Override
	public Page<E> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
}
