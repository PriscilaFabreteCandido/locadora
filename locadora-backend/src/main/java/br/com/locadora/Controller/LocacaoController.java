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

    @GetMapping
    @Operation(summary = "Retorna todos as locações cadastrados.")
    public ResponseEntity<List<Locacao>> getAllLocacoes(){
        List<Locacao> listaLocacoes = locadoraService.findAllLocacoes();
        return new ResponseEntity<>(listaLocacoes, listaLocacoes.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @GetMapping("/devolucoes")
    @Operation(summary = "Retorna todos as devoluções feitas.")
    public ResponseEntity<List<Locacao>> getAllDevolucoes(){
        List<Locacao> listaDevolucoes = locadoraService.findAllDevolucoes();
        return new ResponseEntity<>(listaDevolucoes, listaDevolucoes.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }


    @PostMapping("/adicionar")
    @Operation(summary = "Adiciona uma nova locação.")
    public ResponseEntity<Locacao> cadastrarLocacao(@RequestBody @Valid LocacaoDTO locacaoDTO){
        Locacao locacao = locadoraService.efetuarNovaLocacao(locacaoDTO);
        return new ResponseEntity<>(locacao, HttpStatus.CREATED);
    }

    @PutMapping("/editar/{id}")
    @Operation(summary = "Edita uma locação existente.")
    public ResponseEntity<Locacao> atualizarItem(@RequestBody @Valid LocacaoDTO locacaoDTO, @PathVariable Long id){
        Locacao locacaoAtualizada = locadoraService.alterarDadosLocacao(locacaoDTO, id);
        return new ResponseEntity<>(locacaoAtualizada, HttpStatus.OK);
    }

    @DeleteMapping("cancelar/{id}")
    @Operation(summary = "Cancela uma locação cadastrada.")
    public ResponseEntity<List<Locacao>> deleteLocacao(@PathVariable Long id){
        locadoraService.cancelarLocacao(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/devolver/{id}")
    @Operation(summary = "Devolve uma locação/item existente.")
    public ResponseEntity<Locacao> devolverItem(@PathVariable Long id){
        Locacao locacaoAtualizada = locadoraService.realizarDevolucao(id);
        return new ResponseEntity<>(locacaoAtualizada, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna uma locação específica.")
    public ResponseEntity<Locacao> getLocacaoById(@PathVariable Long id){
        Locacao locacao = locadoraService.findById(id);
        return new ResponseEntity<>(locacao, HttpStatus.OK);
    }

}
