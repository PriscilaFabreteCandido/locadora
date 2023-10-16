package br.com.locadora.DTO;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
public class DiretorDTO {

    private Long id_diretor;
    
    @NotNull
    @NotEmpty(message = "O nome do diretor não pode ser vazio.")
    private String nome;
    
}
