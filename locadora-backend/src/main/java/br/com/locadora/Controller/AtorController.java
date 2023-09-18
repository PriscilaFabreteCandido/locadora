package br.com.locadora.Controller;

import br.com.locadora.Model.Ator;
import br.com.locadora.Service.AtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atores")
public class AtorController {

    @Autowired
    private AtorService atorService;

    @GetMapping
    public List<Ator> listarAtores(){
        return atorService.findAll();
    }

    @GetMapping("/{id}")
    public Ator listarAtor(@PathVariable Long id){
        return atorService.findById(id);
    }

    @PostMapping("/adicionar")
    public Ator cadastrarAtor(@RequestBody Ator novoAtor){
        return atorService.inserir(novoAtor);
    }

    @PutMapping("/editar/{id}")
    public Ator atualizarAtor(@RequestBody Ator ator, @PathVariable Long id){
        return atorService.atualizar(ator, id);
    }

    @DeleteMapping("/excluir/{id}")
    public void excluirAtor(@PathVariable Long id){
        atorService.excluir(id);
    }



    /*
    @PostMapping("/atores/adicionar")
    public ResponseEntity<?> adicionarAtor(@RequestBody Ator novoAtor){
        Ator ator = novoAtor;
        return ResponseEntity.status(HttpStatus.CREATED).body(ator);
    }
     */


}
