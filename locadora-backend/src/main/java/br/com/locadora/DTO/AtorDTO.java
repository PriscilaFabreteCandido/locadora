package br.com.locadora.DTO;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
public class AtorDTO {

    private Long id_ator;

    @NotNull
    @NotEmpty(message = "O nome do ator não pode ser vazio.")
    private String nome;

}
