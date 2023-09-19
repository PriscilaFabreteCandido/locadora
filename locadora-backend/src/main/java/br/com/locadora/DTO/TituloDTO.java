package br.com.locadora.DTO;

import lombok.Data;

@Data
public class TituloDTO {
    private Long id_titulo;
    private String nome;
    private int ano;
    private String sinopse;
    private String categoria;
}
