package br.com.locadora.Controller;

import br.com.locadora.Model.Classe;
import br.com.locadora.Model.Titulo;
import br.com.locadora.Service.ClasseService;
import br.com.locadora.Service.ConsultaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consulta")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService consultaService;

    @GetMapping
    public ResponseEntity<List<Titulo>> consultaTitulo(
            @RequestParam(name = "titulo", required = false) String nomeTitulo,
            @RequestParam(name = "ator", required = false) String nomeAtor,
            @RequestParam(name = "categoria", required = false) String nomeCategoria) {

        System.out.println("Nome do Ator: " + nomeAtor);
        System.out.println("Nome do titulo: " + nomeTitulo);
        System.out.println("Nome categoria: " + nomeCategoria);

        List<Titulo> listaConsulta = consultaService.consultaCombinada(nomeAtor, nomeTitulo, nomeCategoria);
        return new ResponseEntity<>(listaConsulta, HttpStatus.OK);
    }

}