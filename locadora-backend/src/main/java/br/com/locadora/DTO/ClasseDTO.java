package br.com.locadora.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ClasseDTO {
    private Long id_classe;
    private String nome;
    private float valor;

    private int prazoDevolucao;
}
