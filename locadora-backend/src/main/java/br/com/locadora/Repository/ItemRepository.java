package br.com.locadora.Repository;

import br.com.locadora.Model.Item;
import br.com.locadora.Model.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByNumSerie(String numSerie);
}
