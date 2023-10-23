package br.com.locadora.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@Entity
public class Diretor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_diretor;

    @Column(name = "nome")
    private String nome;

    @OneToMany(mappedBy = "diretor")
    @JsonIgnoreProperties("diretor")
    private List<Titulo> listaTitulos;

}
