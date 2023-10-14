package br.com.locadora.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter @Setter
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
