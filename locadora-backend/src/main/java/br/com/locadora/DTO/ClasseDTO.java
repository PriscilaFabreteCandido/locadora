package br.com.locadora.DTO;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
public class ClasseDTO {

    private Long id_classe;

    @NotNull
    @NotEmpty(message = "O nome da classe não pode ser vazio.")
    private String nome;
    
    private float valor;
    
    private int prazoDevolucao;
    
}
