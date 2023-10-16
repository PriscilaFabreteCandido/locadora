package br.com.locadora.DTO;

import lombok.*;
import java.util.Date;

@Data
public class ItemDTO {

    private Long id_item;

    private String numSerie;

    private Date dtAquisicao;

    private String tipoItem;

}
