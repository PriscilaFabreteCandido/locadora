package br.com.locadora.Controller;

import br.com.locadora.DTO.AtorDTO;
import br.com.locadora.Model.Ator;
import br.com.locadora.Service.AtorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/atores")
public class AtorController {

    private final AtorService atorService;
    
    @GetMapping
    public ResponseEntity<List<Ator>> getAllAtores(){
        List<Ator> listaAtores = atorService.findAll();
        return new ResponseEntity<>(listaAtores, listaAtores.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ator> getAtorById(@PathVariable Long id){
        Ator ator = atorService.findById(id);
        return new ResponseEntity<>(ator, HttpStatus.OK);
    }

    @PostMapping("/adicionar")
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Ator> cadastrarAtor(@RequestBody @Valid AtorDTO atorDTO){
        Ator ator = atorService.create(atorDTO);
        return new ResponseEntity<>(ator, HttpStatus.CREATED);
    }

    @PutMapping("/editar/{id}")
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Ator> atualizarAtor(@RequestBody @Valid AtorDTO atorDTO, @PathVariable Long id){
        Ator atorAtualizado = atorService.update(atorDTO, id);
        return new ResponseEntity<>(atorAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Ator> excluirAtor(@PathVariable Long id){
        atorService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
