package br.com.locadora.Controller;

import br.com.locadora.Model.Ator;
import br.com.locadora.Service.AtorService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/atores")
public class AtorController {

    @Autowired
    private AtorService atorService;

    @GetMapping
    public ResponseEntity<List<Ator>> listarAtores(){
        List<Ator> listaAtores = atorService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(listaAtores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ator> listarAtor(@PathVariable Long id){
        Ator ator = atorService.findById(id);

        if(ator == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(ator);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<Ator> cadastrarAtor(@RequestBody Ator novoAtor){
        try{
            Ator ator = atorService.inserir(novoAtor);
            return ResponseEntity.status(HttpStatus.OK).body(ator);
        } catch (IllegalArgumentException mensagem){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Ator> atualizarAtor(@RequestBody Ator ator, @PathVariable Long id){
        try{
            Ator atorAtualizado = atorService.atualizar(ator, id);
            return ResponseEntity.status(HttpStatus.OK).body(atorAtualizado);
        } catch (ResponseStatusException mensagem){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Ator> excluirAtor(@PathVariable Long id){
        try{
            atorService.excluir(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (ResponseStatusException mensagem){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
