package br.com.locadora.DTO;

import br.com.locadora.Model.Cliente;
import lombok.Data;

import java.util.List;

@Data
public class SocioDTO extends ClienteDTO {

	private String CPF;
	private String endereco;
	private String telefone;
	private List<DependenteDTO> dependentes;
	
}
