    package br.com.locadora.Model;

    import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
    import jakarta.persistence.*;
    import lombok.*;

    import java.util.ArrayList;
    import java.util.List;

    @Data
    @Entity
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

        @ManyToMany
        @JoinTable(name = "ator_titulo",
                joinColumns = @JoinColumn(name = "id_titulo"),
                inverseJoinColumns = @JoinColumn(name = "id_ator"))
        @JsonIgnoreProperties(value = "listaTitulos")
        private List<Ator> listaAtores = new ArrayList<>();

        @ManyToOne
        @JoinColumn(name = "id_diretor")
        @JsonIgnoreProperties(value = "listaTitulos")
        private Diretor diretor;

        @ManyToOne
        @JoinColumn(name = "id_classe")
        @JsonIgnoreProperties(value = "listaTitulos")
        private Classe classe;

        @OneToMany(mappedBy = "titulo")
        @JsonIgnoreProperties(value = "titulo")
        private List<Item> listaItens;

    }
