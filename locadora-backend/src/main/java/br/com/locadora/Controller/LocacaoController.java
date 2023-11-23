package br.com.locadora.Controller;

import br.com.locadora.DTO.ItemDTO;
import br.com.locadora.DTO.LocacaoDTO;
import br.com.locadora.Model.Item;
import br.com.locadora.Model.Locacao;
import br.com.locadora.Service.LocacaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/locacoes")
public class LocacaoController {

    private final LocacaoService locadoraService;

    @PostMapping("/adicionar")
    @Operation(summary = "Adiciona uma nova locação.")
    public ResponseEntity<Locacao> cadastrarLocacao(@RequestBody @Valid LocacaoDTO locacaoDTO){
        Locacao locacao = locadoraService.efetuarNovaLocacao(locacaoDTO);
        return new ResponseEntity<>(locacao, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Retorna todos as locações cadastrados.")
    public ResponseEntity<List<Locacao>> getAllLocacoes(){
        List<Locacao> listaLocacoes = locadoraService.findAll();
        return new ResponseEntity<>(listaLocacoes, listaLocacoes.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @DeleteMapping("excluir/{id}")
    @Operation(summary = "Retorna todos as locações cadastrados.")
    public ResponseEntity<List<Locacao>> deleteLocacao(@PathVariable Long id){
        locadoraService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
