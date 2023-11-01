package br.com.locadora.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@Entity
public class Ator {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ator;

    @Column(name = "nome")
    private String nome;

    @ManyToMany(mappedBy = "listaAtores")
    @JsonIgnoreProperties ("listaAtores")
    private List<Titulo> listaTitulos;



}


