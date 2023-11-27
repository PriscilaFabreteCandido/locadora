package br.com.locadora.Controller;

import br.com.locadora.DTO.AtorDTO;
import br.com.locadora.DTO.SocioDTO;
import br.com.locadora.Model.Ator;
import br.com.locadora.Model.Socio;
import br.com.locadora.Service.AtorService;
import br.com.locadora.Service.SocioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "SocioController", description = "Fornece serviços REST para gerenciar informações de **sócios** da locadora.")

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/socios")
public class SocioController {
	
	private final SocioService socioService;
	
	@GetMapping
	@Operation(summary = "Retorna todos os sócios cadastrados.")
	public ResponseEntity<List<Socio>> getAllSocios(){
		List<Socio> listaSocios = socioService.findAll();
		return new ResponseEntity<>(listaSocios, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Retorna um sócio específico.")
	public ResponseEntity<Socio> getSocioById(@PathVariable Long id){
		Socio socio = socioService.findById(id);
		return new ResponseEntity<>(socio, HttpStatus.OK);
	}
	
	@PostMapping("/adicionar")
	@Operation(summary = "Adiciona um novo sócio.")
	public ResponseEntity<Socio> cadastrarSocio(@RequestBody @Valid SocioDTO socioDTO){
		Socio socio = socioService.create(socioDTO);
		return new ResponseEntity<>(socio, HttpStatus.CREATED);
	}
	
	@Operation(summary = "Edita um sócio existente.")
	@PutMapping("/editar/{id}")
	public ResponseEntity<Socio> atualizarSocio(@RequestBody @Valid SocioDTO socioDTO, @PathVariable Long id){
		Socio socioAtualizado = socioService.update(socioDTO, id);
		return new ResponseEntity<>(socioAtualizado, HttpStatus.OK);
	}

	@Operation(summary = "Ativa ou desativa um sócio.")
	@PutMapping("/ativarOrDesativar/{id}")
	public ResponseEntity<Socio> ativarOrDesativarSocio(@RequestBody @Valid SocioDTO socioDTO, @PathVariable Long id){
		Socio socioAtualizado = socioService.ativarOrDesativarSocio(socioDTO, id);
		return new ResponseEntity<>(socioAtualizado, HttpStatus.OK);
	}
	
	@Operation(summary = "Exclui um sócio existente.")
	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<Socio> excluirSocio(@PathVariable Long id){
		socioService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}

