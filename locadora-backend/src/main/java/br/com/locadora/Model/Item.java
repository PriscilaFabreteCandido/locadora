package br.com.locadora.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Data
@Entity
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_item;

    @Column(name = "numSerie")
    private String numSerie;

    @Column(name = "dtAquisicao") @Temporal(TemporalType.DATE)
    private Date dtAquisicao;

    @Column(name = "tipoItem")
    private String tipoItem;

    @ManyToOne
    @JoinColumn(name = "id_titulo")
    @JsonIgnoreProperties("listaTitulos")
    private Titulo titulo;
    

}
