package br.com.locadora.Model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
public class Classe {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_classe;

    @Column(name = "nome")
    private String nome;

    @Column(name = "valor")
    private float valor;

    @Column(name = "prazoDevolucao")
    private int prazoDevolucao;
}
