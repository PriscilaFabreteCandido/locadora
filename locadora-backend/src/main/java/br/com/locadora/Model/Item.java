package br.com.locadora.Model;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Getter @Setter
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_item;

    @Column(name = "numSerie")
    private String numSerie;

    @Column(name = "dtAquisicao") @Temporal(TemporalType.DATE)
    private Date dtAquisicao;

    @Column(name = "tipoItem")
    private String tipoItem;

}
