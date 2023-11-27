package br.com.locadora.Controller;

import br.com.locadora.DTO.TituloDTO;
import br.com.locadora.Model.Titulo;
import br.com.locadora.Model.Titulo;
import br.com.locadora.Service.TituloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "TituloController", description = "Fornece serviços REST para gerenciar informações de **títulos** da locadora.")

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/titulos")
public class TituloController {
	
	private final TituloService tituloService;

	@GetMapping
	@Operation(summary = "Retorna todos os títulos cadastrados.")
	public ResponseEntity<List<Titulo>> getAllTitulos(){
		List<Titulo> listaTitulos = tituloService.findAll();
		return new ResponseEntity<>(listaTitulos, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Retorna um título específico.")
	public ResponseEntity<Titulo> getTituloById(@PathVariable Long id){
		Titulo titulo = tituloService.findById(id);
		return new ResponseEntity<>(titulo, HttpStatus.OK);
	}

	@PostMapping("/adicionar")
	@Operation(summary = "Adiciona um novo título.")
	public ResponseEntity<Titulo> cadastrarTitulo(@RequestBody @Valid TituloDTO tituloDTO){
		Titulo titulo = tituloService.create(tituloDTO);
		return new ResponseEntity<>(titulo, HttpStatus.CREATED);
	}

	@PutMapping("/editar/{id}")
	@Operation(summary = "Edita um título existente.")
	public ResponseEntity<Titulo> atualizarTitulo(@RequestBody @Valid TituloDTO tituloDTO, @PathVariable Long id){
		Titulo tituloAtualizado = tituloService.update(tituloDTO, id);
		return new ResponseEntity<>(tituloAtualizado, HttpStatus.OK);
	}

	@DeleteMapping("/excluir/{id}")
	@Operation(summary = "Exclui um título existente.")
	public ResponseEntity<Titulo> excluirTitulo(@PathVariable Long id){
		tituloService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
