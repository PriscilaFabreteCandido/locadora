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

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
            throw new IllegalStateException("O cliente está em débito. Não é possível realizar nova locação.");
        }

        // Verificar se há itens disponíveis do tipo solicitado
        if (!itemEstaDisponivel(item)) {
            throw new IllegalStateException("Não há itens disponíveis do tipo solicitado");
        }


        // Calcular o valor da locação com base na classe do título
        double valorLocacao = item.getTitulo().getClasse().getValor();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(locacaoDTO.getDtLocacao());
        calendar.add(Calendar.DAY_OF_MONTH, item.getTitulo().getClasse().getPrazoDevolucao());

        Date dataDevolucaoPrevista = calendar.getTime();

        Locacao novaLocacao = new Locacao();
        BeanUtils.copyProperties(locacaoDTO, novaLocacao);
        novaLocacao.setDtDevolucaoPrevista(dataDevolucaoPrevista);
        novaLocacao.setValorCobrado(valorLocacao);
        novaLocacao.setMultaCobrada(0.0);
        novaLocacao.setItem(item);
        novaLocacao.setCliente(cliente);

        return locacaoRepository.save(novaLocacao);
    }

    public Locacao alterarDadosLocacao(Long idLocacao, LocacaoDTO novosDados) {
        Locacao locacao = locacaoRepository.findById(idLocacao).orElseThrow(
                () -> new EntityNotFoundException("Locação não encontrada."));

        validarAlteracaoLocacao(locacao, novosDados);

        // Aplicar as alterações nos dados da locação
        BeanUtils.copyProperties(novosDados, locacao, "id_locacao", "dtLocacao", "item", "cliente");
        locacaoRepository.save(locacao);

        return locacao;
    }
    public void cancelarLocacao(Long idLocacao) {
        Locacao locacao = locacaoRepository.findById(idLocacao).orElseThrow(
                () -> new EntityNotFoundException("Locação não encontrada."));

        validarCancelamentoLocacao(locacao);

        // Remover a locação
        locacaoRepository.deleteById(idLocacao);
    }


    private void validarAlteracaoLocacao(Locacao locacao, LocacaoDTO novosDados) {
        // Validar as alterações da locação, por exemplo, a nova data de devolução prevista
        if (novosDados.getDtDevolucaoPrevista() != null && novosDados.getDtDevolucaoPrevista().before(locacao.getDtLocacao())) {
            throw new IllegalArgumentException("A nova data de devolução prevista deve ser maior ou igual à data de locação.");
        }
    }


    private void validarCancelamentoLocacao(Locacao locacao) {
        if (locacao.getDtDevolucaoEfetiva() != null) {
            throw new IllegalStateException("A locação já foi devolvida e não pode ser cancelada.");
        }

        if (locacao.getValorCobrado() > 0.0) {
            throw new IllegalStateException("A locação possui um pagamento e não pode ser cancelada.");
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

    private boolean itemEstaDisponivel(Item item) {
        return locacaoRepository.countByItemAndDtDevolucaoEfetivaIsNull(item) == 0;
    }

    public List<Locacao> findAll(){
        return locacaoRepository.findAll();
    }

    public void delete(Long id){
        locacaoRepository.deleteById(id);
    }



}
