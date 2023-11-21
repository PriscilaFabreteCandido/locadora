package br.com.locadora.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Socio extends Cliente {

	@Column
	private String CPF;
	
	@Column
	private String endereco;
	
	@Column
	private String telefone;
	
	@OneToMany(mappedBy = "socio", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("socio")
	private List<Dependente> dependentes;
	
	
}
