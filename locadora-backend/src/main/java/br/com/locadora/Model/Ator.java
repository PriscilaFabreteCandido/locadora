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

    @ManyToMany
    @JsonIgnoreProperties ("listaAtores")
    @JoinTable(name = "ator_titulo",
            joinColumns = @JoinColumn(name = "id_ator"),
            inverseJoinColumns = @JoinColumn(name = "id_titulo"))
    private List<Titulo> listaTitulos;


    
}
