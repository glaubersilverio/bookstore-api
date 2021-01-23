package br.com.gsr.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.gsr.bookstore.domain.Categoria;
import br.com.gsr.bookstore.dtos.CategoriaDTO;
import br.com.gsr.bookstore.repositories.CategoriaRepository;
import br.com.gsr.bookstore.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria findById(Long id) {
		Optional<Categoria> obj = this.categoriaRepository.findById(id);
		return obj.orElseThrow(() ->
			new ObjectNotFoundException("Object not found - Id: " + id + " - Type: " + Categoria.class.getName())
		);
	}
	
	public List<Categoria> findAll() {
		return this.categoriaRepository.findAll();
	}
	
	public Categoria create (Categoria obj) {
		obj.setId(null);
		return this.categoriaRepository.save(obj);
	}

	public Categoria update(Long id, CategoriaDTO objDto) {
		Categoria obj = this.findById(id);
		obj.setNome(objDto.getNome());
		obj.setDescricao(objDto.getDescricao());
		return this.categoriaRepository.save(obj);
	}

	public void delete(Long id) {
		try {
			this.categoriaRepository.deleteById(id);			
		} catch (DataIntegrityViolationException e) {
			throw new br.com.gsr.bookstore.service.exceptions.DataIntegrityViolationException("Category cannot be deleted");
		}
	}
}
