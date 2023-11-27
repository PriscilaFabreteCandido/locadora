package br.com.locadora.Controller;

import br.com.locadora.DTO.DiretorDTO;
import br.com.locadora.Model.Diretor;
import br.com.locadora.Service.DiretorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "DiretorController", description = "Fornece serviços REST para gerenciar informações de **diretores** da locadora.")

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/diretores")
public class DiretorController {
    
    private final DiretorService diretorService;

    @GetMapping
    @Operation(summary = "Retorna todos os diretores cadastrados.")
    public ResponseEntity<List<Diretor>> getAllDiretores(){
        List<Diretor> listaDiretores = diretorService.findAll();
        return new ResponseEntity<>(listaDiretores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um diretor específico.")
    public ResponseEntity<Diretor> getDiretorById(@PathVariable Long id){
        Diretor diretor = diretorService.findById(id);
        return new ResponseEntity<>(diretor, HttpStatus.OK);
    }

    @PostMapping("/adicionar")
    @Operation(summary = "Adiciona um novo diretor.")
    public ResponseEntity<Diretor> cadastrarDiretor(@RequestBody @Valid DiretorDTO diretorDTO){
        Diretor diretor = diretorService.create(diretorDTO);
        return new ResponseEntity<>(diretor, HttpStatus.CREATED);
    }

    @PutMapping("/editar/{id}")
    @Operation(summary = "Edita um diretor existente.")
    public ResponseEntity<Diretor> atualizarDiretor(@RequestBody @Valid DiretorDTO diretorDTO, @PathVariable Long id){
        Diretor diretorAtualizado = diretorService.update(diretorDTO, id);
        return new ResponseEntity<>(diretorAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/excluir/{id}")
    @Operation(summary = "Exclui um diretor existente.")
    public ResponseEntity<Diretor> excluirDiretor(@PathVariable Long id){
        diretorService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
