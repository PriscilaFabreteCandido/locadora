package br.com.locadora.Controller;

import br.com.locadora.DTO.DependenteDTO;
import br.com.locadora.DTO.SocioDTO;
import br.com.locadora.Model.Dependente;
import br.com.locadora.Model.Socio;
import br.com.locadora.Service.DependenteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "DependenteController", description = "Fornece serviços REST para gerenciar informações de **dependentes** da locadora.")

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/dependentes")
public class DependenteController {
	
	private final DependenteService dependenteService;
	
	@GetMapping
	@Operation(summary = "Retorna todos os dependentes cadastrados.")
	public ResponseEntity<List<Dependente>> getAllDependentes(){
		List<Dependente> listaDependentes = dependenteService.findAll();
		return new ResponseEntity<>(listaDependentes, listaDependentes.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Retorna um dependente específico.")
	public ResponseEntity<Dependente> getDependenteById(@PathVariable Long id){
		Dependente dependente = dependenteService.findById(id);
		return new ResponseEntity<>(dependente, HttpStatus.OK);
	}
	
	@PostMapping("/adicionar")
	@Operation(summary = "Adiciona um novo dependente.")
	public ResponseEntity<Dependente> cadastrarDependente(@RequestBody @Valid DependenteDTO dependenteDTO){
		Dependente dependente = dependenteService.create(dependenteDTO);
		return new ResponseEntity<>(dependente, HttpStatus.CREATED);
	}
	
	@Operation(summary = "Edita um dependente existente.")
	@PutMapping("/editar/{id}")
	public ResponseEntity<Dependente> atualizarDependente(@RequestBody @Valid DependenteDTO dependenteDTO, @PathVariable Long id){
		Dependente dependenteAtualizado = dependenteService.update(dependenteDTO, id);
		return new ResponseEntity<>(dependenteAtualizado, HttpStatus.OK);
	}
	
	@Operation(summary = "Exclui um dependente existente.")
	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<Dependente> excluirDependente(@PathVariable Long id){
		dependenteService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Operation(summary = "Ativa ou desativa um dependente.")
	@PutMapping("/ativarOrDesativar/{id}")
	public ResponseEntity<Dependente> ativarOrDesativarSocio(@RequestBody @Valid DependenteDTO dependenteDTO, @PathVariable Long id){
		Dependente result = dependenteService.ativarOrDesativar(dependenteDTO, id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
