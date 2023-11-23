package br.com.locadora.Repository;

import br.com.locadora.Model.Item;
import br.com.locadora.Model.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, Long> {
    long countByItemAndDtDevolucaoEfetivaIsNull(Item item);
    Locacao findByItemAndDtDevolucaoEfetivaIsNull(Item item);
    List<Locacao> findByDtDevolucaoEfetivaIsNotNull();
    List<Locacao> findByDtDevolucaoEfetivaIsNull();
}
