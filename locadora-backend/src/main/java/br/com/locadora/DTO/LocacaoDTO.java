package br.com.locadora.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class LocacaoDTO {

    private Long id_locacao;

    private Date dtLocacao;

    private Date dtDevolucaoPrevista;

    private Date dtDevolucaoEfetiva;

    private double valorCobrado;

    private double multaCobrada;

    private ItemDTO item;

    private ClienteDTO cliente;
}
