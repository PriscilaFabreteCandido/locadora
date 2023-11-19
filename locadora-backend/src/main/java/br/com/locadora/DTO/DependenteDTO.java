package br.com.locadora.DTO;

import br.com.locadora.Model.Cliente;
import lombok.Data;

@Data
public class DependenteDTO extends ClienteDTO {

	private SocioDTO socio;
}
