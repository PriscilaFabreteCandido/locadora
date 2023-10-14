package br.com.locadora.Controller;

import br.com.locadora.DTO.TituloDTO;
import br.com.locadora.Model.Titulo;
import br.com.locadora.Model.Titulo;
import br.com.locadora.Service.TituloService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/titulos")
public class TituloController {
	
	private final TituloService tituloService;

	@GetMapping
	public ResponseEntity<List<Titulo>> getAllTitulos(){
		List<Titulo> listaTitulos = tituloService.findAll();
		return new ResponseEntity<>(listaTitulos, listaTitulos.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Titulo> getTituloById(@PathVariable Long id){
		Titulo titulo = tituloService.findById(id);
		return new ResponseEntity<>(titulo, HttpStatus.OK);
	}

	@PostMapping("/adicionar")
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Titulo> cadastrarTitulo(@RequestBody @Valid TituloDTO tituloDTO){
		Titulo titulo = tituloService.create(tituloDTO);
		return new ResponseEntity<>(titulo, HttpStatus.CREATED);
	}

	@PutMapping("/editar/{id}")
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Titulo> atualizarTitulo(@RequestBody @Valid TituloDTO tituloDTO, @PathVariable Long id){
		Titulo tituloAtualizado = tituloService.update(tituloDTO, id);
		return new ResponseEntity<>(tituloAtualizado, HttpStatus.OK);
	}

	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<Titulo> excluirTitulo(@PathVariable Long id){
		tituloService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
