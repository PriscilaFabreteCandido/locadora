package br.com.locadora.Controller;

import br.com.locadora.DTO.ClasseDTO;
import br.com.locadora.Model.Classe;
import br.com.locadora.Service.ClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class ClasseController {

    @Autowired
    private ClasseService classeService;

    @GetMapping
    public List<Classe> listarClasses(){
        return classeService.findAll();
    }

    @GetMapping("/{id}")
    public Classe listarClasse(@PathVariable Long id){
        return classeService.findById(id);
    }

    @PostMapping("/adicionar")
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public Classe cadastrarClasse(@RequestBody ClasseDTO novaClasse){
        return classeService.inserir(novaClasse);
    }

    @PutMapping("/editar/{id}")
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public Classe atualizarClasse(@RequestBody ClasseDTO classe, @PathVariable Long id){
        return classeService.atualizar(classe, id);
    }

    @DeleteMapping("/excluir/{id}")
    public void excluirClasse(@PathVariable Long id){
        classeService.excluir(id);
    }

}
