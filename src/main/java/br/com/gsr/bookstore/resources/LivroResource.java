package br.com.gsr.bookstore.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.gsr.bookstore.domain.Livro;
import br.com.gsr.bookstore.dtos.LivroDTO;
import br.com.gsr.bookstore.service.LivroService;

@RestController
@RequestMapping(value = "/livros")
public class LivroResource {

	@Autowired
	private LivroService livroService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Livro> findById(@PathVariable(name = "id") Long id) {
		Livro obj = this.livroService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping
	public ResponseEntity<List<LivroDTO>> findAll(
			@RequestParam(value="categoria", defaultValue = "0") Long idCat) {
		List<Livro> list = this.livroService.findAll(idCat);
		List<LivroDTO> listDTO = list.stream().map(Livro -> new LivroDTO(Livro))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@PostMapping
	public ResponseEntity<Livro> create(
			@RequestParam(value="categoria", defaultValue = "0") Long idCat,
			@RequestBody Livro obj) {
		obj = this.livroService.create(idCat, obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<LivroDTO> update(@PathVariable (name = "id") Long id, @RequestBody LivroDTO objDto) {
		Livro newObj = this.livroService.update(id, objDto);
		return ResponseEntity.ok().body(new LivroDTO(newObj));
		
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<LivroDTO> updatePatch(@PathVariable (name = "id") Long id, @RequestBody LivroDTO objDto) {
		Livro newObj = this.livroService.update(id, objDto);
		return ResponseEntity.ok().body(new LivroDTO(newObj));
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable (name = "id") Long id) {
		this.livroService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
