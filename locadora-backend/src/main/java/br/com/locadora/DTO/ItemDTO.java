package br.com.locadora.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ItemDTO {

    private Long id_item;
    
    private String numSerie;
    
    private Date dtAquisicao;
    
    private String tipoItem;

}
