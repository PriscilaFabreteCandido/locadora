package br.com.locadora.DTO;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
public class TituloDTO {

    private Long id_titulo;
    
    @NotNull
    @NotEmpty(message = "O nome do diretor não pode ser vazio.")
    private String nome;
    
    private int ano;
    
    private String sinopse;
    
    private String categoria;

}
