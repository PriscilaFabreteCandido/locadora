package br.com.locadora.Service;

import br.com.locadora.DTO.LocacaoDTO;
import br.com.locadora.Model.Cliente;
import br.com.locadora.Model.Diretor;
import br.com.locadora.Model.Item;
import br.com.locadora.Model.Locacao;
import br.com.locadora.Repository.ClienteRepository;
import br.com.locadora.Repository.ItemRepository;
import br.com.locadora.Repository.LocacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocacaoService {

    private final LocacaoRepository locacaoRepository;
    private final ItemRepository itemRepository;
    private final ClienteRepository clienteRepository;

    public Locacao efetuarNovaLocacao(LocacaoDTO locacaoDTO) {
        Item item = itemRepository.findById(locacaoDTO.getItem().getId_item()).orElseThrow(
                () -> new EntityNotFoundException("Item não encontrado."));

        Cliente cliente = clienteRepository.findById(locacaoDTO.getCliente().getNumInscricao()).orElseThrow(
                () -> new EntityNotFoundException("Cliente não encontrado."));

        if (clienteEstaEmDebito(cliente)) {
            throw new EntityNotFoundException("O cliente está em débito. Não é possível realizar nova locação.");
        }

        if (!itemEstaDisponivel(item)) {
            throw new EntityNotFoundException("Não há itens disponíveis do tipo solicitado");
        }

        Locacao novaLocacao = new Locacao();
        BeanUtils.copyProperties(locacaoDTO, novaLocacao);
        novaLocacao.setDtDevolucaoPrevista(locacaoDTO.getDtDevolucaoPrevista());
        novaLocacao.setValorCobrado(locacaoDTO.getValorCobrado());
        novaLocacao.setMultaCobrada(0.0);
        novaLocacao.setItem(item);
        novaLocacao.setCliente(cliente);
        novaLocacao.setDtLocacao(new Date());

        return locacaoRepository.save(novaLocacao);
    }

    public Locacao alterarDadosLocacao(LocacaoDTO novosDados, Long idLocacao) {
        Locacao locacao = locacaoRepository.findById(idLocacao).orElseThrow(
                () -> new EntityNotFoundException("Locação não encontrada."));

        validarAlteracaoLocacao(locacao, novosDados);

        BeanUtils.copyProperties(novosDados, locacao, "id_locacao", "dtLocacao", "item", "cliente", "dtDevolucaoPrevista");
        locacao.setDtDevolucaoPrevista(novosDados.getDtDevolucaoPrevista());
        locacaoRepository.save(locacao);

        return locacao;
    }

    public void cancelarLocacao(Long idLocacao) {
        Locacao locacao = locacaoRepository.findById(idLocacao).orElseThrow(
                () -> new EntityNotFoundException("Locação não encontrada."));

        validarCancelamentoLocacao(locacao);

        locacaoRepository.deleteById(idLocacao);
    }

    public Locacao realizarDevolucao(Long idLocacao){

        Locacao locacaoEncontrada = findById(idLocacao);


        Item item = itemRepository.findByNumSerie(locacaoEncontrada.getItem().getNumSerie()).orElseThrow(
                () -> new EntityNotFoundException("Item não encontrado."));

        Locacao locacao = obterLocacaoVigente(item);

        if (locacao == null) {
            throw new EntityNotFoundException("O item informado não está locado no momento.");
        }

        if (locacaoEstaEmAtraso(locacao)) {
            double multa = calcularMulta(locacao);
            locacao.setMultaCobrada(multa);
        }

        locacao.setDtDevolucaoEfetiva(new Date());
        locacaoRepository.save(locacao);

        return locacao;
    }

    public List<Locacao> findAllLocacoes(){
        return locacaoRepository.findByDtDevolucaoEfetivaIsNull();
    }

    public List<Locacao> findAllDevolucoes(){
        return locacaoRepository.findByDtDevolucaoEfetivaIsNotNull();
    }

    public Locacao findById(Long id){
        return locacaoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Locação não encontrada"));
    }

    public void delete(Long id){
        locacaoRepository.deleteById(id);
    }

    //============================================================================

    private boolean itemEstaDisponivel(Item item) {
        return locacaoRepository.countByItemAndDtDevolucaoEfetivaIsNull(item) == 0;
    }

    private Locacao obterLocacaoVigente(Item item) {
        return locacaoRepository.findByItemAndDtDevolucaoEfetivaIsNull(item);
    }

    private boolean locacaoEstaEmAtraso(Locacao locacao) {
        return locacao.getDtDevolucaoPrevista().before(new Date());
    }

    private double calcularMulta(Locacao locacao) {
        double valorLocacao = locacao.getValorCobrado();
        return valorLocacao * 0.1;
    }

    private void validarAlteracaoLocacao(Locacao locacao, LocacaoDTO novosDados) {
        Date novaDataDevolucaoPrevista = novosDados.getDtDevolucaoPrevista();
        Date dataLocacao = locacao.getDtLocacao();

        if (novaDataDevolucaoPrevista != null && dataLocacao != null && novaDataDevolucaoPrevista.before(dataLocacao)) {
            throw new EntityNotFoundException("A nova data de devolução prevista deve ser maior ou igual à data de locação.");
        }
    }

    private void validarCancelamentoLocacao(Locacao locacao) {
        if (locacao.getDtDevolucaoEfetiva() != null) {
            throw new EntityNotFoundException("A locação já foi devolvida e não pode ser cancelada.");
        }

        if (locacao.getValorCobrado() > 0.0) {
            throw new EntityNotFoundException("A locação possui um pagamento e não pode ser cancelada.");
        }
    }

    private boolean clienteEstaEmDebito(Cliente cliente) {
        for (Locacao locacao : cliente.getLocacoes()) {
            if (locacao.getDtDevolucaoEfetiva() == null &&
                    locacao.getDtDevolucaoPrevista().before(new Date())) {
                return true; // O cliente está em débito
            }
        }
        return false; // O cliente não está em débito
    }

    public double obterMulta(Long id){
        Optional<Locacao> locacao = this.locacaoRepository.findById(id);
        if(locacao.isPresent() && this.locacaoEstaEmAtraso(locacao.get())){
            return this.calcularMulta(locacao.get());
        }

        return 0.0;
    }

}




