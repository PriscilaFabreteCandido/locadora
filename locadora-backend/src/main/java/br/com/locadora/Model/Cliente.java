package br.com.locadora.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)

public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numInscricao;
	
	@Column
	private String nome;
	
	@Temporal(TemporalType.DATE)
	private Date dtNascimento;
	
	@Column
	private String sexo;
	
	@Column
	private boolean esta_ativo;
	
}
