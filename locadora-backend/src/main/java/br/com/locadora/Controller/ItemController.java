package br.com.locadora.Controller;

import br.com.locadora.DTO.ItemDTO;
import br.com.locadora.Model.Item;
import br.com.locadora.Service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "ItemController", description = "Fornece serviços REST para gerenciar informações de **itens** da locadora.")

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/itens")
public class ItemController {
	
	private final ItemService itemService;

	@GetMapping
	@Operation(summary = "Retorna todos os itens cadastrados.")
	public ResponseEntity<List<Item>> getAllItens(){
		List<Item> listaItens = itemService.findAll();
		return new ResponseEntity<>(listaItens, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Retorna um item específico.")
	public ResponseEntity<Item> getItemById(@PathVariable Long id){
		Item item = itemService.findById(id);
		return new ResponseEntity<>(item, HttpStatus.OK);
	}

	@PostMapping("/adicionar")
	@Operation(summary = "Adiciona um novo item.")
	public ResponseEntity<Item> cadastrarItem(@RequestBody @Valid ItemDTO itemDTO){
		Item item = itemService.create(itemDTO);
		return new ResponseEntity<>(item, HttpStatus.CREATED);
	}

	@PutMapping("/editar/{id}")
	@Operation(summary = "Edita um item existente.")
	public ResponseEntity<Item> atualizarItem(@RequestBody @Valid ItemDTO itemDTO, @PathVariable Long id){
		Item itemAtualizado = itemService.update(itemDTO, id);
		return new ResponseEntity<>(itemAtualizado, HttpStatus.OK);
	}

	@DeleteMapping("/excluir/{id}")
	@Operation(summary = "Exclui um item existente.")
	public ResponseEntity<Item> excluirItem(@PathVariable Long id){
		itemService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
