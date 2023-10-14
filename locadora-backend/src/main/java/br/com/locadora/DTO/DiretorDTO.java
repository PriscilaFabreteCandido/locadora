package br.com.locadora.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiretorDTO {

    private Long id_diretor;
    
    @NotNull
    @NotEmpty(message = "O nome do diretor não pode ser vazio.")
    private String nome;
    
}
