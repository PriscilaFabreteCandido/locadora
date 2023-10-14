package br.com.locadora.Controller;

import br.com.locadora.DTO.ClasseDTO;
import br.com.locadora.Model.Classe;
import br.com.locadora.Service.ClasseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/classes")
public class ClasseController {
    
    private final ClasseService classeService;
    
    @GetMapping
    public ResponseEntity<List<Classe>> getAllClasses(){
        List<Classe> listaClasses = classeService.findAll();
        return new ResponseEntity<>(listaClasses, listaClasses.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Classe> getClasseById(@PathVariable Long id){
        Classe classe = classeService.findById(id);
        return new ResponseEntity<>(classe, HttpStatus.OK);
    }
    
    @PostMapping("/adicionar")
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Classe> cadastrarClasse(@RequestBody @Valid ClasseDTO classeDTO){
        Classe classe = classeService.create(classeDTO);
        
        return new ResponseEntity<>(classe, HttpStatus.CREATED);
    }
    
    @PutMapping("/editar/{id}")
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Classe> atualizarClasse(@RequestBody @Valid ClasseDTO classeDTO, @PathVariable Long id){
        Classe classeAtualizada = classeService.update(classeDTO, id);
        return new ResponseEntity<>(classeAtualizada, HttpStatus.OK);
    }
    
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Classe> excluirClasse(@PathVariable Long id){
        classeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
