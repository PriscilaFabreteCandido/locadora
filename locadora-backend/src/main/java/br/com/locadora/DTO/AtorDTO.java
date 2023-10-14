package br.com.locadora.DTO;

import br.com.locadora.Model.Titulo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AtorDTO {

    private Long id_ator;

    @NotNull
    @NotEmpty(message = "O nome do ator não pode ser vazio.")
    private String nome;

    //@JsonIgnore
    //private List<Titulo> listaTitulos;

}
