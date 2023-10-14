package br.com.locadora.Controller;

import br.com.locadora.DTO.DiretorDTO;
import br.com.locadora.Model.Diretor;
import br.com.locadora.Service.DiretorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/diretores")
public class DiretorController {
    
    private final DiretorService diretorService;

    @GetMapping
    public ResponseEntity<List<Diretor>> getAllDiretores(){
        List<Diretor> listaDiretores = diretorService.findAll();
        return new ResponseEntity<>(listaDiretores, listaDiretores.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diretor> getDiretorById(@PathVariable Long id){
        Diretor diretor = diretorService.findById(id);
        return new ResponseEntity<>(diretor, HttpStatus.OK);
    }

    @PostMapping("/adicionar")
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Diretor> cadastrarDiretor(@RequestBody @Valid DiretorDTO diretorDTO){
        Diretor diretor = diretorService.create(diretorDTO);
        return new ResponseEntity<>(diretor, HttpStatus.CREATED);
    }

    @PutMapping("/editar/{id}")
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Diretor> atualizarDiretor(@RequestBody @Valid DiretorDTO diretorDTO, @PathVariable Long id){
        Diretor diretorAtualizado = diretorService.update(diretorDTO, id);
        return new ResponseEntity<>(diretorAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Diretor> excluirDiretor(@PathVariable Long id){
        diretorService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
