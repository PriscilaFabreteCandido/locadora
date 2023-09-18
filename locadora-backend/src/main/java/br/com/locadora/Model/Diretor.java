package br.com.locadora.Model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
public class Diretor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_diretor;

    @Column(name = "nome")
    private String nome;

}
