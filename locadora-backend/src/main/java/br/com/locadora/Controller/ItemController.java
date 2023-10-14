package br.com.locadora.Controller;

import br.com.locadora.DTO.ItemDTO;
import br.com.locadora.Model.Item;
import br.com.locadora.Service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/itens")
public class ItemController {
	
	private final ItemService itemService;

	@GetMapping
	public ResponseEntity<List<Item>> getAllItems(){
		List<Item> listaItems = itemService.findAll();
		return new ResponseEntity<>(listaItems, listaItems.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Item> getItemById(@PathVariable Long id){
		Item item = itemService.findById(id);
		return new ResponseEntity<>(item, HttpStatus.OK);
	}

	@PostMapping("/adicionar")
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Item> cadastrarItem(@RequestBody @Valid ItemDTO itemDTO){
		Item item = itemService.create(itemDTO);
		return new ResponseEntity<>(item, HttpStatus.CREATED);
	}

	@PutMapping("/editar/{id}")
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Item> atualizarItem(@RequestBody @Valid ItemDTO itemDTO, @PathVariable Long id){
		Item itemAtualizado = itemService.update(itemDTO, id);
		return new ResponseEntity<>(itemAtualizado, HttpStatus.OK);
	}

	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<Item> excluirItem(@PathVariable Long id){
		itemService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
