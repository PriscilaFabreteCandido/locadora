package br.com.locadora.Repository;

import br.com.locadora.Model.Titulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TituloRepository extends JpaRepository<Titulo, Long> {
    @Query("SELECT t FROM Titulo t " +
            "JOIN t.listaAtores a " +
            "WHERE (a.nome = :nomeAtor OR :nomeAtor IS NULL) " +
            "AND (t.nome = :nomeTitulo OR :nomeTitulo IS NULL) " +
            "AND (t.categoria = :categoria OR :categoria IS NULL)")
    List<Titulo> findByAtorCategoriaNome(
            @Param("nomeAtor") String nomeAtor,
            @Param("nomeTitulo") String nomeTitulo,
            @Param("categoria") String categoria
    );
}