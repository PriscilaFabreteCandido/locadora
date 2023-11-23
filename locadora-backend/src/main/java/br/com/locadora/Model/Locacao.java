package br.com.locadora.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Locacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_locacao;
	
	@Temporal(TemporalType.DATE)
	private Date dtLocacao;
	
	@Temporal(TemporalType.DATE)
	private Date dtDevolucaoPrevista;
	
	@Temporal(TemporalType.DATE)
	private Date dtDevolucaoEfetiva;
	
	@Column
	private double valorCobrado;
	
	@Column
	private double multaCobrada;
	
	@ManyToOne
	@JoinColumn(name = "id_item")
	private Item item;

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	@JsonIgnoreProperties("locacoes")
	private Cliente cliente;
}
