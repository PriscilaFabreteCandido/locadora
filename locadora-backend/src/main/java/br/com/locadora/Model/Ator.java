package br.com.locadora.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Ator {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ator;

    @Column(name = "nome")
    private String nome;

}
