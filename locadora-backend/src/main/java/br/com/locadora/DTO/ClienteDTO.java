package br.com.locadora.DTO;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Data
public class ClienteDTO {

	private Long numInscricao;
	private String nome;
	private Date dtNascimento;
	private String sexo;
	private boolean esta_ativo;

	
}
