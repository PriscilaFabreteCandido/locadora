package br.com.locadora.Service;

import br.com.locadora.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

}
