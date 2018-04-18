package br.com.apicadel.services;

import java.util.List;

import org.springframework.data.domain.Page;

public interface GenericService<E, K> {

	public E find(K id);
	
	public List<E> findAll();
	
	public E save(E entity);
		
	public void delete(K id);
	
	public Page<E> findPage(Integer page, Integer linesPerPage, String orderBy, String direction);
}
