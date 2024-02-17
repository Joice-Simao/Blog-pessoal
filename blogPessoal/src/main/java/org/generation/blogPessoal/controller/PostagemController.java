package org.generation.blogPessoal.controller;

import java.util.List;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {

	@Autowired
	private PostagemRepository repository;

	/*
	 * METODO - retorna Lista do tipo Postagem de TODAS postagens.
	 */
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	/*
	 * METODO - retorna Lista do tipo Postagem pelo ID.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	/*
	 * METODO - retorna Lista do tipo Postagem pelo Titulo.
	 */
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}

	/*
	 * METODO - cria recurso.
	 */
	@PostMapping
	public ResponseEntity<Postagem> post(@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}

	/*
	 * METODO - atualiza recurso.
	 */
	@PutMapping
	public ResponseEntity<Postagem> put(@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
	}

	/*
	 * METODO - deleta recurso pelo Id.
	 */
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id){
		repository.deleteById(id);
	}
}