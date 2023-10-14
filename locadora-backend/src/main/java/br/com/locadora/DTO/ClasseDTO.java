package br.com.locadora.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClasseDTO {

    private Long id_classe;

    @NotNull
    @NotEmpty(message = "O nome da classe não pode ser vazio.")
    private String nome;
    
    private float valor;
    
    private int prazoDevolucao;
    
}
