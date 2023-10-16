package br.com.locadora.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@Entity
public class Classe {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_classe;

    @Column(name = "nome")
    private String nome;

    @Column(name = "valor")
    private float valor;

    @Column(name = "prazoDevolucao")
    private int prazoDevolucao;
    
    @OneToMany(mappedBy = "classe")
    @JsonIgnoreProperties(value = "classe")
    private List<Titulo> listaTitulos;
}
