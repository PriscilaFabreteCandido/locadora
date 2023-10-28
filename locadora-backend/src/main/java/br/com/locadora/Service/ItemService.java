package br.com.locadora.Service;

import br.com.locadora.DTO.ItemDTO;
import br.com.locadora.Model.Item;
import br.com.locadora.Model.Titulo;
import br.com.locadora.Repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    
    private final ItemRepository itemRepository;
    
    public Item create(ItemDTO itemDTO){
        Item item = new Item();
        
        item.setNumSerie(itemDTO.getNumSerie());
        item.setDtAquisicao(itemDTO.getDtAquisicao());
        item.setTipoItem(itemDTO.getTipoItem());

        if(itemDTO.getTitulo() != null){
            Titulo titulo = new Titulo();
            titulo.setId_titulo(itemDTO.getTitulo().getId_titulo());
            item.setTitulo(titulo);
        }

        return itemRepository.save(item);
    }
    
    public Item update(ItemDTO itemDTO, Long id){
        Item itemEncontrado = findById(id);
        
        itemEncontrado.setNumSerie(itemDTO.getNumSerie());
        itemEncontrado.setDtAquisicao(itemDTO.getDtAquisicao());
        itemEncontrado.setTipoItem(itemDTO.getTipoItem());

        if(itemDTO.getTitulo() != null){
            Titulo titulo = new Titulo();
            titulo.setId_titulo(itemDTO.getTitulo().getId_titulo());
            itemEncontrado.setTitulo(titulo);
        }

        return itemRepository.save(itemEncontrado);
    }
    
    public void delete(Long id){
        Item tituloEncontrado = findById(id);
        
        itemRepository.delete(tituloEncontrado);
    }
    
    public Item findById(Long id){
        return itemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Item não encontrado."));
    }
    
    public List<Item> findAll(){
        return itemRepository.findAll();
    }

}
