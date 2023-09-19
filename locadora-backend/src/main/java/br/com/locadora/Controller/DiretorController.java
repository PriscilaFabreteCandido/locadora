package br.com.locadora.Controller;

import br.com.locadora.DTO.DiretorDTO;
import br.com.locadora.Model.Diretor;
import br.com.locadora.Service.DiretorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diretores")
public class DiretorController {

    @Autowired
    private DiretorService diretorService;

    @GetMapping
    public List<Diretor> listarDiretores(){
        return diretorService.findAll();
    }

    @GetMapping("/{id}")
    public Diretor listarDiretor(@PathVariable Long id){
        return diretorService.findById(id);
    }

    @PostMapping("/adicionar")
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public Diretor cadastrarDiretor(@RequestBody DiretorDTO novoDiretor){
        return diretorService.inserir(novoDiretor);
    }

    @PutMapping("/editar/{id}")
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public Diretor atualizarDiretor(@RequestBody DiretorDTO diretor, @PathVariable Long id){
        return diretorService.atualizar(diretor, id);
    }

    @DeleteMapping("/excluir/{id}")
    public void excluirDiretor(@PathVariable Long id){
        diretorService.excluir(id);
    }
}
