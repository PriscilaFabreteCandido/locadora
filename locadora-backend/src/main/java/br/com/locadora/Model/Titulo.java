package br.com.locadora.Model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
public class Titulo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_titulo;

    @Column(name = "nome")
    private String nome;

    @Column(name = "ano")
    private int ano;

    @Column(name = "sinopse")
    private String sinopse;

    @Column(name = "categoria")
    private String categoria;

}
