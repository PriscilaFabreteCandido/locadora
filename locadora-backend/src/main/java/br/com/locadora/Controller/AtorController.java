package br.com.locadora.Controller;

import br.com.locadora.DTO.AtorDTO;
import br.com.locadora.Model.Ator;
import br.com.locadora.Service.AtorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "AtorController", description = "Fornece serviços REST para gerenciar informações de **atores** da locadora.")

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/atores")
public class AtorController {

    private final AtorService atorService;

    @GetMapping
    @Operation(summary = "Retorna todos os atores cadastrados.")
    public ResponseEntity<List<Ator>> getAllAtores(){
        List<Ator> listaAtores = atorService.findAll();
        return new ResponseEntity<>(listaAtores, listaAtores.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um ator específico.")
    public ResponseEntity<Ator> getAtorById(@PathVariable Long id){
        Ator ator = atorService.findById(id);
        return new ResponseEntity<>(ator, HttpStatus.OK);
    }

    @PostMapping("/adicionar")
    @Operation(summary = "Adiciona um novo ator.")
    public ResponseEntity<Ator> cadastrarAtor(@RequestBody @Valid AtorDTO atorDTO){
        Ator ator = atorService.create(atorDTO);
        return new ResponseEntity<>(ator, HttpStatus.CREATED);
    }

    @Operation(summary = "Edita um ator existente.")
    @PutMapping("/editar/{id}")
    public ResponseEntity<Ator> atualizarAtor(@RequestBody @Valid AtorDTO atorDTO, @PathVariable Long id){
        Ator atorAtualizado = atorService.update(atorDTO, id);
        return new ResponseEntity<>(atorAtualizado, HttpStatus.OK);
    }

    @Operation(summary = "Exclui um ator existente.")
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Ator> excluirAtor(@PathVariable Long id){
        atorService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
