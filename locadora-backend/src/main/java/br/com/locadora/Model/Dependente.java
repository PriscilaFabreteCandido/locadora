package br.com.locadora.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Dependente extends Cliente{

	@ManyToOne
	@JoinColumn(name = "id_socio")
	@JsonIgnoreProperties("dependentes")
	private Socio socio;

}
