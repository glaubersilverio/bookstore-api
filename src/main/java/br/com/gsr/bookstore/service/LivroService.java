package br.com.gsr.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.gsr.bookstore.domain.Livro;
import br.com.gsr.bookstore.dtos.LivroDTO;
import br.com.gsr.bookstore.repositories.LivroRepository;
import br.com.gsr.bookstore.service.exceptions.ObjectNotFoundException;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private CategoriaService categoriaService;
	
	public Livro findById(Long id) {
		Optional<Livro> obj = this.livroRepository.findById(id);
		return obj.orElseThrow(() ->
			new ObjectNotFoundException("Object not found - Id: " + id + " - Type: " + Livro.class.getName())
		);
	}
	
	public List<Livro> findAll() {
		return this.livroRepository.findAll();
	}
	
	public Livro create (Livro obj) {
		obj.setId(null);
		return this.livroRepository.save(obj);
	}

	public Livro update(Long id, LivroDTO objDto) {
		Livro obj = this.findById(id);
		obj.setId(objDto.getId());
		obj.setTitulo(objDto.getTitulo());
		return this.livroRepository.save(obj);
	}

	public void delete(Long id) {
		try {
			this.livroRepository.deleteById(id);			
		} catch (DataIntegrityViolationException e) {
			throw new br.com.gsr.bookstore.service.exceptions.DataIntegrityViolationException("Category cannot be deleted");
		}
	}

	public List<Livro> findAll(Long idCat) {
		categoriaService.findById(idCat);
		return livroRepository.findByCategoria(idCat);
	}
}
